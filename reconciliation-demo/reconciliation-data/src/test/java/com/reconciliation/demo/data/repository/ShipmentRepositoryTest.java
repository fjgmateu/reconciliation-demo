package com.reconciliation.demo.data.repository;

import com.reconciliation.demo.data.domain.Shipment;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

/**
 * Created by FJGMATEU.
 */

public interface ShipmentRepositoryTest extends MongoRepository<Shipment, String> {

    public Shipment findByReference(String reference);
    public List<Shipment> findAll();

}