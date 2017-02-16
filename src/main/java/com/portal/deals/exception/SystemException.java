package com.portal.deals.exception;

/**
 * This is the exception class that will be thrown in case of any system
 * exception is bound to happen
 * 
 * @author Gaurav Jain
 *
 */
public class SystemException extends BaseException {

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
	public SystemException(String errCode, String errMsg) {
		super(errCode, errMsg);
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