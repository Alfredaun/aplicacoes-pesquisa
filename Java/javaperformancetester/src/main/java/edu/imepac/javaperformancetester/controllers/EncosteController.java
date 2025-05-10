package edu.imepac.javaperformancetester.controllers;

import edu.imepac.javaperformancetester.dtos.ResponseDto;
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

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
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
    public ResponseEntity<ResponseDto<List<Encoste>>> salvartodos(@RequestBody List<Encoste> vagoes){

        //inicio de medição de usagem de cpu
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        boolean cpuTimeSupported = threadMXBean.isCurrentThreadCpuTimeSupported();

        long cpuStart = cpuTimeSupported ? threadMXBean.getCurrentThreadCpuTime() : 0;

        //normal do controller
        List<Encoste> salvos = encosteService.salvarTodos(vagoes);

        //fim de medição de uso de cpu
        long cpuEnd = cpuTimeSupported ? threadMXBean.getCurrentThreadCpuTime() : 0;
        long cpuTimeMs = cpuTimeSupported ? (cpuEnd - cpuStart) / 1_000_000 : -1;

        //preparação pra retornar o valor
        ResponseDto<List<Encoste>> resposta = new ResponseDto<>(cpuTimeMs,salvos);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @Timed(value = "api.encoste.listagem", description = "Tempo de listagem do encoste")
    @GetMapping
    public ResponseEntity<ResponseDto<List<Encoste>>> listarTodos(){

        //inicio de medição de usagem de cpu
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        boolean cpuTimeSupported = threadMXBean.isCurrentThreadCpuTimeSupported();

        long cpuStart = cpuTimeSupported ? threadMXBean.getCurrentThreadCpuTime() : 0;

        //normal do controller
        List<Encoste> encostes = encosteService.findAll();

        //fim de medição de uso de cpu
        long cpuEnd = cpuTimeSupported ? threadMXBean.getCurrentThreadCpuTime() : 0;
        long cpuTimeMs = cpuTimeSupported ? (cpuEnd - cpuStart) / 1_000_000 : -1;

        //preparação pra retornar o valor
        ResponseDto<List<Encoste>> resposta = new ResponseDto<>(cpuTimeMs,encostes);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }
}
