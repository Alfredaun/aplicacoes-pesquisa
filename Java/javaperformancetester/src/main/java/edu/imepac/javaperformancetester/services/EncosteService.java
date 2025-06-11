package edu.imepac.javaperformancetester.services;

import edu.imepac.javaperformancetester.models.Encoste;
import edu.imepac.javaperformancetester.models.Vagao;
import edu.imepac.javaperformancetester.repositories.EncosteRepository;
import edu.imepac.javaperformancetester.repositories.VagaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EncosteService {

    @Autowired
    private EncosteRepository encosteRepository;

    @Autowired
    private VagaoRepository vagaoRepository;

    @Transactional
    public List<Encoste> salvarTodos(List<Encoste> encostes){

        List<Encoste> salvos= encosteRepository.saveAll(encostes);

        for(Encoste encoste : salvos){
            for(Vagao vagao : encoste.getVagoes()){
                vagao = vagaoRepository.findById(vagao.getId()).orElse(null);
            }
        }
        return encosteRepository.saveAll(encostes);
    }

    public List<Encoste> findAll(){
        return encosteRepository.findAll();
    }

    public Encoste create(Encoste encoste){
        return encosteRepository.save(encoste);
    }
}
