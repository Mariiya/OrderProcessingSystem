package com.mako.event;

import com.mako.dto.EventType;
import com.mako.dto.PaymentInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEvent {

    private PaymentInfoDTO paymentInfo;
    private EventType eventType;
    private String correlationId;

}
