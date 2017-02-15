package com.portal.deals.exception;

public class UserNotFoundException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	private String errCode;
	private String errMsg;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public UserNotFoundException(String errCode, String errMsg) {
		super(errCode, errMsg);
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

}