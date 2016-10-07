package com.krusty.soa.gctv1x0.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

// import com.krusty.soa.cust.dao.GetCustomerTypeV1x0Dao;
import com.krusty.soa.gctv1x0.domain.CustomerInfoV1x0;
// import com.krusty.soa.common.Constant;
// import com.krusty.soa.common.SoaContext;
// import com.krusty.soa.exception.SystemException;
// import com.krusty.soa.msg.AppMsg;
// import com.krusty.soa.msg.AppMsgImpl;
// import com.krusty.soa.msg.MessageCode;
// import com.krusty.soa.utils.ValidationUtility;

/**
 * A service to fetch Customer details for an account number. 
 * 
 * @author K. Fortenberry
 * @since October 06, 2014
 * @see GetCustomerTypeV1x0Service
 */

public class GetCustomerTypeV1x0ServiceImpl implements GetCustomerTypeV1x0Service {
	
	// logger
	private static final Logger LOG = 
			Logger.getLogger(GetCustomerTypeV1x0ServiceImpl.class);
	
	
	// service dao
//	@Autowired
//	@Qualifier("icomsGetCustomerTypeV1x0Dao")
//	private GetCustomerTypeV1x0Dao icomsGetCustomerTypeV1x0Dao;	
	
	@Override
	public CustomerInfoV1x0 getCustomerInfoFromDB(String accountNumber, String phoneNumber) 
	{
		if ( LOG.isDebugEnabled() ) {
			LOG.debug("Entered GetCustomerTypeV1x0ServiceImpl:" +
					  "getCustomerInfoFromDB():["+accountNumber+","+phoneNumber+"]");
		}
		CustomerInfoV1x0 info = new CustomerInfoV1x0();
		info.setCustNumber(accountNumber);
		info.setCustPhone(phoneNumber);
		info.setCustRating("R");
		info.setIsOMSe("Y");
		
		// TODO Auto-generated method stub
		return info;
	}

}
