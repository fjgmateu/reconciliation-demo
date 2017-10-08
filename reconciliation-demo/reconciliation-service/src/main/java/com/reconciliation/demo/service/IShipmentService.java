package com.reconciliation.demo.service;

import com.reconciliation.demo.data.domain.Shipment;

import java.util.List;

/**
 * FJGMATEU
 */
public interface IShipmentService {

        public Shipment find (final String reference);

        public List<Shipment> findAll () ;
    }
