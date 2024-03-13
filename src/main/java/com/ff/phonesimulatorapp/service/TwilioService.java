package com.ff.phonesimulatorapp.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ff.phonesimulatorapp.configuration.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class TwilioService {
	
	@Autowired
	private TwilioConfig twilioConfig;
	
	private String callSid;
	
	public void makeCall(String toPhoneNumber) throws URISyntaxException {
		Twilio.init(twilioConfig.getAccountId(), twilioConfig.getAuthToken());
		Call call = Call.creator(
				new PhoneNumber(toPhoneNumber),
				new PhoneNumber(twilioConfig.getPhoneNumber()),
				new URI("https://github.com/Nakul240/twiML-file-/blob/master/voice_response.xml"))
		.setStatusCallback(URI.create("http://postb.in/1234abcd"))
		.create();
		
		callSid = call.getSid();
		
	}
	
	
	//getPhoneNumber and getToPhoneNumber gets you the twilio provided from number and registered personal number i.e to number
	//these numbers are configured in the twilioConfig class
	public void sendSms(String msg) {
		Twilio.init(twilioConfig.getAccountId(), twilioConfig.getAuthToken());
		Message.creator(new PhoneNumber(twilioConfig.getToPhoneNumber() ),
										  new PhoneNumber(twilioConfig.getPhoneNumber()),
										  msg)
										  .setMediaUrl(Arrays.asList(URI.create("https://demo.twilio.com/owl.png")))
										  .setStatusCallback(URI.create("http://postb.in/1234abcd"))
										  .create();		
	}
	
	public void endCall() throws URISyntaxException {
		Twilio.init(twilioConfig.getAccountId(), twilioConfig.getAuthToken());
		Call.updater(callSid)
			.setStatus(Call.UpdateStatus.COMPLETED)
			.update();
	}
}
