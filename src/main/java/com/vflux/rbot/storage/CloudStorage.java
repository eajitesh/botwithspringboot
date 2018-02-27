package com.vflux.rbot.storage;

import java.io.InputStream;

public interface CloudStorage {
	void uploadFile(String keyName, String filePath);
	void uploadAudioStream(String keyName, InputStream inputStream);
}
