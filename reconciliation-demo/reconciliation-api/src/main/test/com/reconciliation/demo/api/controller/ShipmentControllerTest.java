package com.reconciliation.demo.api.controller;

import com.reconciliation.demo.data.domain.Shipment;
import com.reconciliation.demo.service.exception.ServiceDataException;
import com.reconciliation.demo.service.impl.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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
        Shipment shipment=shipmentService.find(reference);
        if (shipment==null){
            throw new ServiceDataException("No se han encontrado datos");
        }
        return shipment;
    }


    @CrossOrigin
    @RequestMapping(value = "/shipments", method = RequestMethod.GET)
    public List<Shipment> findAll() {
        List<Shipment>  shipments= shipmentService.findAll();
        if (CollectionUtils.isEmpty(shipments)){
            throw new ServiceDataException("No se han encontrado datos");
        }
        return shipments;
    }


}
