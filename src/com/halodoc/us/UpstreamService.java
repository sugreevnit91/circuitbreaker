package com.halodoc.us;

import com.halodoc.cb.CircuitBreaker;

public class UpstreamService {
	private CircuitBreaker circuitBreaker;

	public UpstreamService() {
		this.circuitBreaker = new CircuitBreaker();
	}

	public String getStaus() {
		return circuitBreaker.getCircuitStatus();
	}

	public String getResponse(String gateway) throws InterruptedException {
		return circuitBreaker.getResponse(gateway);
	}
}
