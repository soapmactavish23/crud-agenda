package com.example.agendaapi.service;

import com.example.agendaapi.exception.NotFoundException;
import com.example.agendaapi.model.Contact;
import com.example.agendaapi.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository repository;

    @Transactional
    public Contact create(Contact obj) {
        return repository.save(obj);
    }

    @Transactional
    public Contact update(Contact obj) {
        Contact contactSaved = findById(obj.getId());

        BeanUtils.copyProperties(obj, contactSaved, "id");

        return repository.save(contactSaved);
    }

    public Contact findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Contato não encontrado"));
    }

    @Transactional(readOnly = true)
    public List<Contact> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void delete(Integer id) {
        Contact contact = findById(id);
        repository.deleteById(contact.getId());
    }

}
