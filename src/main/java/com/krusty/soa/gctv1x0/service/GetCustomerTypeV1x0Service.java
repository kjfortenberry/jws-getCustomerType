package com.krusty.soa.gctv1x0.service;

import com.krusty.soa.gctv1x0.domain.CustomerInfoV1x0;

/**
 * A service to fetch Customer Type information for a given account number and phone number. 
 * 
 * @author K. Fortenberry
 * @since October 06, 2014
 * @see GetCustomerProfileV1x0ServiceImpl
 */
public interface GetCustomerTypeV1x0Service {
	
	/**
	 * Fetches the account and statement details. 
	 * 
	 * @param accountNumber - <code>java.lang.String</code>
	 * @param phoneNumber - <code>java.lang.String</code>
	 * @return <code>com.twc.soa.cust.domain.GCPV1x0.Response</code> 
	 */
	CustomerInfoV1x0 getCustomerInfoFromDB(String accountNumber, String phoneNumber);
}
