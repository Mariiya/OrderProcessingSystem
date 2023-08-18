package com.mako.event;

import com.mako.dto.EventType;
import com.mako.dto.OrderDTO;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class OrderEvent extends AbstractEvent {

    private OrderDTO order;

    public OrderEvent(EventType eventType, String correlationId, OrderDTO order) {
        super(eventType, correlationId);
        this.order = order;
    }
}
