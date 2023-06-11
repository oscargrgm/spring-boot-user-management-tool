package net.javaguides.springboot.controller.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
data class UserDTO(
    val id: Long,
    @NotEmpty(message = "User's first name must not be null or empty")
    val firstName: String,
    @NotEmpty(message = "User's last name must not be null or empty")
    val lastName: String,
    @NotEmpty(message = "User's email must not be null or empty empty")
    @Email(message = "Email address must be valid")
    val email: String
)