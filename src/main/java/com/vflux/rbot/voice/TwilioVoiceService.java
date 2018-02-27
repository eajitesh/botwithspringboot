package com.vflux.rbot.voice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;

@Component
public class TwilioVoiceService implements VoiceService {
	
	private static final String APIVERSION = "2010-04-01";
	private static final String TWIMLET_BASE_URL = "http://twimlets.com/message?Message%5B0%5D=";
	
	private TwilioRestClient twilioRestClient;
	private String fromPhoneNumber;
	
	public TwilioVoiceService(@Autowired TwilioRestClient twilioRestClient, @Autowired String fromPhoneNumber) {
		this.twilioRestClient = twilioRestClient;
		this.fromPhoneNumber = fromPhoneNumber;
	}
	
	@Override
	public void playVoice(String toNumber, String voicePath) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("From", this.fromPhoneNumber);
		params.put("To", toNumber);
		String url = TWIMLET_BASE_URL + voicePath;
		params.put("Url", url);
		
		Request request = new Request(HttpMethod.POST, "https://api.twilio.com/" + APIVERSION + "/Accounts/"+ this.twilioRestClient.getAccountSid() + "/Calls");
		request.addPostParam("From", this.fromPhoneNumber);
		request.addPostParam("To", toNumber);
		request.addPostParam("Url", url);
		
		Response response = this.twilioRestClient.request(request);
		System.out.println(response.getContent());
	}

}
