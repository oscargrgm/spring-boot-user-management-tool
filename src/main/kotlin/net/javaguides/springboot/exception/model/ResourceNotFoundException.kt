package net.javaguides.springboot.exception.model

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
data class ResourceNotFoundException(
    val resourceName: String,
    val fieldName: String,
    val fieldValue: Long
) : RuntimeException(String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue))
