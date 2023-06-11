package net.javaguides.springboot.exception.model

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
data class EmailAlreadyExistsException(override val message: String) : RuntimeException(message)
