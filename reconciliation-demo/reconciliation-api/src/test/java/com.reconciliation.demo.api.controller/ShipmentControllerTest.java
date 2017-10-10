package com.reconciliation.demo.api.controller;

import com.reconciliation.demo.api.config.ReconciliationApiApplication;
import com.reconciliation.demo.data.domain.Shipment;
import com.reconciliation.demo.service.impl.ShipmentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;




/**
 * Created by FJGMATEU
 */

@RunWith(SpringRunner.class)
//@WebMvcTest(ReconciliationController.class)
@SpringBootTest(classes = {ReconciliationApiApplication.class})
public class ShipmentControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ShipmentController shipmentController;

    @Mock
    private ShipmentService shipmentService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
              .standaloneSetup(shipmentController)
              .build();
    }

    @Test
    public void givenShipment_whenGetShipment_thenReturnJson()
          throws Exception {

        String reference="ABCD123456";
        Shipment shipment = new Shipment("ABCD123456",1,"pending");
        when(shipmentService.find(reference)).thenReturn(shipment);

        mockMvc.perform(get("/shipment/"+reference)
                          .contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk());

    }


    @Test
    public void givenShipments_whenGetShipments_thenReturnJsonArray()
          throws Exception {

        Shipment shipment = new Shipment("ABCD123456",1,"pending");

        List<Shipment> allShipments = Arrays.asList(shipment);

        given(shipmentService.findAll()).willReturn(allShipments);



        mockMvc.perform(get("/shipments")
                          .contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$[0].reference").value(shipment.getReference()));



    }

}
