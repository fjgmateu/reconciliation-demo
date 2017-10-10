package com.reconciliation.demo.service.domain.api;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by FJGMATEU
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarrierSuccessRequest {


    @JsonProperty("packlink_reference")
    @NotEmpty
    private String packlinkReference;

    @Valid
    @NotEmpty
    private List<Parcel> parcels;

    @NotEmpty
    private String carrier;

    @JsonProperty("service_id")
    @NotEmpty
    private String serviceId;

    @JsonProperty("integration_code")
    @NotEmpty
    private String integrationCode;

    @JsonProperty("tracking_numbers")
    @NotEmpty
    private List<String> trackingNumbers;


    public String getPacklinkReference() {
        return packlinkReference;
    }

    public void setPacklinkReference(String packlinkReference) {
        this.packlinkReference = packlinkReference;
    }

    public List<Parcel> getParcels() {
        return parcels;
    }

    public void setParcels(List<Parcel> parcels) {
        this.parcels = parcels;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getIntegrationCode() {
        return integrationCode;
    }

    public void setIntegrationCode(String integrationCode) {
        this.integrationCode = integrationCode;
    }

    public List<String> getTrackingNumbers() {
        return trackingNumbers;
    }

    public void setTrackingNumbers(List<String> trackingNumbers) {
        this.trackingNumbers = trackingNumbers;
    }


    @java.lang.Override
    public java.lang.String toString() {
        return "CarrierSuccessRequest{" +
                "packlinkReference='" + packlinkReference + '\'' +
                ", parcels=" + parcels +
                ", carrier='" + carrier + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", integrationCode='" + integrationCode + '\'' +
                ", trackingNumbers=" + trackingNumbers +
                '}';
    }
}
