package com.mako.accountservice.utils;

import com.mako.accountservice.model.User;
import com.mako.dto.UserDTO;
import com.mako.utils.CommonTool;

public class Converter {

    public static User convertToUserEntity(UserDTO userDTO) {
        if (CommonTool.isEmpty(userDTO)) {
            return null;
        }
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setRoles(userDTO.getRoles());
        return user;
    }

    public static UserDTO convertToUserDTO(User user) {
        if (CommonTool.isEmpty(user)) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }

}
