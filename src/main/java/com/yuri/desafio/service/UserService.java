package com.yuri.desafio.service;

import com.yuri.desafio.dto.user.CreateUserDTO;
import com.yuri.desafio.dto.user.ResponseUserDTO;

public interface UserService {
    void saveUser(CreateUserDTO userDTO);
    ResponseUserDTO listUserData(Long id);
}
