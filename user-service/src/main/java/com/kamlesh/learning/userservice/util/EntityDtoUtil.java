package com.kamlesh.learning.userservice.util;

import com.kamlesh.learning.userservice.dto.UserDto;
import com.kamlesh.learning.userservice.entity.User;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

    public static User toEntity(UserDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        return user;
    }

    //private static UT toDto (UT u)
}
