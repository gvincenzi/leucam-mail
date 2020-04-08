package org.leucam.mail.binding;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MQBinding {
    String USER_REGISTRATION = "userRegistrationChannel";
    String USER_CANCELLATION = "userCancellationChannel";
    String USER_ORDER = "userOrderChannel";

    @Input(USER_REGISTRATION)
    SubscribableChannel userRegistrationChannel();

    @Input(USER_CANCELLATION)
    SubscribableChannel userCancellationChannel();

    @Input(USER_ORDER)
    SubscribableChannel userOrderChannel();
}
