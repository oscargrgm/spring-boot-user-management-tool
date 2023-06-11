package net.javaguides.springboot.controller

import jakarta.validation.Valid
import net.javaguides.springboot.controller.dto.UserDTO
import net.javaguides.springboot.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/users")
class UserController(
    private val userService: UserService
) {

    @PostMapping
    fun createUser(
        @Valid @RequestBody user: UserDTO
    ): ResponseEntity<UserDTO> {
        val createdUser = userService.createUser(user)
        return ResponseEntity(createdUser, HttpStatus.CREATED)
    }

    // http://localhost:8080/api/users/1
    @GetMapping("/{userId}")
    fun getUserById(
        @PathVariable("userId") userId: Long
    ): ResponseEntity<UserDTO> {
        val user = userService.getUserById(userId)
        return ResponseEntity(user, HttpStatus.OK)
    }

    // http://localhost:8080/api/users
    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserDTO>> {
        val users = userService.getAllUsers()
        return ResponseEntity(users, HttpStatus.OK)
    }

    // http://localhost:8080/api/users/update
    @PutMapping("/update")
    fun updateUser(
        @Valid @RequestBody user: UserDTO
    ): ResponseEntity<UserDTO> {
        val updatedUser = userService.updateUser(user)
        return ResponseEntity(updatedUser, HttpStatus.OK)
    }

    // http://localhost:8080/api/users/delete/1
    @DeleteMapping("/delete/{userId}")
    fun deleteUser(
        @PathVariable("userId") userId: Long
    ): ResponseEntity<String> {
        userService.deleteUser(userId)
        return ResponseEntity("User successfully deleted", HttpStatus.OK)
    }
}
