package com.vflux.rbot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.amazonaws.services.securitytoken.model.GetSessionTokenRequest;
import com.amazonaws.services.securitytoken.model.GetSessionTokenResult;

@Configuration
@PropertySource("classpath:application.properties")
public class AWSAppConfig {
	
	private static final Integer TEMPORARY_CREDENTIALS_DURATION_DEFAULT = 7200;
	
	@Value("${aws.access.key.id}") String awsKeyId;
	@Value("${aws.access.key.secret}") String awsKeySecret;
	@Value("${aws.region}") String awsRegion;
	@Value("${aws.s3.data.bucket}") String awsS3DataBucket; 
	@Value("${aws.temporary.credentials.validity.duration}") String credentialsValidityDuration;  
	@Value("${aws.polly.language.code}") String pollyLanguageCode;
	@Value("${aws.polly.voice.id}") String pollyVoiceId;
	
	@Bean(name = "awsKeyId") 
	public String getAWSKeyId() {
		return awsKeyId;
	}
	
	@Bean(name = "awsKeySecret") 
	public String getAWSKeySecret() {
		return awsKeySecret;
	}
	
	@Bean(name = "awsRegion") 
	public Region getAWSPollyRegion() {
		return Region.getRegion(Regions.fromName(awsRegion));
	}
	
	@Bean(name = "awsCredentialsProvider") 
	public AWSCredentialsProvider awsCredentialsProvider() {
		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(this.awsKeyId, this.awsKeySecret);
		return new AWSStaticCredentialsProvider(awsCredentials);
	}
	
	@Bean(name = "sessionCredentials")
	public BasicSessionCredentials sessionCredentials() {
		AWSSecurityTokenServiceClient sts_client = (AWSSecurityTokenServiceClient) AWSSecurityTokenServiceClientBuilder.defaultClient();
		GetSessionTokenRequest session_token_request = new GetSessionTokenRequest();
		if(this.credentialsValidityDuration == null || this.credentialsValidityDuration.trim().equals("")) {
			session_token_request.setDurationSeconds(TEMPORARY_CREDENTIALS_DURATION_DEFAULT);
		} else {
			session_token_request.setDurationSeconds(Integer.parseInt(this.credentialsValidityDuration));
		}
		
		GetSessionTokenResult session_token_result =
			    sts_client.getSessionToken(session_token_request);
		Credentials session_creds = session_token_result.getCredentials();
		BasicSessionCredentials sessionCredentials = new BasicSessionCredentials(
				   session_creds.getAccessKeyId(),
				   session_creds.getSecretAccessKey(),
				   session_creds.getSessionToken());
		return sessionCredentials;
	}
	
	@Bean(name = "awsS3DataBucket") 
	public String awsS3DataBucket() {
		return awsS3DataBucket;
	}
	
	@Bean(name = "pollyLanguageCode") 
	public String pollyLanguageCode() {
		return pollyLanguageCode;
	}
	
	@Bean(name = "pollyVoiceId") 
	public String pollyVoiceId() {
		return pollyVoiceId;
	}
}
