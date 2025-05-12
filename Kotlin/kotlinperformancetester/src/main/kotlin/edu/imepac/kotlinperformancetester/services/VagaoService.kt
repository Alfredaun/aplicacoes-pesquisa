package edu.imepac.kotlinperformancetester.services

import edu.imepac.kotlinperformancetester.models.Vagao
import edu.imepac.kotlinperformancetester.repositories.VagaoRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class VagaoService {
    @Autowired
    private lateinit var vagaoRepository: VagaoRepository

    fun findAll(): List<Vagao> {
        return vagaoRepository.findAll()
    }

    @Transactional
    fun salvarTodos(vagoes: List<Vagao>) : List<Vagao> {
        return vagaoRepository.saveAll(vagoes)
    }
    fun create(vagao: Vagao): Vagao {
        return vagaoRepository.save(vagao)
    }
}