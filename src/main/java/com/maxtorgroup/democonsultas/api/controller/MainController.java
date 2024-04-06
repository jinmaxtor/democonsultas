package com.maxtorgroup.democonsultas.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {
    @GetMapping
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Bienvenido a la API de DemoConsultas!");
    }
}
