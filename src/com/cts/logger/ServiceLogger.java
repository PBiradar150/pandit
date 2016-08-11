package com.cts.logger;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.ThreadContext;

public class ServiceLogger {

	public void logServiceRequest(HttpServletRequest request) {
		String ipAddress = request.getRemoteAddr();
		if (ipAddress .equals("0:0:0:0:0:0:0:1")) {
			ipAddress = getIPaddress();
		}
		String userName = request.getHeader("IIS-REMOTE-USER");
		if (userName == null) {
			userName = System.getProperty("user.name");
		}
		ThreadContext.put("id", UUID.randomUUID().toString());
		ThreadContext.put("ipAddress", ipAddress);
		ThreadContext.put("userId", userName);
	}

	public void clearMap() {
		ThreadContext.clearMap();
	}

	public String getIPaddress() {
		String ipaddress = "";
		try {
			InetAddress ipAddr = InetAddress.getLocalHost();
			ipaddress = ipAddr.getHostAddress();
		} catch (UnknownHostException e) {

			e.printStackTrace();
		}
		return ipaddress;

	}

}
