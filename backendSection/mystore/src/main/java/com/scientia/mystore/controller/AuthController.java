package com.scientia.mystore.controller;


import com.scientia.mystore.dto.LoginRequestDto;
import com.scientia.mystore.dto.LoginResponseDto;
import com.scientia.mystore.dto.RegisterRequestDto;
import com.scientia.mystore.dto.UserDto;
import com.scientia.mystore.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.synth.Region;
import java.util.List;


@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> apiLogin(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.username(),
                    loginRequestDto.password()));
            String jwtToken = jwtUtil.generateJwtToken(authentication);
            var userDto = new UserDto();
            var loggedInUserDto = (User) authentication.getPrincipal();
            userDto.setName(loggedInUserDto.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDto(HttpStatus.OK.getReasonPhrase(),
                    userDto, jwtToken));
        } catch(BadCredentialsException ex) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid username and password");
        }catch(AuthenticationException ex) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Authentication Failed");
        }catch(Exception ex) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error has occurred");
        }


    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        inMemoryUserDetailsManager.createUser(new User(registerRequestDto.getEmail(),
                passwordEncoder.encode(registerRequestDto.getPassword()),
                List.of(new SimpleGrantedAuthority("USER"))));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Registration successful");
    }

    private ResponseEntity<LoginResponseDto> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new LoginResponseDto(message, null, null));
    }

}