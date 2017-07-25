package com.example.demo.redis;

public interface IRedisService<K, V> {
    void put(V value);
    V get(K key);
    void delete(K key);
    void flushAll();
}
