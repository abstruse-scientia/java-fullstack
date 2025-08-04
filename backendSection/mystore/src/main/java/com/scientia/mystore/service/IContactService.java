package com.scientia.mystore.service;

import com.scientia.mystore.dto.ContactRequestDto;

public interface IContactService {
    boolean saveContact(ContactRequestDto contactRequestDto);
}
