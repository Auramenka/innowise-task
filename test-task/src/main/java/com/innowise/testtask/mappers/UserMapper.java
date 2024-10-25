package com.innowise.testtask.mappers;

import com.innowise.testtask.dto.UserDto;
import com.innowise.testtask.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
