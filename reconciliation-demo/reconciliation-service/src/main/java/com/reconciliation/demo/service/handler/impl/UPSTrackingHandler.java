package com.reconciliation.demo.service.handler.impl;

import com.reconciliation.demo.service.client.ups.UPSCarrierClient;
import com.reconciliation.demo.service.client.ups.request.EventRequest;
import com.reconciliation.demo.service.client.ups.response.Track;
import com.reconciliation.demo.service.exception.ServiceClientException;
import com.reconciliation.demo.service.handler.ITrackingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FJGMATEU.
 */
@Service(value = "UPS")
public class UPSTrackingHandler implements ITrackingHandler {

    public static final String RECONCILIATION_EVENT = "reconciliation.item.parse.sucess";
    public static final String FINAL_STATE = "DELIVERED";

    @Autowired
    private UPSCarrierClient syncUpsCarrierClient;


    public boolean track (final String trackingReference,int parcels,double weight)throws ServiceClientException {
        List<Track> tracking=syncUpsCarrierClient.findTracking(trackingReference);

        Track track = tracking.stream().filter(t -> ((t.getWeight() ==weight)&&
                (t.getParcels()==parcels)&&(FINAL_STATE.equals(t.getStatus())))).
                 findFirst().orElse(null);

        //Si existe, reconciliation success.
        //Supongo que la reconciliación se envia al carrier para indicar que todo
        //es correcto.
        //En caso de no coincidir los pesos y paquetes, entiendo que se debería notificar tambíen al cliente y al transportista.


        if (track!=null) {

            EventRequest eventRequest = new EventRequest(RECONCILIATION_EVENT,
                    trackingReference,
                    parcels,
                    weight);

            if (track != null) {
                syncUpsCarrierClient.sendEvent(eventRequest);

            }
            return true;
        }
        return false;
    }

}
