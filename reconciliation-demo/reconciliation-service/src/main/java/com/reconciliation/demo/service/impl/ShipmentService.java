package com.reconciliation.demo.service.impl;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by FJGMATEU
 */
@Service
public class ShipmentService implements IShipmentService {

    private final Logger logger = LoggerFactory.getLogger(ShipmentService.class);

    @Autowired
    private ShipmentRepository shipmentRepository;


    public Shipment find (final String reference) {
        logger.info("ShipmentService.findAll, reference: " + reference);
        return shimentRepository.findByReference(reference);
    }

    public List<Shipment> findAll () {
       logger.info("ShipmentService.findAll"));
       return shipmentRepository.findAll());
    }


}
