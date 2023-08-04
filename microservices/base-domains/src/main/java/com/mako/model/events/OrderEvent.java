package com.mako.model.events;

import com.mako.model.EventType;
import com.mako.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {
    private Order order;
    private EventType status;
}
