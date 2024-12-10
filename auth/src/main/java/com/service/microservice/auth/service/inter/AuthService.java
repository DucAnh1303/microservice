package com.service.microservice.auth.service.inter;

import com.service.microservice.support.BasePage;
import com.service.microservice.auth.request.AccountRegister;
import com.service.microservice.auth.request.LoginAccount;
import com.service.microservice.auth.response.AuthGenJwtResponse;
import com.service.microservice.auth.response.AuthResponse;
import org.springframework.stereotype.Component;

@Component
public interface AuthService {

    BasePage<?> search(Integer pageNo, Integer pageSize) ;

    AuthGenJwtResponse login(LoginAccount loginAccount);

    AuthGenJwtResponse refreshToken(String refreshToken);

    AuthResponse add(AccountRegister register);
}
