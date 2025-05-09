package edu.imepac.javaperformancetester.models;

import edu.imepac.javaperformancetester.enums.Carga;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vagao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;
    private Carga carga;
    private Long tara;
    private Long pesoBruto;
    private Long pesoLiquido;
    private Carga ultimaCarga;
}
