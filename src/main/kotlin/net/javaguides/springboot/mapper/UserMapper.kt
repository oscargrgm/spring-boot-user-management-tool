package net.javaguides.springboot.mapper

import net.javaguides.springboot.controller.dto.UserDTO
import net.javaguides.springboot.entity.User

fun UserDTO.toJPAModel(): User = User(id, firstName, lastName, email)

fun User.toDTOModel(): UserDTO = UserDTO(id, firstName, lastName, email)