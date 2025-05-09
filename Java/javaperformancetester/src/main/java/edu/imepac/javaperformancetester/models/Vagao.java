package edu.imepac.javaperformancetester.models;

import edu.imepac.javaperformancetester.enums.Carga;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Long id;

    private String placa;
    private Carga carga;//soja,SPC,milho,ureia,acucar
    private Long tara;
    private Long pesoBruto;
    private Carga ultimaCarga;//soja,SPC,milho,ureia,acucar
}
