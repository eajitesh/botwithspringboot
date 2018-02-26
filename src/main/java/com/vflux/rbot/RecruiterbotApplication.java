package com.vflux.rbot;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vflux.rbot.storage.CloudStorage;

import javazoom.jl.decoder.JavaLayerException;

@SpringBootApplication
public class RecruiterbotApplication implements CommandLineRunner {

	@Autowired CloudStorage awsCloudStorage;
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(RecruiterbotApplication.class);
		app.run(args);
	}

	@Override
	public void run(String... arg0) throws IOException, JavaLayerException  {
		this.awsCloudStorage.uploadFile("message123.txt", "/home/support/Documents/corpus.lm");
	}

}