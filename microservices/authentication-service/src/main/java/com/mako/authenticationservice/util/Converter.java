package com.mako.authenticationservice.util;

import com.mako.authenticationservice.model.PasswordReset;
import com.mako.dto.PasswordChangeDTO;
import com.mako.utils.CommonTool;

public class Converter {
    public static PasswordChangeDTO convertToPasswordChangeDTO(PasswordReset passwordReset) {
        if (CommonTool.isEmpty(passwordReset)) {
            return null;
        }
        PasswordChangeDTO passwordChangeDTO = new PasswordChangeDTO();
        passwordChangeDTO.setId(passwordReset.getId());
        passwordChangeDTO.setToken(passwordReset.getToken());
        passwordChangeDTO.setUserEmail(passwordReset.getUserEmail());
        passwordChangeDTO.setNewPassword(passwordReset.getNewPassword());
        return passwordChangeDTO;
    }


}
