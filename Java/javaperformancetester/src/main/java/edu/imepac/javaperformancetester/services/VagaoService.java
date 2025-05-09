package edu.imepac.javaperformancetester.services;

import edu.imepac.javaperformancetester.models.Vagao;
import edu.imepac.javaperformancetester.repositories.VagaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class VagaoService {

    @Autowired
    private VagaoRepository vagaoRepository;

    @GetMapping
    public List<Vagao> findAll(){
        return vagaoRepository.findAll();
    }

    @PostMapping
    public Vagao create(@RequestBody Vagao vagao){
        return vagaoRepository.save(vagao);
    }

}
