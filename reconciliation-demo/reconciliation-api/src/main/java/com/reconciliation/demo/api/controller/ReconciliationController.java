package com.reconciliation.demo.api.controller;



import com.reconciliation.demo.service.domain.api.CarrierSuccessRequest;
import com.reconciliation.demo.service.impl.ReconciliationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Created by FJGMATEU
 */
@RestController
public class ReconciliationController {

    @Autowired
    private ReconciliationService reconciliationService;

    @CrossOrigin
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping() {
        return "OK";
    }

    @CrossOrigin
    @RequestMapping(value = "/events/carrier_sucess", method = RequestMethod.POST)
    public ResponseEntity<?> carrierSucess(@RequestBody CarrierSuccessRequest input) {

        reconciliationService.carrierSuccess(input);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/shipment/{id}")
                .buildAndExpand(input.getPacklinkReference()).toUri();

        return ResponseEntity.created(location).build();
    }
}
