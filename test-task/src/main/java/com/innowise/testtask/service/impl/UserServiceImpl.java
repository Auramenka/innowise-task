package com.innowise.testtask.service.impl;

import com.innowise.testtask.dto.UserDto;
import com.innowise.testtask.exception.NotExistsException;
import com.innowise.testtask.exception.UserDtoValidationException;
import com.innowise.testtask.mappers.UserMapper;
import com.innowise.testtask.model.User;
import com.innowise.testtask.repository.UserRepository;
import com.innowise.testtask.security.JwtUtil;
import com.innowise.testtask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    public UserDto registerUser(UserDto userDto) {
        checkUserDto(userDto);
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public String loginUser(UserDto userDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
        return jwtUtil.generateToken(userDetails.getUsername());
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        User userFromDB = getUserFromDB(id);
        userRepository.deleteById(userFromDB.getId());
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        checkUserDto(userDto);
        User userFromDB = getUserFromDB(id);
        userFromDB.setUsername(userDto.getUsername());
        userFromDB.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userMapper.toDto(userRepository.save(userFromDB));
    }

    private void checkUserDto(UserDto userDto) {
        if (userDto == null) {
            throw new UserDtoValidationException("UserDto is empty");
        }
    }

    private User getUserFromDB(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotExistsException("User doesn't exist"));
    }
}
