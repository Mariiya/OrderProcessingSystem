package com.mako.event;

import com.mako.dto.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractEvent {
    private EventType eventType;
    private String correlationId;
}
