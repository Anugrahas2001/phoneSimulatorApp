package com.ff.phonesimulatorapp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "twilio")
public class TwilioConfig {

	@Value("${twilio.accountId}")
	private String accountId;

	@Value("${twilio.authToken}")
	private String authToken;

	@Value("${twilio.phoneNumber}")
	private String phoneNumber;
	
	@Value("${twilio.toPhoneNumber}")
	private String toPhoneNumber;

}
