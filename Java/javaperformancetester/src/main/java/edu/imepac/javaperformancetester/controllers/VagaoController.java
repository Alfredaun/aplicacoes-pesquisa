package edu.imepac.javaperformancetester.controllers;

import edu.imepac.javaperformancetester.models.Vagao;
import edu.imepac.javaperformancetester.services.VagaoService;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vagao")
public class VagaoController {

    @Autowired
    private VagaoService vagaoService;

    @PostMapping
    public ResponseEntity<Vagao> criar(@RequestBody Vagao vagao){
        Vagao salvo = vagaoService.create(vagao);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @Timed(value = "api.vagao.insercao", description = "Tempo de inserção do vagao")
    @PostMapping("/salvaremlote")
    public ResponseEntity<List<Vagao>> salvartodos(@RequestBody List<Vagao> vagoes){
        List<Vagao> salvos = vagaoService.salvarTodos(vagoes);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvos);
    }

    @Timed(value = "api.vagao.listagem", description = "Tempo de listagem do vagao")
    @GetMapping
    public ResponseEntity<List<Vagao>> listarTodos(){
        List<Vagao> vagoes = vagaoService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(vagoes);
    }
}
