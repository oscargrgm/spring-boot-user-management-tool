package net.javaguides.springboot.exception

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.time.LocalDateTime

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
data class ErrorDetails(
    val timestamp: LocalDateTime,
    val message: String,
    val path: String,
    val errorCode: String
)
