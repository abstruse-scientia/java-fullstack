package com.scientia.mystore.service;


import com.scientia.mystore.dto.ProfileRequestDto;
import com.scientia.mystore.dto.ProfileResponseDto;

public interface IProfileService {
    ProfileResponseDto getProfile();

    ProfileResponseDto updateProfile(ProfileRequestDto profileRequestDto);
}
