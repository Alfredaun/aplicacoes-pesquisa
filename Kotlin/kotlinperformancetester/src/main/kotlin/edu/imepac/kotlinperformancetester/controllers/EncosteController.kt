package edu.imepac.kotlinperformancetester.controllers

import edu.imepac.kotlinperformancetester.dtos.ResponseDto
import edu.imepac.kotlinperformancetester.models.Encoste
import edu.imepac.kotlinperformancetester.models.Vagao
import edu.imepac.kotlinperformancetester.services.EncosteService
import io.micrometer.core.annotation.Timed
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.management.ManagementFactory
import java.lang.management.ThreadMXBean

@RestController
@RequestMapping("/encoste")
class EncosteController(
    @Autowired
    private val encosteService: EncosteService,
) {
    @PostMapping
    fun criar(@RequestBody encoste: Encoste): ResponseEntity<Encoste> {
        val encosteSalvo = encosteService.create(encoste)
        return ResponseEntity.status(HttpStatus.CREATED).body(encosteSalvo)
    }

    @Timed(value="api.encoste.insercao", description = "Tempo de inserção do encoste")
    @PostMapping("/salvaremlote")
    fun salvarTodos(@RequestBody encostes: List<Encoste>): ResponseEntity<ResponseDto<List<Encoste>>> {
        val threadMXBean: ThreadMXBean = ManagementFactory.getThreadMXBean()
        val cpuTimeSupported: Boolean = threadMXBean.isThreadCpuTimeSupported

        val cpuStart = if (cpuTimeSupported) threadMXBean.currentThreadCpuTime else 0L


        val salvos: List<Encoste> = encosteService.salvarTodos(encostes)

        val cpuEnd = if (cpuTimeSupported) threadMXBean.currentThreadCpuTime else 0L
        val cpuTimeMs = if (cpuTimeSupported) (cpuEnd - cpuStart) / 1_000_000 else -1L

        val resposta = ResponseDto(cpuTimeMs, salvos)
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta)
    }

    @Timed(value = "api.encoste.listagem", description = "Tempo de listagem do encoste")
    @GetMapping
    fun listarTodos(): ResponseEntity<ResponseDto<List<Encoste>>> {
        val threadMXBean: ThreadMXBean = ManagementFactory.getThreadMXBean()
        val cpuTimeSupported: Boolean = threadMXBean.isThreadCpuTimeSupported

        val cpuStart = if (cpuTimeSupported) threadMXBean.currentThreadCpuTime else 0L

        val encostes: List<Encoste> = encosteService.findAll()
        val cpuEnd = if (cpuTimeSupported) threadMXBean.currentThreadCpuTime else 0L
        val cpuTimeMs = if (cpuTimeSupported) (cpuEnd - cpuStart) / 1_000_000 else -1L
        val resposta = ResponseDto(cpuTimeMs, encostes)
        return ResponseEntity.status(HttpStatus.OK).body(resposta)
    }
}