package com.halodoc.cb;

import com.halodoc.cm.CustomMessages;
import com.halodoc.ds.DownStreamService;

public class CircuitBreaker {
	private DownStreamService downStreamService = null;
	private int timeTaken = 10;
	private int x = 1000;
	private int totalOccur = 10;
	public static int count = 0;
	public static int time = 0;
	public static long prev_time = 0;
	
	public CircuitBreaker() {
		downStreamService = new DownStreamService();
	}
	
	/* method to get response from downStreamService */
	public String getResponse(String gateway) throws InterruptedException {
		long t = System.currentTimeMillis() / 1000;
		String message = downStreamService.getResponse(gateway);
		
		if(message.contains("200")) {
			count = 0;
		}else {
			time += (t - prev_time);
			count++;
			prev_time = t;
		}
		
		if (count >= totalOccur && time >= timeTaken) {
			message = CustomMessages.CONFIG_ERROR;
			Thread.sleep(x);
			count = 0;
			time = 0;
			prev_time = 0;
		}
		return message;
	}

	/* method to get circuit status */
	public String getCircuitStatus() {
		return downStreamService.getCircuitStatus();
	}
}
