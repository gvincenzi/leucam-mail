package org.leucam.mail.service;

import org.leucam.mail.dto.OrderDTO;
import org.leucam.mail.dto.RechargeUserCreditLogDTO;
import org.leucam.mail.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;

@Component
public class MailServiceImpl implements MailService {

    @Autowired
    public JavaMailSender javaMailSender;

    @Autowired
    SimpleMailMessage templateRegistrationMessage;

    @Autowired
    SimpleMailMessage templateUserCancellationMessage;

    @Autowired
    SimpleMailMessage templateOrderMessage;

    @Autowired
    SimpleMailMessage templateCreditRechargeConfirmationMessage;

    @Autowired
    SimpleMailMessage templatePaymentConfirmationMessage;

    @Value("${template.subject.registration}")
    public String templateSubjectRegistration;

    @Value("${template.subject.usercancellation}")
    public String templateSubjectUserCancellation;

    @Value("${template.subject.order}")
    public String templateSubjectOrder;

    @Value("${template.subject.creditrecharge}")
    public String templateSubjectCreditRecharge;

    @Value("${template.subject.payment}")
    public String templateSubjectPayment;

    public void sendRegistrationMessage(UserDTO userDTO) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userDTO.getMail());
        message.setSubject(templateSubjectRegistration);
        message.setText(String.format(templateRegistrationMessage.getText(), userDTO.getSurname(), userDTO.getName(), userDTO.getMail()));
        javaMailSender.send(message);
    }

    @Override
    public void sendUserCancellationMessage(UserDTO userDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userDTO.getMail());
        message.setSubject(templateSubjectUserCancellation);
        message.setText(String.format(templateUserCancellationMessage.getText(), userDTO.getName()));
        javaMailSender.send(message);
    }

    public void sendOrderMessage(OrderDTO orderDTO) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(orderDTO.getUser().getMail());
        message.setSubject(templateSubjectOrder);
        message.setText(String.format(templateOrderMessage.getText(), orderDTO.getUser().getName(), orderDTO.toString()));
        javaMailSender.send(message);
    }

    @Override
    public void sendRechargeUserCreditMessage(RechargeUserCreditLogDTO rechargeUserCreditLogDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(rechargeUserCreditLogDTO.getUserCredit().getMail());
        message.setSubject(templateSubjectCreditRecharge);
        message.setText(String.format(templateCreditRechargeConfirmationMessage.getText(), rechargeUserCreditLogDTO.getUserCredit().getName(), NumberFormat.getCurrencyInstance().format(rechargeUserCreditLogDTO.getOldCredit()), NumberFormat.getCurrencyInstance().format(rechargeUserCreditLogDTO.getNewCredit()), rechargeUserCreditLogDTO.getRechargeDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))));
        javaMailSender.send(message);
    }

    public void sendOrderPaymentConfirmationMessage(OrderDTO orderDTO) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(orderDTO.getUser().getMail());
        message.setSubject(templateSubjectPayment);
        message.setText(String.format(templatePaymentConfirmationMessage.getText(), orderDTO.getUser().getName(), orderDTO.toString(), NumberFormat.getCurrencyInstance().format(orderDTO.getAmount())));
        javaMailSender.send(message);
    }
}
