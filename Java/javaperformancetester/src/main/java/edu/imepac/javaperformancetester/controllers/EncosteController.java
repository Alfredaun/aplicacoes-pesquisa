package edu.imepac.javaperformancetester.controllers;

import edu.imepac.javaperformancetester.models.Encoste;
import edu.imepac.javaperformancetester.models.Encoste;
import edu.imepac.javaperformancetester.repositories.EncosteRepository;
import edu.imepac.javaperformancetester.services.EncosteService;
import edu.imepac.javaperformancetester.services.EncosteService;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/encoste")
public class EncosteController {

    @Autowired
    private EncosteService encosteService;

    @PostMapping
    public ResponseEntity<Encoste> criar(@RequestBody Encoste encoste){
        Encoste salvo = encosteService.create(encoste);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @Timed(value = "api.encoste.insercao", description = "Tempo de inserção do encoste")
    @PostMapping("/salvaremlote")
    public ResponseEntity<List<Encoste>> salvartodos(@RequestBody List<Encoste> vagoes){
        List<Encoste> salvos = encosteService.salvarTodos(vagoes);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvos);
    }

    @Timed(value = "api.encoste.listagem", description = "Tempo de listagem do encoste")
    @GetMapping
    public ResponseEntity<List<Encoste>> listarTodos(){
        List<Encoste> vagoes = encosteService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(vagoes);
    }
}
