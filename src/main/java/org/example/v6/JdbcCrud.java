package org.example.v6;
/*
   Ultra-compact JDBC 4.0 CRUD example con PostgreSQL.
   Demuestra: get(id), getAll, insert (devuelve Product con id), update/delete (boolean).
*/

import java.sql.*;
import java.util.*;

public class JdbcCrud {

    static final String URL = System.getenv().getOrDefault("PG_URL", "jdbc:postgresql://localhost:5432/testdb");
    static final String USER = System.getenv().getOrDefault("PG_USER", "postgres");
    static final String PASS = System.getenv().getOrDefault("PG_PASS", "postgres");

    record Product(Long id, String name, double price, int stock) {
    }

    static Connection conn() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    static Product insert(Product p) throws SQLException {
        String sql = "INSERT INTO products(name,price,stock) VALUES(?,?,?) RETURNING id";
        try (Connection c = conn(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.name());
            ps.setDouble(2, p.price());
            ps.setInt(3, p.stock());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return new Product(rs.getLong(1), p.name(), p.price(), p.stock());
            }
        }
        return null;
    }

    static Optional<Product> get(long id) throws SQLException {
        String sql = "SELECT id,name,price,stock FROM products WHERE id=?";
        try (Connection c = conn(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return Optional.of(map(rs));
            }
        }
        return Optional.empty();
    }

    static List<Product> getAll() throws SQLException {
        List<Product> out = new ArrayList<>();
        try (Connection c = conn();
                PreparedStatement ps = c.prepareStatement("SELECT * FROM products ORDER BY id");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next())
                out.add(map(rs));
        }
        return out;
    }

    static boolean update(Product p) throws SQLException {
        String sql = "UPDATE products SET name=?,price=?,stock=? WHERE id=?";
        try (Connection c = conn(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.name());
            ps.setDouble(2, p.price());
            ps.setInt(3, p.stock());
            ps.setLong(4, p.id());
            return ps.executeUpdate() == 1;
        }
    }

    static boolean delete(long id) throws SQLException {
        try (Connection c = conn(); PreparedStatement ps = c.prepareStatement("DELETE FROM products WHERE id=?")) {
            ps.setLong(1, id);
            return ps.executeUpdate() == 1;
        }
    }

    static Product map(ResultSet rs) throws SQLException {
        return new Product(rs.getLong("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("stock"));
    }

    public static void main(String[] args) throws Exception {
        Product p = insert(new Product(null, "Lapiz", 1.2, 100));
        System.out.println("Insertado: " + p);
        System.out.println("Uno: " + get(p.id()));
        System.out.println("Todos: " + getAll());
        System.out.println("Update: " + update(new Product(p.id(), "Lapiz2", 1.5, 80)));
        System.out.println("Delete: " + delete(p.id()));
    }
}
