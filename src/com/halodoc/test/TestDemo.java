package com.halodoc.test;

import com.halodoc.ds.DownStreamService;
import com.halodoc.us.UpstreamService;

public class TestDemo {

	public static void main(String[] args) throws InterruptedException {

		UpstreamService us = new UpstreamService();
		String status = us.getStaus();
		String response = "";
		for (int i = 1; i <= 10; i++) {
			response = us.getResponse("usd");
		}
		
		System.out.println("Status is: " + status);
		System.out.println("Response from server is: " + response);

		 
		//method to set service available or not 
		DownStreamService.setServiceAvailable(0);

		//method to set service running or not 
		DownStreamService.setServiceRunning(0);

		status = us.getStaus();
		response = us.getResponse("test");
		System.out.println("Status is: " + status);
		System.out.println("Response from server is: " + response);
	}
}
