package com.vflux.rbot.storage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;

@Component("awsCloudStorage")
public class AWSCloudStorage implements CloudStorage {

	public static final String S3_BASE_URL = "http://s3.amazonaws.com/";
	
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
	
	public String uploadAudioStream(String keyName, InputStream inputStream) throws IOException {
		try {
			//
			// Create Read permission for audio file
			//
			AccessControlList acl = new AccessControlList();
			acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);
			//
			// Create ObjectMetaData for setting content length and content type
			//
			ObjectMetadata objectMetaData = new ObjectMetadata();
			byte[] bytes = IOUtils.toByteArray(inputStream);
			objectMetaData.setContentLength(bytes.length);
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
			objectMetaData.setContentType("audio/mpeg");
			//
			// Put the object in the AWS S3
			//
			PutObjectRequest putObjectRequest = new PutObjectRequest(this.awsS3DataBucket, keyName, byteArrayInputStream, objectMetaData).withAccessControlList(acl);
			this.amazonS3.putObject(putObjectRequest);
		} catch (AmazonServiceException e) {
			System.err.println(e.getErrorMessage());
		}
		return getS3URL(keyName);
	}
	
	private String getS3URL(String key) {
		return S3_BASE_URL + this.awsS3DataBucket + "/" + key;
	}
}
