package com.reconciliation.demo.data.repository;

import com.reconciliation.demo.data.domain.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

//@Repository
public class ShipmentRedisRepository  {

    private static final String KEY = "shipment";

    private RedisTemplate<String, Shipment> redisTemplate;
    private HashOperations hashOps;

    @Autowired
    private ShipmentRedisRepository(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOps = redisTemplate.opsForHash();
    }

    public Shipment save(Shipment shipment) {
        hashOps.put(KEY, shipment.getReference(), shipment);
        return shipment;
    }

    public void update(Shipment shipment) {
        hashOps.put(KEY, shipment.getReference(), shipment);
    }

    public Shipment findByReference(String reference) {
        return (Shipment) hashOps.get(KEY, reference);
    }

    public List<Shipment> findAll() {
        return (List<Shipment>) hashOps.entries(KEY).values();
    }

    public void deleteStudent(String id) {
        hashOps.delete(KEY, id);
    }

    public Shipment findOne(Shipment shipment) {
        return (Shipment) hashOps.get(KEY, shipment.getReference());
    }

}
