package com.krusty.soa.gctv1x0.domain;

import com.krusty.soa.utils.Utility;

public class CustomerInfoV1x0 {
	
	protected String _custName = "";
	protected String _custAddr1 = "";
	protected String _custAddr2 = "";	
	protected String _custCity = "";	
	protected String _custState = "";	
	protected String _custZip = "";	
	protected String _custPhone = "";
	protected String _custNumber = "";
	protected String _custRating = "";
	protected String _isOMSe = "";
	
	/**
	 * 
	 */
	public CustomerInfoV1x0() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the _custName
	 */
	public String getCustName() {
		return _custName;
	}
	/**
	 * @param _custName the _custName to set
	 */
	public void setCustName(String _custName) {
		this._custName = _custName;
	}
	/**
	 * @return the _custAddr1
	 */
	public String getCustAddr1() {
		return _custAddr1;
	}
	/**
	 * @param _custAddr1 the _custAddr1 to set
	 */
	public void setCustAddr1(String _custAddr1) {
		this._custAddr1 = _custAddr1;
	}
	/**
	 * @return the _custAddr2
	 */
	public String getCustAddr2() {
		return _custAddr2;
	}
	/**
	 * @param _custAddr2 the _custAddr2 to set
	 */
	public void setCustAddr2(String _custAddr2) {
		this._custAddr2 = _custAddr2;
	}
	/**
	 * @return the _custCity
	 */
	public String getCustCity() {
		return _custCity;
	}
	/**
	 * @param _custCity the _custCity to set
	 */
	public void setCustCity(String _custCity) {
		this._custCity = _custCity;
	}
	/**
	 * @return the _custState
	 */
	public String getCustState() {
		return _custState;
	}
	/**
	 * @param _custState the _custState to set
	 */
	public void setCustState(String _custState) {
		this._custState = _custState;
	}
	/**
	 * @return the _custZip
	 */
	public String getCustZip() {
		return _custZip;
	}
	/**
	 * @param _custZip the _custZip to set
	 */
	public void setCustZip(String _custZip) {
		this._custZip = _custZip;
	}
	/**
	 * @return the _custPhone
	 */
	public String getCustPhone() {
		return _custPhone;
	}
	/**
	 * @param _custPhone the _custPhone to set
	 */
	public void setCustPhone(String _custPhone) {
		this._custPhone = _custPhone;
	}
	/**
	 * @return the _custNumber
	 */
	public String getCustNumber() {
		return _custNumber;
	}
	/**
	 * @param _custNumber the _custNumber to set
	 */
	public void setCustNumber(String _custNumber) {
		this._custNumber = _custNumber;
	}
	/**
	 * @return the _custRating
	 */
	public String getCustRating() {
		return _custRating;
	}
	/**
	 * @param _custRating the _custRating to set
	 */
	public void setCustRating(String _custRating) {
		this._custRating = _custRating;
	}
	/**
	 * @return the _isOMSe
	 */
	public String getIsOMSe() {
		return _isOMSe;
	}
	/**
	 * @param _isOMSe the _isOMSe to set
	 */
	public void setIsOMSe(String _isOMSe) {
		this._isOMSe = _isOMSe;
	}
	
	@Override
	public String toString()
	{
		return Utility.toString(this);
	}
	
	/**
	 * sets all members to empty strings
	 */
	public CustomerInfoV1x0 clear()
	{
    	setCustName("");
    	setCustAddr1("");
    	setCustAddr2("");
    	setCustCity("");
    	setCustState("");
    	setCustZip("");
    	setCustRating("");
    	setCustNumber("");
    	setIsOMSe("");
    	return this;
	}
	
}
