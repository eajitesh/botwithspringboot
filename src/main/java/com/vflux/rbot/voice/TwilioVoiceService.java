package com.vflux.rbot.voice;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;

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
		String url = TWIMLET_BASE_URL + voicePath;
		
		PhoneNumber to = new PhoneNumber(toNumber); // Replace with your phone number
        PhoneNumber from = new PhoneNumber(this.fromPhoneNumber); // Replace with a Twilio number
        URI uri = URI.create(url);

        // Make the call
        Call call = Call.creator(to, from, uri).create(this.twilioRestClient);
        System.out.println(call.getSid());
	}

}
