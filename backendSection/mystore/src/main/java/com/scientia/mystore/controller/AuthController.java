package com.scientia.mystore.controller;


import com.scientia.mystore.dto.LoginRequestDto;
import com.scientia.mystore.dto.LoginResponseDto;
import com.scientia.mystore.dto.RegisterRequestDto;
import com.scientia.mystore.dto.UserDto;
import com.scientia.mystore.entity.Customer;
import com.scientia.mystore.entity.Role;
import com.scientia.mystore.repository.CustomerRepository;
import com.scientia.mystore.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.synth.Region;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;
    private final CompromisedPasswordChecker compromisedPasswordChecker;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> apiLogin(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.username(),
                    loginRequestDto.password()));
            String jwtToken = jwtUtil.generateJwtToken(authentication);
            var userDto = new UserDto();
            var loggedInUser = (Customer) authentication.getPrincipal();
            BeanUtils.copyProperties(loggedInUser, userDto);
            userDto.setRoles(authentication.getAuthorities().stream().map(
                    GrantedAuthority::getAuthority).collect(Collectors.joining(",")));
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
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        CompromisedPasswordDecision decision = compromisedPasswordChecker.check(registerRequestDto.getPassword());
        if (decision.isCompromised()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("password", "Choose a strong password"));
        }
        Optional<Customer> existingCustomer = customerRepository.findByEmailOrMobileNumber(
                registerRequestDto.getEmail(), registerRequestDto.getMobileNumber()
        );

        if(existingCustomer.isPresent()) {
            Map<String, String> errors = new HashMap<>();
            Customer customer = existingCustomer.get();
            if(customer.getEmail().equalsIgnoreCase(registerRequestDto.getEmail())) {
                errors.put("email", "Email already exists");
            }
            if(customer.getMobileNumber().equalsIgnoreCase(registerRequestDto.getMobileNumber())) {
                errors.put("mobileNumber", "Mobile number already exists");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(registerRequestDto, customer);
        customer.setPasswordHash(passwordEncoder.encode(registerRequestDto.getPassword()));
        Role role = new Role();
        role.setName("ROLE_USER");
        customer.setRoles(Set.of(role));
        customerRepository.save(customer);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Registration successful");
    }

    private ResponseEntity<LoginResponseDto> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new LoginResponseDto(message, null, null));
    }

}