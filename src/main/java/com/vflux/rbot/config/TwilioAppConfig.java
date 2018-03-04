package com.vflux.rbot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.twilio.http.TwilioRestClient;
import com.twilio.http.TwilioRestClient.Builder;


@Configuration
@PropertySource("classpath:application.properties")
public class TwilioAppConfig {

	@Value("${twilio.account.sid}") String accountSID;
	@Value("${twilio.auth.token}") String authToken;
	@Value("${twilio.from.phone.number}") String fromPhoneNumber;
	
	@Bean(name = "twilioRestClient")
	public TwilioRestClient twilioRestClient() {
		return (new Builder(this.accountSID, this.authToken)).build();
	}
	
	@Bean(name = "fromPhoneNumber")
	public String fromPhoneNumber() {
		return this.fromPhoneNumber;
	}
	
	@Bean(name = "twilioAccountSID")
	public String twilioAccountSID() {
		return this.accountSID;
	}
	
	@Bean(name = "twilioAuthToken")
	public String twilioAuthToken() {
		return this.authToken;
	}
}
