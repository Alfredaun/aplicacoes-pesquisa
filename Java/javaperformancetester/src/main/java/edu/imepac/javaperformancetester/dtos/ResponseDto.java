package edu.imepac.javaperformancetester.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*ATENÇÃO!!!:
    Essa é a nova classe que os controllers devem retornar.
    Olhem no controller dos vagões como eu usei
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDto<T> {//<T> pra poder colocar qualquer tipo de dado nela.
    private long tempoCpuMs;
    private T dados;//aqui q vai ficar os dados de vagão e encoste (depende do controller sendo usando no momento)
}
