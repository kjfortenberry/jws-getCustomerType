package com.krusty.soa.gctv1x0.ws;

import org.apache.log4j.Logger;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;

import com.krusty.soa.gctv1x0.jaxb.CustomerAccountRating;
import com.krusty.soa.gctv1x0.jaxb.ObjectFactory;
import com.krusty.soa.gctv1x0.jaxb.GetCustomerTypeRequest;
import com.krusty.soa.gctv1x0.jaxb.GetCustomerTypeResponse;
import com.krusty.soa.gctv1x0.jaxb.SOAResponseInfoType;
import com.krusty.soa.gctv1x0.jaxb.SOAResponseInfoType.ServiceInfo;

/**
 * @author kjfortenberry
 */
@Endpoint
public class GetCustomerTypeV1x0Endpoint {
	
	/**
	 * logger object for message logging
	 */
	private static final Logger LOG = Logger.getLogger(GetCustomerTypeV1x0Endpoint.class);
	
	private static ObjectFactory f = new ObjectFactory();

	public GetCustomerTypeV1x0Endpoint() {
		// TODO Auto-generated constructor stub
	}
	
	@PayloadRoot(localPart="GetCustomerTypeRequest", 
		    	 namespace="http://www.krusty.com/soa/types/GetCustomerType/v1x0")
	public GetCustomerTypeResponse execute(GetCustomerTypeRequest r) {
		LOG.debug("entering GetCustomerTypeV1x0:execute...");
		
		// create service info
		ServiceInfo svcInfo = f.createSOAResponseInfoTypeServiceInfo();
		svcInfo.setName("mine");
		svcInfo.setValue("yours");
		
		ServiceInfo svcInfo2 = f.createSOAResponseInfoTypeServiceInfo();
		svcInfo2.setName("up");
		svcInfo2.setValue("down");
		
		// create soa response info
		SOAResponseInfoType respInfo = f.createSOAResponseInfoType();
		respInfo.setEchoBack( r.getSOARequestInfo().getEchoBack() );
		respInfo.getServiceInfo().add(svcInfo);
		respInfo.getServiceInfo().add(svcInfo2);

		// create acct rating
		CustomerAccountRating rating = f.createCustomerAccountRating();
		rating.setAccountNumber( r.getAccountNumber() );
		rating.setPhoneNumber( r.getPhoneNumber() );
		rating.setRatingCode("R");
		rating.setIsOMSe("Y");
		
		// create response
		GetCustomerTypeResponse resp = f.createGetCustomerTypeResponse();
		resp.setResult(rating);
		resp.setSOAResponseInfo(respInfo);

		// return response
		return resp;
	}

}

