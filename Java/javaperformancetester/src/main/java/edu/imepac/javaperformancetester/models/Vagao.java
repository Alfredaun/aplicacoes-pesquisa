package edu.imepac.javaperformancetester.models;

import edu.imepac.javaperformancetester.enums.Carga;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vagao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "placa")
    private String placa;

    @Column(name = "carga")
    private Carga carga;//soja,SPC,milho,ureia,acucar

    @Column(name = "tara")
    private Long tara;

    @Column(name = "peso_bruto")
    private Long pesoBruto;

    @Column(name = "ultima_carga")
    private Carga ultimaCarga;//soja,SPC,milho,ureia,acucar
}
