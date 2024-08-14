package com.employee.controller;

import com.employee.entity.secondary.NewEntity;
import com.employee.repository.secondary.NewEntityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/new-entity")
public class NewEntityController {

    @Autowired
    private NewEntityRepository newEntityRepository;

    @GetMapping
    public List<NewEntity> getAllNewEntities() {
        return newEntityRepository.findAll();
    }

    @PostMapping
    public NewEntity createNewEntity(@RequestBody NewEntity newEntity) {
        return newEntityRepository.save(newEntity);
    }

    @GetMapping("/{id}")
    public NewEntity getNewEntityById(@PathVariable Long id) {
        return newEntityRepository.findById(id).orElse(null);
    }
}
