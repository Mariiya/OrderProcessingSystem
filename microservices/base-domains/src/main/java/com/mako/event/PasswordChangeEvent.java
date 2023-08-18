package com.mako.event;

import com.mako.dto.EventType;
import com.mako.dto.PasswordChangeDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PasswordChangeEvent extends AbstractEvent {

    private PasswordChangeDTO passwordChangeDTO;

    public PasswordChangeEvent(EventType eventType, String correlationId, PasswordChangeDTO dto) {
        super(eventType, correlationId);
        this.passwordChangeDTO = dto;
    }
}
