package com.reconciliation.demo.api.controller;

import com.google.gson.Gson;
import com.reconciliation.demo.api.config.ReconciliationApiApplication;
import com.reconciliation.demo.service.domain.api.CarrierSuccessRequest;
import com.reconciliation.demo.service.domain.api.Parcel;
import com.reconciliation.demo.service.impl.ReconciliationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by FJGMATEU
 */
@RunWith(SpringRunner.class)
//@WebMvcTest(ReconciliationController.class)
@SpringBootTest(classes = {ReconciliationApiApplication.class})
public class ReconciliationControllerTest {


    private MockMvc mockMvc;

    @InjectMocks
    private ReconciliationController reconciliationController;
    @Mock
    private ReconciliationService reconciliationService;

    @Autowired
    private Gson gson;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
              .standaloneSetup(reconciliationController)
              .build();
    }
    @Test
    public void carrier_succes()
          throws Exception {

        CarrierSuccessRequest input=new CarrierSuccessRequest ();
        input.setPacklinkReference("ABCD123456");
        input.setCarrier("UPS");
        input.setIntegrationCode("UPS_ES_A");
        input.setServiceId("28123");

        Parcel parcel=new Parcel();
        parcel.setWeight(1);
        parcel.setWidth(10);
        parcel.setHeight(10);
        parcel.setLenght(10);
        List<Parcel> parcels=new ArrayList<Parcel>();
        parcels.add(parcel);

        List<String> tracking=new ArrayList<String>();
        tracking.add("XYZ123");
        input.setTrackingNumbers(tracking);
        input.setParcels(parcels);

        String requestJson=gson.toJson(input);


        mockMvc.perform(post("/events/carrier_sucess")
                    .contentType(MediaType.APPLICATION_JSON)
                   .content(requestJson)
        )
         .andExpect(status().isCreated());
    }

}
