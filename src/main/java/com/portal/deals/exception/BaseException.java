package com.portal.deals.exception;

/**
 * This is the base exception class for the exception framework, all other
 * exceptions will extend this class
 * 
 * @author Gaurav Jain
 *
 */
public class BaseException extends RuntimeException {

	/** Default serial version id */
	private static final long serialVersionUID = 1L;

	/** The error code for the exception */
	private String errCode;

	/** Error message associated with the exception */
	private String errMsg;

	/**
	 * The Constructor
	 * 
	 * @param errCode
	 *            The error code for the exception
	 * @param errMsg
	 *            Error message associated with the exception
	 */
	public BaseException(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	/**
	 * @return the errCode
	 */
	public String getErrCode() {
		return errCode;
	}

	/**
	 * @param errCode
	 *            the errCode to set
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	/**
	 * @return the errMsg
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * @param errMsg
	 *            the errMsg to set
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}