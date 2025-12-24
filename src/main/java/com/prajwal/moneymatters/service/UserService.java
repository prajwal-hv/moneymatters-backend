package com.prajwal.moneymatters.service;

import com.prajwal.moneymatters.dto.UserRegisterRequest;
import com.prajwal.moneymatters.dto.UserResponse;


public interface UserService {

     UserResponse register(UserRegisterRequest request);
}
