package com.halodoc.cb;

import com.halodoc.cm.CustomMessages;
import com.halodoc.ds.DownStreamService;

public class CircuitBreaker {
	private DownStreamService downStreamService = null;
	private int timeTaken = 10;
	private int x = 5000;
	private int totalOccur = 10;
	public static int count = 0;
	public static int time = 0;
	public CircuitBreaker() {
		downStreamService = new DownStreamService();
	}
	
	/* method to get response from downStreamService */
	public String getResponse(String gateway, int t) throws InterruptedException {
		String message = downStreamService.getResponse(gateway);
		if(message.contains("200")) {
			count = 0;
		}else {
			time += t;
			count++;
		}
		if (count <= totalOccur && time >= timeTaken) {
			message = CustomMessages.CONFIG_ERROR;
			Thread.sleep(x);
		}else {
			count = 0;
		}
		return message;
	}

	/* method to get circuit status */
	public String getCircuitStatus() {
		return downStreamService.getCircuitStatus();
	}
}
