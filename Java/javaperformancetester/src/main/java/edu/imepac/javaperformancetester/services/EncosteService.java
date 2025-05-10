package edu.imepac.javaperformancetester.services;

import edu.imepac.javaperformancetester.models.Encoste;
import edu.imepac.javaperformancetester.repositories.EncosteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncosteService {

    @Autowired
    private EncosteRepository encosteRepository;

    public List<Encoste> salvarTodos(List<Encoste> encostes){
        return encosteRepository.saveAll(encostes);
    }

    public List<Encoste> findAll(){
        return encosteRepository.findAll();
    }

    public Encoste create(Encoste encoste){
        return encosteRepository.save(encoste);
    }
}
