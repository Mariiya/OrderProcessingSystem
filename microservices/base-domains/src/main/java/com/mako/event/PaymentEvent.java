package com.mako.event;

import com.mako.dto.EventType;
import com.mako.dto.PaymentInfoDTO;
import lombok.*;

@NoArgsConstructor
@Setter
@Getter
public class PaymentEvent extends AbstractEvent {

    private PaymentInfoDTO paymentInfo;

    public PaymentEvent(EventType eventType, String correlationId, PaymentInfoDTO info) {
        super(eventType, correlationId);
        this.paymentInfo = info;
    }
}
