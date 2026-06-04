package com.codigo.msregisterhexagonal.application.controller;

import com.codigo.msregisterhexagonal.domain.aggregates.dto.PersonDTO;
import com.codigo.msregisterhexagonal.domain.ports.in.PersonServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/hexagonal/")
@RequiredArgsConstructor
public class PersonController {

    private final PersonServiceIn serviceIn;

    @PostMapping("/save")
    public ResponseEntity<PersonDTO> createPerson(@RequestParam("dni") String dni){
        return ResponseEntity.ok(serviceIn.createPersonIn(dni));
    }

    @GetMapping("/persons")
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        return ResponseEntity.ok(serviceIn.getAllPersonsIn());
    }

    @GetMapping("/persons/{dni}")
    public ResponseEntity<PersonDTO> getPersonByDni(@PathVariable String dni) {
        return ResponseEntity.ok(serviceIn.getPersonByDniIn(dni));
    }

    @DeleteMapping("/persons/{dni}")
    public ResponseEntity<Void> deletePersonByDni(@PathVariable String dni) {
        serviceIn.deletePersonByDniIn(dni);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
