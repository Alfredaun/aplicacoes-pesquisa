package edu.imepac.kotlinperformancetester.controllers

import edu.imepac.kotlinperformancetester.dtos.ResponseDto
import edu.imepac.kotlinperformancetester.models.Vagao
import edu.imepac.kotlinperformancetester.services.VagaoService
import io.micrometer.core.annotation.Timed
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.Management
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.management.ManagementFactory
import java.lang.management.ThreadMXBean

@RestController
@RequestMapping("/vagao")
class VagaoController {
    @Autowired
    private lateinit var vagaoService: VagaoService

    @PostMapping
    fun criar(@RequestBody vagao: Vagao): ResponseEntity<Vagao> {
        val vagaoSalvo = vagaoService.create(vagao)
        return ResponseEntity.status(HttpStatus.CREATED).body(vagaoSalvo)
    }

    @Timed(value="api.vagao.insercao", description = "Tempo de inserção do vagao")
    @PostMapping("/salvaremlote")
    fun salvarTodos(@RequestBody vagoes: List<Vagao>): ResponseEntity<List<Vagao>> {
        val threadMXBean: ThreadMXBean = ManagementFactory.getThreadMXBean()
        val cpuTimeSupported: Boolean = threadMXBean.isThreadCpuTimeSupported

        val cpuStart = if (cpuTimeSupported) threadMXBean.currentThreadCpuTime else 0L


        val salvos: List<Vagao> = vagaoService.salvarTodos(vagoes)

        val cpuEnd = if (cpuTimeSupported) threadMXBean.currentThreadCpuTime else 0L
        val cpuTimeMs = if (cpuTimeSupported) (cpuEnd - cpuStart) / 1_000_000 else -1L

        val resposta = ResponseDto(cpuTimeMs, salvos)
        return ResponseEntity.status(HttpStatus.CREATED).body(vagoes)
    }

    @Timed(value = "api.vagao.listagem", description = "Tempo de listagem do vagao")
    @GetMapping
    fun listarTodos(): ResponseEntity<List<Vagao>> {
        val threadMXBean: ThreadMXBean = ManagementFactory.getThreadMXBean()
        val cpuTimeSupported: Boolean = threadMXBean.isThreadCpuTimeSupported

        val cpuStart = if (cpuTimeSupported) threadMXBean.currentThreadCpuTime else 0L

        val vagoes = vagaoService.findAll()
        val cpuEnd = if (cpuTimeSupported) threadMXBean.currentThreadCpuTime else 0L
        val cpuTimeMs = if (cpuTimeSupported) (cpuEnd - cpuStart) / 1_000_000 else -1L
        return ResponseEntity.status(HttpStatus.OK).body(vagoes)
    }

}