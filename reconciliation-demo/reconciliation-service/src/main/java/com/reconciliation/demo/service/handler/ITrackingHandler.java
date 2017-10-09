package com.reconciliation.demo.service.handler;

/**
 * Created by FJGMATEU
 */
public interface ITrackingHandler {

    public boolean track (final String trackingReference,int parcels,double weight);
}
