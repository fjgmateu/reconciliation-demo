package com.reconciliation.demo.service.impl;

import com.reconciliation.demo.data.domain.Shipment;
import com.reconciliation.demo.data.repository.ShipmentRedisRepository;
import com.reconciliation.demo.data.repository.ShipmentRepository;
import com.reconciliation.demo.service.IReconciliationService;
import com.reconciliation.demo.service.config.ApplicationContextProvider;
import com.reconciliation.demo.service.domain.api.CarrierSuccessRequest;
import com.reconciliation.demo.service.handler.ITrackingHandler;
import com.reconciliation.demo.service.validator.ReconciliationValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by FJGMATEU
 */
@Service
public class ReconciliationService implements IReconciliationService {

    public static final String PENDING = "pending";
    public static final String CONCILIATION = "sent_for_concilliation";


    private final Logger logger = LoggerFactory.getLogger(ReconciliationService.class);

    @Autowired
    private ShipmentRepository shipmentRepository;

    //@Autowired
    //private ShipmentRedisRepository shipmentRepository;

    @Autowired
    private ReconciliationValidator reconciliationValidator;


    @Autowired
    private ApplicationContextProvider applicationContextProvider;



    private ITrackingHandler carrierHandler;


    public boolean carrierSuccess (final CarrierSuccessRequest input) {
        logger.info("ReconciliationService.carrierSucess, input: " + input);
        reconciliationValidator.validate(input);
        Shipment shipment=new Shipment(input.getPacklinkReference(),input.getParcels().size(), PENDING);
        shipmentRepository.save(shipment);

        //Factoria de manejador de tracking por carrier
        carrierHandler = applicationContextProvider.getInstanceContextByName(input.getCarrier());

        double totalWeight = input.getParcels().stream().filter(p -> p.getWeight() > 0).mapToDouble(p -> p.getWeight()).sum();

        boolean reconciliation=carrierHandler.track(input.getTrackingNumbers().get(0),input.getParcels().size(),totalWeight);
        //reconciliation notificada y guardamos
        if (reconciliation){
            shipment.setState(CONCILIATION);
            shipmentRepository.save(shipment);
        }
        return reconciliation;
    }




}
