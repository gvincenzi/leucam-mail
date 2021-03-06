package org.leucam.mail.service;

import org.leucam.mail.dto.OrderDTO;
import org.leucam.mail.dto.RechargeUserCreditLogDTO;
import org.leucam.mail.dto.UserDTO;

public interface MailService {
    void sendRegistrationMessage(UserDTO userDTO);
    void sendUserCancellationMessage(UserDTO userDTO);
    void sendOrderMessage(OrderDTO orderDTO);
    void sendOrderUpdateMessage(OrderDTO orderDTO);
    void sendRechargeUserCreditMessage(RechargeUserCreditLogDTO rechargeUserCreditLogDTO);
    void sendOrderPaymentConfirmationMessage(OrderDTO msg);
    void sendOrderCancellationMessage(OrderDTO msg);
}
