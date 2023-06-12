package net.javaguides.springboot.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import net.javaguides.springboot.controller.dto.UserDTO
import net.javaguides.springboot.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(
    name = "CRUD REST API for User Resource",
    description = "CRUD REST API - Create User, Update User, Get User, Get All Users, Delete Users"
)
@RestController
@RequestMapping("api/users")
class UserController(
    private val userService: UserService
) {

    @Operation(
        summary = "Create User REST API",
        description = "Create User REST API is used to save User in DB"
    )
    @ApiResponse(
        responseCode = "201",
        description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    fun createUser(
        @Valid @RequestBody user: UserDTO
    ): ResponseEntity<UserDTO> {
        val createdUser = userService.createUser(user)
        return ResponseEntity(createdUser, HttpStatus.CREATED)
    }

    @Operation(
        summary = "Get User by ID REST API",
        description = "Get User REST API is used to fetch a single User from DB"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("/{userId}")
    fun getUserById(
        @PathVariable("userId") userId: Long
    ): ResponseEntity<UserDTO> {
        val user = userService.getUserById(userId)
        return ResponseEntity(user, HttpStatus.OK)
    }

    @Operation(
        summary = "Get All User REST API",
        description = "Get All Users REST API is used to fetch all Users from DB"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserDTO>> {
        val users = userService.getAllUsers()
        return ResponseEntity(users, HttpStatus.OK)
    }

    @Operation(
        summary = "Update User REST API",
        description = "Update User REST API is used to update a single User from DB"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("/update")
    fun updateUser(
        @Valid @RequestBody user: UserDTO
    ): ResponseEntity<UserDTO> {
        val updatedUser = userService.updateUser(user)
        return ResponseEntity(updatedUser, HttpStatus.OK)
    }

    @Operation(
        summary = "Delete User REST API",
        description = "Delete User REST API is used to delete a single User from DB"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("/delete/{userId}")
    fun deleteUser(
        @PathVariable("userId") userId: Long
    ): ResponseEntity<String> {
        userService.deleteUser(userId)
        return ResponseEntity("User successfully deleted", HttpStatus.OK)
    }
}
