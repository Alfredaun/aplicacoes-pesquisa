package edu.imepac.javaperformancetester.controllers

import edu.imepac.javaperformancetester.dtos.ResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.management.ManagementFactory
import java.lang.management.ThreadMXBean
import java.math.BigInteger

@RestController
@RequestMapping("/algoritmos")
class AlgoritmosController {

    @GetMapping("/fatorial/{fatorando}")
    fun fatorialRecursivo(@PathVariable fatorando: Int): ResponseEntity<ResponseDto<BigInteger>> {
        val threadMXBean: ThreadMXBean = ManagementFactory.getThreadMXBean()
        val cpuTimeSupported = threadMXBean.isCurrentThreadCpuTimeSupported
        val cpuStart = if (cpuTimeSupported) threadMXBean.currentThreadCpuTime else 0

        val resultado = fatorial(fatorando)

        val cpuEnd = if (cpuTimeSupported) threadMXBean.currentThreadCpuTime else 0
        val cpuTimeMs = if (cpuTimeSupported) (cpuEnd - cpuStart) / 1_000_000 else -1

        val resposta = ResponseDto(cpuTimeMs, resultado)
        return ResponseEntity.status(HttpStatus.OK).body(resposta)
    }

    @GetMapping("/primos/{limite}")
    fun primos(@PathVariable limite: Int): ResponseEntity<ResponseDto<List<Int>>> {
        val threadMXBean: ThreadMXBean = ManagementFactory.getThreadMXBean()
        val cpuTimeSupported = threadMXBean.isCurrentThreadCpuTimeSupported
        val cpuStart = if (cpuTimeSupported) threadMXBean.currentThreadCpuTime else 0

        val resultado = calcularPrimos(limite)

        val cpuEnd = if (cpuTimeSupported) threadMXBean.currentThreadCpuTime else 0
        val cpuTimeMs = if (cpuTimeSupported) (cpuEnd - cpuStart) / 1_000_000 else -1

        val resposta = ResponseDto(cpuTimeMs, resultado)
        return ResponseEntity.status(HttpStatus.OK).body(resposta)
    }

    fun fatorial(n: Int): BigInteger {
        return if (n <= 1) BigInteger.ONE else BigInteger.valueOf(n.toLong()).multiply(fatorial(n - 1))
    }

    fun calcularPrimos(limite: Int): List<Int> {
        val ehPrimo = BooleanArray(limite + 1) { true }
        if (limite >= 0) ehPrimo[0] = false
        if (limite >= 1) ehPrimo[1] = false

        for (i in 2..Math.sqrt(limite.toDouble()).toInt()) {
            if (ehPrimo[i]) {
                for (j in i * i..limite step i) {
                    ehPrimo[j] = false
                }
            }
        }

        return (2..limite).filter { ehPrimo[it] }
    }
}
