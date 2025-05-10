package edu.imepac.kotlinperformancetester.controllers

import edu.imepac.kotlinperformancetester.models.Vagao
import edu.imepac.kotlinperformancetester.services.VagaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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

    @PostMapping("/salvaremlote")
    fun salvarTodos(@RequestBody vagoes: List<Vagao>): ResponseEntity<List<Vagao>> {
        vagaoService.salvarTodos(vagoes)
        return ResponseEntity.status(HttpStatus.CREATED).body(vagoes)
    }

    @GetMapping
    fun listarTodos(): ResponseEntity<List<Vagao>> {
        val vagoes = vagaoService.findAll()
        return ResponseEntity.status(HttpStatus.OK).body(vagoes)
    }

}