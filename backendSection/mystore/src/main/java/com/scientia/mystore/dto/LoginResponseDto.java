package com.scientia.mystore.dto;

public record LoginResponseDto(String message, UserDto user, String jwtToken) {
}
