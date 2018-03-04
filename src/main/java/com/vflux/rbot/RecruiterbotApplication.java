package com.vflux.rbot;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

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
		String text = "Hello Ajitesh. Hope you had great holi celebrations. Stay peaceful, calm and happy. Just relax. Just chill. Have a great day ahead. Good Bye!";
		InputStream speechStream = this.customPolly.synthesisSpeechMP3Format(text);
		String s3Key = UUID.randomUUID().toString() + ".mp3";
		String s3URL = this.awsCloudStorage.uploadAudioStream(s3Key, speechStream);
		this.twilioVoiceService.playVoice("+919885069156", s3URL);
	}

}