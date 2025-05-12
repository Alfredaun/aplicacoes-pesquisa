package edu.imepac.kotlinperformancetester.services

import edu.imepac.kotlinperformancetester.models.Encoste
import edu.imepac.kotlinperformancetester.models.Vagao
import edu.imepac.kotlinperformancetester.repositories.EncosteRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EncosteService {

    @Autowired
    private lateinit var encosteRepository: EncosteRepository

    fun findAll(): List<Encoste> {
        return encosteRepository.findAll()
    }

    @Transactional
    fun salvarTodos(encostes: List<Encoste>): List<Encoste> {
        return encosteRepository.saveAll(encostes)
    }
    fun create(encoste: Encoste): Encoste {
        return encosteRepository.save(encoste)
    }
}