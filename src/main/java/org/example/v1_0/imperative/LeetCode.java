package org.example.v1_0.imperative;

import java.util.HashMap;

public class LeetCode {
    public static int[] twoSum(int[] nums, int target) {
        int[] result = { 0, 0 };

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }

        return result;
    }

    /*
     * Mientras recorremos los números, guardamos en un mapa los números que ya
     * vimos y su índice.
     * Así, cada vez que vemos un nuevo número, podemos buscar directamente si ya
     * vimos el número que falta para llegar al target.
     */
    public static int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }

        return new int[] {}; // Return empty array if no solution is found
    }

}
