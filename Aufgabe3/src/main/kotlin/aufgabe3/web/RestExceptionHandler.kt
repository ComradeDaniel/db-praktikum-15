package aufgabe3.web

import aufgabe3.api.NotYetImplementedException
import aufgabe3.hibernate.entity.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestExceptionHandler {
    @ExceptionHandler(NotYetImplementedException::class)
    fun notYetImplemented(ex: NotYetImplementedException): ResponseEntity<Map<String, String>> =
        ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
            .body(mapOf("error" to (ex.message ?: "not implemented")))

    @ExceptionHandler(NotFoundException::class)
    fun notFound(ex: NotFoundException): ResponseEntity<Map<String, String>> =
        ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(mapOf("error" to (ex.message ?: "not found")))

    @ExceptionHandler(IllegalArgumentException::class)
    fun badRequest(ex: IllegalArgumentException): ResponseEntity<Map<String, String>> =
        ResponseEntity.badRequest().body(mapOf("error" to (ex.message ?: "bad request")))
}
