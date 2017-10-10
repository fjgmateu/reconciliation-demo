package com.reconciliation.demo.service;

import com.reconciliation.demo.service.domain.api.CarrierSuccessRequest;

/**
 * Created by FJGMATEU
 */
public interface IReconciliationService {

    public boolean carrierSuccess (final CarrierSuccessRequest input);
}
