package edu.imepac.kotlinperformancetester.dtos

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
data class ResponseDto<T>(
    val cpuTimeMs: Long,
    val data: T
){
}
