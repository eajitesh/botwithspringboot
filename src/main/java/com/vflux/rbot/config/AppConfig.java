package com.vflux.rbot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
	@Value("${aws.access.key.id}") String awsKeyId;
	@Value("${aws.access.key.secret}") String awsKeySecret;
	@Value("${aws.polly.region}") String awsPollyRegion;
	
	@Bean(name = "awsKeyId") 
	public String getAWSKeyId() {
		return awsKeyId;
	}
	
	@Bean(name = "awsKeySecret") 
	public String getAWSKeySecret() {
		return awsKeySecret;
	}
	
	@Bean(name = "awsPollyRegion") 
	public Region getAWSPollyRegion() {
		return Region.getRegion(Regions.fromName(awsPollyRegion));
	}
}
