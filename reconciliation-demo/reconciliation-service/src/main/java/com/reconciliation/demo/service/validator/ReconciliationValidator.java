package com.reconciliation.demo.service.validator;
import com.reconciliation.demo.service.domain.api.CarrierSuccessRequest;
import com.reconciliation.demo.service.exception.ServiceValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by FJGMATEU
 */
@Service
public class ReconciliationValidator {


    private final Logger logger = LoggerFactory.getLogger(ReconciliationValidator.class);

    @Autowired
    private ValidatorBean validatorBean;


    public void validate(final CarrierSuccessRequest input) throws ServiceValidationException {

        validatorBean.validate(input);

    }
}
