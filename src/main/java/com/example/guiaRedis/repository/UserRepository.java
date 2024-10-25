package com.example.guiaRedis.repository;

import java.util.List;

import com.example.guiaRedis.model.Persona;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;



@Repository
public class UserRepository {

    public static final String USER_KEY = "USER";

    private HashOperations hashOperations;

    private RedisTemplate redisTemplate;

    public UserRepository(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public void save(Persona persona) {
        hashOperations.put(USER_KEY, persona.getId(), persona);
    }

    public List findAll(){
        return hashOperations.values(USER_KEY);
    }

    public Persona findById(String id) {
        return (Persona) hashOperations.get(USER_KEY, id);
    }

    public void update(Persona persona) {
        save(persona);
    }

    public void delete(String id) {
        hashOperations.delete(USER_KEY, id);
    }

}

