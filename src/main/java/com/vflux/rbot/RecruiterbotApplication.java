package com.vflux.rbot;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vflux.rbot.storage.CloudStorage;
import com.vflux.rbot.texttospeech.CustomPolly;
import com.vflux.rbot.voice.VoiceService;


@SpringBootApplication
public class RecruiterbotApplication implements CommandLineRunner {

	@Autowired VoiceService twilioVoiceService;
	@Autowired CustomPolly customPolly;
	@Autowired CloudStorage awsCloudStorage;
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(RecruiterbotApplication.class);
		app.run(args);
	}

	@Override
	public void run(String... arg0) throws IOException {
		String text = "Hello Ajitesh! This is Polly. How are you doing today?";
		InputStream speechStream = this.customPolly.synthesisSpeechMP3Format(text);
		this.awsCloudStorage.uploadAudioStream("helloworld.mp3", speechStream);
		this.twilioVoiceService.playVoice("+919885069156", "http://remainders-25022018.s3.amazonaws.com/helloworld.mp3");
	}

}