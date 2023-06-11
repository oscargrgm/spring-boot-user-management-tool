package net.javaguides.springboot.service

import net.javaguides.springboot.controller.dto.UserDTO

interface UserService {
    fun createUser(userDto: UserDTO): UserDTO
    fun getUserById(userId: Long): UserDTO
    fun getAllUsers(): List<UserDTO>
    fun updateUser(userDto: UserDTO): UserDTO
    fun deleteUser(userId: Long)
}