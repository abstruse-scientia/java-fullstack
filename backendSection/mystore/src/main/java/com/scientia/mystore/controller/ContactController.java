package com.scientia.mystore.controller;

import com.scientia.mystore.dto.ContactRequestDto;

import com.scientia.mystore.service.IContactService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/v1/contacts")
@RequiredArgsConstructor

public class ContactController {

    private final IContactService iContactService;


    @PostMapping
    public String saveContact(@RequestBody ContactRequestDto contactRequestDto) {
        boolean isSaved = iContactService.saveContact(contactRequestDto);
        if (isSaved) {
            return "Request processed successfully.";
        }
        else {
            return "An error occurred.Please try again or contact Dev team";
        }
    }
}
