package com.vflux.rbot.storage;

import java.io.InputStream;

import org.springframework.stereotype.Component;

@Component("googleCloudStorage")
public class GoogleCloudStorage implements CloudStorage {

	@Override
	public void uploadFile(String keyName, String filePath) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void uploadAudioStream(String keyName, InputStream inputStream) {
		// TODO Auto-generated method stub
		
	}

}
