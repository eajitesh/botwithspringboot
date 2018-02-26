package com.vflux.rbot.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Component("awsCloudStorage")
public class AWSCloudStorage implements CloudStorage {

	@Autowired
	String awsS3DataBucket;

	private AmazonS3 amazonS3;
	

	public AWSCloudStorage(@Autowired Region awsRegion, @Autowired BasicSessionCredentials sessionCredentials) {
		this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(sessionCredentials)).build();
	}

//	
//  Sample code for instantiating AmazonS3 using AWS security credentials	
//
//	public AWSCloudStorage(@Autowired Region awsRegion, @Autowired AWSCredentialsProvider awsCredentialsProvider) {
//		this.amazonS3 = AmazonS3ClientBuilder.standard().withCredentials(awsCredentialsProvider)
//				.withRegion(awsRegion.getName()).build();
//	}
//

	public void uploadFile(String keyName, String filePath) {
		try {
			this.amazonS3.putObject(this.awsS3DataBucket, keyName, filePath);
		} catch (AmazonServiceException e) {
			System.err.println(e.getErrorMessage());
		}
	}
}
