package net.javaguides.springboot.exception

import net.javaguides.springboot.exception.model.EmailAlreadyExistsException
import net.javaguides.springboot.exception.model.ResourceNotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

private const val ERROR_CODE_USER_NOT_FOUND = "USER_NOT_FOUND"
private const val ERROR_CODE_EMAIL_ALREADY_EXISTS = "EMAIL_ALREADY_EXISTS"
private const val ERROR_CODE_INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR"

@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(
        exception: ResourceNotFoundException,
        request: WebRequest
    ): ResponseEntity<ErrorDetails> {
        val errorDetails = ErrorDetails(
            timestamp = LocalDateTime.now(),
            message = exception.message.orEmpty(),
            path = request.getDescription(false),
            errorCode = ERROR_CODE_USER_NOT_FOUND
        )

        return ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(EmailAlreadyExistsException::class)
    fun handleEmailAlreadyExistsException(
        exception: EmailAlreadyExistsException,
        request: WebRequest
    ): ResponseEntity<ErrorDetails> {
        val errorDetails = ErrorDetails(
            timestamp = LocalDateTime.now(),
            message = exception.message,
            path = request.getDescription(false),
            errorCode = ERROR_CODE_EMAIL_ALREADY_EXISTS
        )

        return ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleGlobalException(
        exception: Exception,
        request: WebRequest
    ): ResponseEntity<ErrorDetails> {
        val errorDetails = ErrorDetails(
            timestamp = LocalDateTime.now(),
            message = exception.message.orEmpty(),
            path = request.getDescription(false),
            errorCode = ERROR_CODE_INTERNAL_SERVER_ERROR
        )

        return ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    override fun handleMethodArgumentNotValid(
        exception: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errors: HashMap<String, String> = hashMapOf()

        exception.bindingResult.allErrors.forEach { objectError ->
            val fieldName = (objectError as FieldError).field
            val message = objectError.defaultMessage.orEmpty()

            errors[fieldName] = message
        }

        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }
}
