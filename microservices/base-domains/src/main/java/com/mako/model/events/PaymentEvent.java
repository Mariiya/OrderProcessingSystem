package com.mako.model.events;

import com.mako.model.EventType;
import com.mako.model.PaymentInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEvent {

    private PaymentInfo paymentInfo;
    private EventType eventType;

}
