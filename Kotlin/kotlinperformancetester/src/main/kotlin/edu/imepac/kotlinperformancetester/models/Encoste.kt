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
    @JoinTable(
        name = "encoste_vagao",
        joinColumns = [JoinColumn(name = "encoste_id")],
        inverseJoinColumns = [JoinColumn(name = "vagao_id")]
    )
    val vagoes: List<Vagao> = mutableListOf()

}