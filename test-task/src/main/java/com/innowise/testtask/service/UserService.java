package com.innowise.testtask.service;


import com.innowise.testtask.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto registerUser(UserDto userDto);
    String loginUser(UserDto userDto);
    List<UserDto> getAllUsers();
    void deleteUser(Long id);
    UserDto updateUser(Long id, UserDto userDto);
}
