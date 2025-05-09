package edu.imepac.javaperformancetester.controllers;

import edu.imepac.javaperformancetester.models.Vagao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vagao")
public class VagaoController {

    @PostMapping
    public ResponseEntity<Vagao> criar(@RequestBody Vagao vagao){

    }
}
