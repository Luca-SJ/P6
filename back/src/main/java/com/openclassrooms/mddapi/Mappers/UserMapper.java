package com.openclassrooms.mddapi.Mappers;

import com.openclassrooms.mddapi.Dtos.RegisterDTO;
import com.openclassrooms.mddapi.Dtos.UserDTO;
import com.openclassrooms.mddapi.Models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") // Enables integration with Spring
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    UserDTO toUserDTO(User user);

    User toUser(RegisterDTO userDTO);
}

