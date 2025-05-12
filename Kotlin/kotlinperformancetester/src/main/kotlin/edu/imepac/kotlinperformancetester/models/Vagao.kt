package edu.imepac.kotlinperformancetester.models

import edu.imepac.kotlinperformancetester.enums.Carga
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class Vagao {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    var id: Long? = null
    var placa: String? = null
    var carga: Carga? = null // soja,SPC,milho,ureia,acucar
    var tara: Long? = null
    var pesoBruto: Long? = null
    var ultimaCarga: Carga? = null // soja,SPC,milho,ureia,acucar


    /*
    *  private String placa;
    private Carga carga;//soja,SPC,milho,ureia,acucar
    private Long tara;
    private Long pesoBruto;
    private Carga ultimaCarga;//soja,SPC,milho,ureia,acucar
    * */
}