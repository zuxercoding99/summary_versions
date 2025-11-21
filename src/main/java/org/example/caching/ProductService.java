package com.example.caching;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // ============================
    // ✔ GET ALL (cacheado)
    // ============================
    @Cacheable("products")
    public List<Product> getAll() {
        return repository.findAll();
    }

    // ============================
    // ✔ GET BY ID (cacheado)
    // ============================
    @Cacheable(value = "productById", key = "#id")
    public Product getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    // ============================
    // ✔ CREATE
    // - invalida solo el listado
    // ============================
    @CacheEvict(value = "products", allEntries = true)
    public Product create(Product p) {
        return repository.save(p);
    }

    // ============================
    // ✔ UPDATE
    // - invalida lista y item
    // ============================
    @Caching(evict = {
            @CacheEvict(value = "products", allEntries = true),
            @CacheEvict(value = "productById", key = "#id")
    })
    public Product update(Long id, Product data) {
        Product p = repository.findById(id).orElseThrow();

        p.setName(data.getName());
        p.setPrice(data.getPrice());

        return repository.save(p);
    }

    // ============================
    // ✔ DELETE
    // - invalida lista y item
    // ============================
    @Caching(evict = {
            @CacheEvict(value = "products", allEntries = true),
            @CacheEvict(value = "productById", key = "#id")
    })
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
