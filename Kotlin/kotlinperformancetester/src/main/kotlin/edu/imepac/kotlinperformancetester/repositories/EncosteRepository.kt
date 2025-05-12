package edu.imepac.kotlinperformancetester.repositories

import edu.imepac.kotlinperformancetester.models.Encoste
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EncosteRepository : JpaRepository<Encoste, Long> {
}