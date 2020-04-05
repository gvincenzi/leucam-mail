package org.leucam.mail.listener;

import org.leucam.mail.binding.MQBinding;
import org.leucam.mail.dto.UserDTO;
import org.leucam.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(MQBinding.class)
public class MQListener {
    @Autowired
    MailService mailService;

    @StreamListener(target = MQBinding.USER_REGISTRATION)
    public void processUserRegistration(UserDTO msg) {
        mailService.sendRegistrationMessage(msg);
    }

    @StreamListener(target = MQBinding.USER_CANCELLATION)
    public void processOrderCancellation(UserDTO msg) {
        mailService.sendUserCancellationMessage(msg);
    }
}