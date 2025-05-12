package edu.imepac.kotlinperformancetester.models

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class Encoste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id : Long? = null
    @ManyToMany(fetch = FetchType.EAGER)
    private var vagoes: MutableList<Vagao> = mutableListOf()

}