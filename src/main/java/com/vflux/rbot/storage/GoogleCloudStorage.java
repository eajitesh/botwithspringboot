package com.vflux.rbot.storage;

import java.io.InputStream;

import org.springframework.stereotype.Component;

@Component("googleCloudStorage")
public class GoogleCloudStorage implements CloudStorage {

	@Override
	public void uploadFile(String keyName, String filePath) {
	}

	public static void main(String[] args) {
	}

	@Override
	public String uploadAudioStream(String keyName, InputStream inputStream) {
		return null;
	}

}
