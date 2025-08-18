package com.scientia.mystore.controller;

import com.scientia.mystore.dto.ContactInfoDto;
import com.scientia.mystore.dto.ContactRequestDto;

import com.scientia.mystore.service.IContactService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/contacts")
@RequiredArgsConstructor

public class ContactController {

    private final IContactService iContactService;
    private final ContactInfoDto contactInfoDto;


    @PostMapping
    public ResponseEntity<String> saveContact(@Valid @RequestBody ContactRequestDto contactRequestDto) {
        iContactService.saveContact(contactRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Contact saved successfully");

    }

    @GetMapping
    public ResponseEntity<ContactInfoDto> getContactInfo() {
        return ResponseEntity.ok(contactInfoDto);
    }
}
