package com.vflux.rbot.storage;

public interface CloudStorage {
	void uploadFile(String keyName, String filePath);
}
