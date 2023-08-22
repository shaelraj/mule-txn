package com.sample;

public class CxfErrorHandler {

	public SystemEvent processError(Object originalPayload, Object error,  Exception ex) {

		SystemEvent se = new SystemEvent();
		se.setMessage(ex.getMessage());
		se.setEvent("SOAP-EVENT");
		return se;
	}

}
