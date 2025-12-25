package com.prajwal.moneymatters.service;

import com.prajwal.moneymatters.Model.User;
import com.prajwal.moneymatters.dto.UserRegisterRequest;
import com.prajwal.moneymatters.dto.UserResponse;


public interface UserService {

     UserResponse register(UserRegisterRequest request);

     User findByUsername(String username);
}
