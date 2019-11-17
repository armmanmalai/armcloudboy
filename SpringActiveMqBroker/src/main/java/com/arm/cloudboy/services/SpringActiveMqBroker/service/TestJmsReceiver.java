package com.arm.cloudboy.services.SpringActiveMqBroker.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TestJmsReceiver {
	@JmsListener(destination = "/queue/mailbox1", containerFactory = "mail01listener")
    public void receiveMessage1(String email) {
        System.out.println("Received <" + email + ">");
    }
	@JmsListener(destination = "/queue/mailbox2", containerFactory = "mail01listener")
    public void receiveMessage2(String email) {
        System.out.println("Received <" + email + ">");
    }
}
