package com.asefa.service.tarificacion.producto.etica.api.controller;

import com.asefa.service.tarificacion.producto.etica.service.domain.api.TarifaProductoResponse;
import com.asefa.service.tarificacion.producto.etica.service.domain.api.TarifaProductoRequest;
import com.asefa.service.tarificacion.producto.etica.service.impl.TarificacionProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by FJGMATEU on 16/06/2016.
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
    public ResponseEntity<?> carrierSucess(@RequestBody ShipmentRequest input) {

        reconciliationService.carrierSucess(input);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/shipment/{id}")
                .buildAndExpand(input.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
  .orElse(ResponseEntity.noContent().build());
}
