package com.mako.event;

import com.mako.dto.EventType;
import com.mako.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserEvent extends AbstractEvent {

    private UserDTO user;

    public UserEvent(EventType eventType, String correlationId, UserDTO user) {
        super(eventType, correlationId);
        this.user = user;
    }
}
