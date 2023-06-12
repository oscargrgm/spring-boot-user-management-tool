package net.javaguides.springboot.controller.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Schema(description = "User DTO model information")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
data class UserDTO(

    val id: Long,

    @Schema(description = "User's first name")
    @NotEmpty(message = "User's first name must not be null or empty")
    val firstName: String,

    @Schema(description = "User's last name")
    @NotEmpty(message = "User's last name must not be null or empty")
    val lastName: String,

    @Schema(description = "User's email address")
    @NotEmpty(message = "User's email must not be null or empty empty")
    @Email(message = "Email address must be valid")
    val email: String
)