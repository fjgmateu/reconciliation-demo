package com.reconciliation.demo.service.client.ups.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by FJGMATEU
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventRequest {

    @JsonProperty("event")
    private String event;

    @JsonProperty("reference")
    private String reference;

    @JsonProperty("parcels")
    private int parcels;

    @JsonProperty("weight")
    private double weight;


    public EventRequest(String event, String reference, int parcels, double weight) {
        this.event = event;
        this.reference = reference;
        this.parcels = parcels;
        this.weight = weight;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }


    @java.lang.Override
    public java.lang.String toString() {
        return "EventRequest{" +
                "event='" + event + '\'' +
                ", reference='" + reference + '\'' +
                ", parcels=" + parcels +
                ", weight=" + weight +
                '}';
    }
}
