package org.leucam.mail.service;

import org.leucam.mail.dto.UserDTO;

public interface MailService {
    void sendRegistrationMessage(UserDTO userDTO);
    void sendUserCancellationMessage(UserDTO userDTO);
}