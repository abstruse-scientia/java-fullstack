package com.scientia.mystore.repository;

import com.scientia.mystore.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByStatus(String status);
}
