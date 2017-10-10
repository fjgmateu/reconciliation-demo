package com.reconciliation.demo.service.impl;

import com.reconciliation.demo.service.config.ReconciliationServiceApplication;
import com.reconciliation.demo.service.domain.api.CarrierSuccessRequest;
import com.reconciliation.demo.service.domain.api.Parcel;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *  FJGMATEU
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReconciliationServiceApplication.class)
public class ReconciliationServiceTest {

    @Autowired
    private ReconciliationService reconciliationService;


    @Test
    public void carrier_Event_Success_With_Reconciliation() {
        CarrierSuccessRequest input=new CarrierSuccessRequest ();
        input.setPacklinkReference("ABCD123456");
        input.setCarrier("UPS");
        input.setIntegrationCode("UPS_ES_A");
        input.setServiceId("28123");

        Parcel parcel=new Parcel();
        parcel.setWeight(0.5);
        parcel.setWidth(10);
        parcel.setHeight(10);
        parcel.setLenght(10);
        List<Parcel> parcels=new ArrayList<Parcel>();
        parcels.add(parcel);

        List<String> tracking=new ArrayList<String>();
        tracking.add("XYZ123");
        input.setTrackingNumbers(tracking);
        input.setParcels(parcels);

        assertTrue ( reconciliationService.carrierSuccess(input));

    }


    @Test
    public void carrier_Event_Success_With_Not_Reconciliation() {
        CarrierSuccessRequest input=new CarrierSuccessRequest ();
        input.setPacklinkReference("ABCD123456");
        input.setCarrier("UPS");
        input.setIntegrationCode("UPS_ES_A");
        input.setServiceId("28123");

        Parcel parcel=new Parcel();
        parcel.setWeight(100);
        parcel.setWidth(10);
        parcel.setHeight(10);
        parcel.setLenght(10);
        List<Parcel> parcels=new ArrayList<Parcel>();
        parcels.add(parcel);

        List<String> tracking=new ArrayList<String>();
        tracking.add("XYZ123");
        input.setTrackingNumbers(tracking);
        input.setParcels(parcels);

        assertFalse(reconciliationService.carrierSuccess(input));

    }

    // write test cases here
}