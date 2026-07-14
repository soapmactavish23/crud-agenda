package com.example.agendaapi.controller;

import com.example.agendaapi.model.Contact;
import com.example.agendaapi.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contact")
public class ContactController {

    private final ContactService service;

    @PostMapping
    public ResponseEntity<Contact> create(@RequestBody @Valid Contact request) {
        Contact response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<Contact> update(@RequestBody @Valid Contact request) {
        Contact response = service.update(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> findById(@PathVariable Integer id) {
        Contact response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public List<Contact> findAll() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

}
