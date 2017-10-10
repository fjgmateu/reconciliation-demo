package com.reconciliation.demo.data.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;


/**
 * Created by FJGMATEU.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection="shipment")
public class Shipment {

    @Id
    private String reference;

    @Field("parcels")
    private int parcels;
	
	@Field("state")
    private String state;

    public Shipment(String reference, int parcels, String state) {
        this.reference = reference;
        this.parcels = parcels;
        this.state = state;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getParcels() {
        return parcels;
    }

    public void setParcels(int parcels) {
        this.parcels = parcels;
    }



    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    @java.lang.Override
    public java.lang.String toString() {
        return "Shipment{" +
                "reference='" + reference + '\'' +
                ", parcels=" + parcels +
                ", state='" + state + '\'' +
                '}';
    }
}
