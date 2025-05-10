package edu.imepac.kotlinperformancetester.repositories

import edu.imepac.kotlinperformancetester.models.Vagao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VagaoRepository : JpaRepository<Vagao,Long>{
}