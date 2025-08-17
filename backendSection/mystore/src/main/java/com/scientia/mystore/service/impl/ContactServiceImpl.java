package com.scientia.mystore.service.impl;


import com.scientia.mystore.constants.ApplicationConstant;
import com.scientia.mystore.dto.ContactRequestDto;
import com.scientia.mystore.dto.ContactResponseDto;
import com.scientia.mystore.entity.Contact;
import com.scientia.mystore.exception.ResourceNotFoundException;
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
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public List<ContactResponseDto> getAllOpenMessages() {
        List<Contact> contacts = contactRepository.findByStatus(ApplicationConstant.OPEN_MESSAGE);
        return contacts.stream().map(this::mapToContactResponseDTO).collect(Collectors.toList());
    }

    @Override
    public void updateMessageStatus(Long contactId, String status) {
        Contact contact = contactRepository.findById(contactId).orElseThrow(
                () -> new ResourceNotFoundException("Contact", "ContactID", contactId.toString())
        );
        contact.setStatus(status);
        contactRepository.save(contact);
    }

    private ContactResponseDto mapToContactResponseDTO(Contact contact) {
        ContactResponseDto responseDTO = new ContactResponseDto(
                contact.getContactId(),
                contact.getName(),
                contact.getEmail(),
                contact.getMobileNumber(),
                contact.getMessage(),
                contact.getStatus()
        );
        return responseDTO;
    }

    @Override
    public boolean saveContact(ContactRequestDto contactRequestDto) {
            Contact contact = transformToEntity(contactRequestDto);
            contactRepository.save(contact);
            return true;
    }

    private Contact transformToEntity(ContactRequestDto contactRequestDto) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactRequestDto, contact);
        contact.setStatus(ApplicationConstant.OPEN_MESSAGE);
        return contact;
    }


}
