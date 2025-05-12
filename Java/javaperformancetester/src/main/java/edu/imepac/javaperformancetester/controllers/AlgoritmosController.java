package edu.imepac.javaperformancetester.controllers;

import edu.imepac.javaperformancetester.dtos.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/algoritmos")
public class AlgoritmosController {

    @GetMapping("/fatorial/{fatorando}")
    public ResponseEntity<ResponseDto<BigInteger>> fatorialRecursivo(@PathVariable int fatorando) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        boolean cpuTimeSupported = threadMXBean.isCurrentThreadCpuTimeSupported();
        long cpuStart = cpuTimeSupported ? threadMXBean.getCurrentThreadCpuTime() : 0;

        BigInteger resultado = fatorial(fatorando);

        long cpuEnd = cpuTimeSupported ? threadMXBean.getCurrentThreadCpuTime() : 0;
        long cpuTimeMs = cpuTimeSupported ? (cpuEnd - cpuStart) / 1_000_000 : -1;

        ResponseDto<BigInteger> resposta = new ResponseDto<>(cpuTimeMs, resultado);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

    @GetMapping("/primos/{limite}")
    public ResponseEntity<ResponseDto<List<Integer>>> primos(@PathVariable int limite) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        boolean cpuTimeSupported = threadMXBean.isCurrentThreadCpuTimeSupported();
        long cpuStart = cpuTimeSupported ? threadMXBean.getCurrentThreadCpuTime() : 0;

        List<Integer> resultado = calcularPrimos(limite);

        long cpuEnd = cpuTimeSupported ? threadMXBean.getCurrentThreadCpuTime() : 0;
        long cpuTimeMs = cpuTimeSupported ? (cpuEnd - cpuStart) / 1_000_000 : -1;

        ResponseDto<List<Integer>> resposta = new ResponseDto<>(cpuTimeMs, resultado);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }


    public static BigInteger fatorial(int n) {
        if (n <= 1) return BigInteger.ONE;
        return BigInteger.valueOf(n).multiply(fatorial(n-1));
    }

    public List<Integer> calcularPrimos(int limite) {
        boolean[] ehPrimo = new boolean[limite + 1];
        Arrays.fill(ehPrimo, true);
        ehPrimo[0] = ehPrimo[1] = false;

        for (int i = 2; i * i <= limite; i++) {
            if (ehPrimo[i]) {
                for (int j = i * i; j <= limite; j += i) {
                    ehPrimo[j] = false;
                }
            }
        }

        List<Integer> primos = new ArrayList<>();
        for (int i = 2; i <= limite; i++) {
            if (ehPrimo[i]) primos.add(i);
        }

        return primos;
    }

}
