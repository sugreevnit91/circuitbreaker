package com.halodoc.ds;

import com.halodoc.cm.CustomMessages;

public class DownStreamService {

	private String curGateway = "test";
	public static int serviceAvailable = 1;
	public static int serviceRunning = 1;

	public String getCurGateway() {
		return curGateway;
	}

	public void setCurGateway(String curGateway) {
		this.curGateway = curGateway;
	}

	public int getServiceAvailable() {
		return serviceAvailable;
	}

	public static void setServiceAvailable(int service) {
		serviceAvailable = service;
	}

	public int getServiceRunning() {
		return serviceRunning;
	}

	public static void setServiceRunning(int running) {
		serviceRunning = running;
	}

	public String getResponse(String gateway) {
		String message = "";
		if (!curGateway.equals(gateway)) {
			message = CustomMessages.BAD_GATEWAY;
		} else if (serviceAvailable == 0) {
			message = CustomMessages.SERVICE_UNAVAILABLE;
		} else if (serviceRunning == 0) {
			message = CustomMessages.INTERNAL_SERVER;
		} else {
			message = CustomMessages.SUCCESS;
		}
		return message;
	}

	public String getCircuitStatus() {
		if (serviceRunning == 1)
			return "open";
		return "closed";
	}

}
