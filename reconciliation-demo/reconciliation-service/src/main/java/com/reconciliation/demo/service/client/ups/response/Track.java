package com.reconciliation.demo.service.client.ups.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *FJGMATEU
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Track {


    private String status;
    private int parcels;
    private double weight;
    @JsonProperty("packlink_reference")
    private String packingReference;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getParcels() {
        return parcels;
    }

    public void setParcels(int parcels) {
        this.parcels = parcels;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getPackingReference() {
        return packingReference;
    }

    public void setPackingReference(String packingReference) {
        this.packingReference = packingReference;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Track{" +
                "status='" + status + '\'' +
                ", parcels=" + parcels +
                ", weight=" + weight +
                ", packingReference='" + packingReference + '\'' +
                '}';
    }
}
