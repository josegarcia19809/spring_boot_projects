package com.portfolio.my_portfolio_backend.rest;

import com.portfolio.my_portfolio_backend.model.PersonalInfo;
import com.portfolio.my_portfolio_backend.service.IPersonalInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test-personal-info")
public class PersonalInfoTestController {

    private final IPersonalInfoService personalInfoService;

    public PersonalInfoTestController(IPersonalInfoService personalInfoService) {
        this.personalInfoService = personalInfoService;
    }

    @GetMapping("/all")
    public List<PersonalInfo> getAllPersonalInfo() {
        return personalInfoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<PersonalInfo> getPersonalInfoById(@PathVariable Long id) {
        Optional<PersonalInfo> personalInfo = personalInfoService.findById(id);
        if (personalInfo.isPresent()) {
            return Optional.of(personalInfo.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Personal Info no disponible del id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<PersonalInfo> save(@RequestBody PersonalInfo personalInfo) {
        PersonalInfo savedPersonalInfo = personalInfoService.save(personalInfo);
        return new ResponseEntity<>(savedPersonalInfo, HttpStatus.CREATED);
    }
}
