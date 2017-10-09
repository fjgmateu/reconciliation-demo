package com.reconciliation.demo.api.controller;

import com.reconciliation.demo.data.domain.Shipment;
import com.reconciliation.demo.service.impl.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by FJGMATEU
 */
@RestController
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;


    @CrossOrigin
    @RequestMapping(value = "/shipment/{reference}", method = RequestMethod.GET)
    public Shipment find(@PathVariable("reference") String reference) {
        return shipmentService.find(reference);
    }


    @CrossOrigin
    @RequestMapping(value = "/shipments", method = RequestMethod.GET)
    public List<Shipment> findAll() {
        return shipmentService.findAll();
    }


}
