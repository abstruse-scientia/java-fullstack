package com.scientia.mystore.service.impl;


import com.scientia.mystore.dto.ContactRequestDto;
import com.scientia.mystore.entity.Contact;
import com.scientia.mystore.repository.ContactRepository;

import com.scientia.mystore.service.IContactService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements IContactService {
    private final ContactRepository contactRepository;


    @Override
    public boolean saveContact(ContactRequestDto contactRequestDto) {
        try{
            Contact contact = transformToEntity(contactRequestDto);
            contact.setCreatedAt(Instant.now());
            contact.setCreatedBy(contactRequestDto.getName());
            contactRepository.save(contact);
            return true;
        }catch (Exception exception) {
            return false;
        }
    }

    private Contact transformToEntity(ContactRequestDto contactRequestDto) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactRequestDto, contact);
        return contact;
    }
}
