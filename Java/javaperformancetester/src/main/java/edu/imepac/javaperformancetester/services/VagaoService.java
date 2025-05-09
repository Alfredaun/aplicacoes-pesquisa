package edu.imepac.javaperformancetester.services;

import edu.imepac.javaperformancetester.models.Vagao;
import edu.imepac.javaperformancetester.repositories.VagaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagaoService {

    @Autowired
    private VagaoRepository vagaoRepository;

    public List<Vagao> findAll(){
        return vagaoRepository.findAll();
    }

    @Transactional
    public List<Vagao> salvarTodos(List<Vagao> vagoes){
        return vagaoRepository.saveAll(vagoes);
    }
    public Vagao create(Vagao vagao){
        return vagaoRepository.save(vagao);
    }

}
