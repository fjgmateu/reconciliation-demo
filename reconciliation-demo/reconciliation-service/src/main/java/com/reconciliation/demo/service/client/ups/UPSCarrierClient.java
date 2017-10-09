package com.reconciliation.demo.service.client.ups;

import com.reconciliation.demo.service.client.ups.request.EventRequest;
import com.reconciliation.demo.service.client.ups.response.Track;
import com.reconciliation.demo.service.client.ups.response.Tracking;
import com.reconciliation.demo.service.exception.ServiceClientException;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by FJGMATEU
 */
@Service
public class UPSCarrierClient {
    private final Logger logger = LoggerFactory.getLogger(UPSCarrierClient.class);

    @Value("${endpoint.ups.tracking}")
    private String endPointUPSTracking;

    @Value("${endpoint.ups.event}")
    private String endPointUPSEvent;

    @Autowired
    @Qualifier("restTemplateWrapRoot")
    private RestTemplate restTemplate;

    public List<Track> findTracking(final String trackingNumber) throws ServiceClientException {

        Tracking response = null;
        try {
            URI targetUrl = UriComponentsBuilder.fromUriString(endPointUPSTracking).buildAndExpand(trackingNumber).toUri();
            HttpEntity entity = new HttpEntity(new HttpHeaders());
            response = restTemplate.exchange(targetUrl, HttpMethod.GET, entity, Tracking.class).getBody();
            if (response==null){
                throw new ServiceClientException("Tracking no encontrado,tracking_number="+trackingNumber);
            }
            if (CollectionUtils.isEmpty(response.getTracking())){
                throw new ServiceClientException("Tracking no encontrado,tracking_number="+trackingNumber);
            }

        } catch (Exception e) {
            String message=ExceptionUtils.getMessage(e);
            logger.error("ERROR, CAUSE: {}", message);
            throw new ServiceClientException("Tracking no encontrado, tracking_number="+trackingNumber+", causa:"+message);
        }
        return response.getTracking();
    }


    public void sendEvent(final EventRequest request) throws ServiceClientException {
        try {
            URI targetUrl = UriComponentsBuilder.fromUriString(endPointUPSEvent).build().toUri();
            HttpEntity<EventRequest> entity = new HttpEntity(request, new HttpHeaders());
            restTemplate.exchange(targetUrl, HttpMethod.POST, entity,Void.class);
        } catch (Exception e) {
            String message=ExceptionUtils.getMessage(e);
            logger.error("ERROR, CAUSE: {}", message);
            throw new ServiceClientException(message);
        }

    }

}
