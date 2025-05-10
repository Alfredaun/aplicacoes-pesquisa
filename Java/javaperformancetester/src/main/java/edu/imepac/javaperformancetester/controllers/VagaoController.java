package edu.imepac.javaperformancetester.controllers;

import edu.imepac.javaperformancetester.dtos.ResponseDto;
import edu.imepac.javaperformancetester.models.Vagao;
import edu.imepac.javaperformancetester.services.VagaoService;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
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
    public ResponseEntity<ResponseDto<List<Vagao>>> salvartodos(@RequestBody List<Vagao> vagoes){

        //inicio de medição de usagem de cpu
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        boolean cpuTimeSupported = threadMXBean.isCurrentThreadCpuTimeSupported();

        long cpuStart = cpuTimeSupported ? threadMXBean.getCurrentThreadCpuTime() : 0;

        //normal do controller
        List<Vagao> salvos = vagaoService.salvarTodos(vagoes);

        //fim de medição de uso de cpu
        long cpuEnd = cpuTimeSupported ? threadMXBean.getCurrentThreadCpuTime() : 0;
        long cpuTimeMs = cpuTimeSupported ? (cpuEnd - cpuStart) / 1_000_000 : -1;

        //preparação pra retornar o valor
        ResponseDto<List<Vagao>> resposta = new ResponseDto<>(cpuTimeMs, salvos);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);



    }

    @Timed(value = "api.vagao.listagem", description = "Tempo de listagem do vagao")
    @GetMapping
    public ResponseEntity<ResponseDto<List<Vagao>>> listarTodos(){

        //inicio de medição de usagem de cpu
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        boolean cpuTimeSupported = threadMXBean.isCurrentThreadCpuTimeSupported();

        long cpuStart = cpuTimeSupported ? threadMXBean.getCurrentThreadCpuTime() : 0;

        //normal do controller
        List<Vagao> vagoes = vagaoService.findAll();

        //fim de medição de uso de cpu
        long cpuEnd = cpuTimeSupported ? threadMXBean.getCurrentThreadCpuTime() : 0;
        long cpuTimeMs = cpuTimeSupported ? (cpuEnd - cpuStart) / 1_000_000 : -1;

        ResponseDto<List<Vagao>> resposta = new ResponseDto<>(cpuTimeMs, vagoes);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }
}
