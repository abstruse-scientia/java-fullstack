package com.scientia.mystore.service;

import com.scientia.mystore.dto.ContactRequestDto;
import com.scientia.mystore.dto.ContactResponseDto;

import java.util.List;

public interface IContactService {
    boolean saveContact(ContactRequestDto contactRequestDto);

    List<ContactResponseDto> getAllOpenMessages();

    void updateMessageStatus(Long contactId, String status);
}
