package com.scientia.mystore.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ContactRequestDto {

    private String name;
    private String email;
    private String mobileNumber;
    private String message;
}
