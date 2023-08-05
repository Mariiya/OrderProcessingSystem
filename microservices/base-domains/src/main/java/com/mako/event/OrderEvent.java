package com.mako.event;

import com.mako.dto.EventType;
import com.mako.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {

    private OrderDTO order;
    private EventType status;
    private String correlationId;
}
