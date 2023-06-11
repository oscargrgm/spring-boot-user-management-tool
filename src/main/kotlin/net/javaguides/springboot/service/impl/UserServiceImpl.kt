package net.javaguides.springboot.service.impl

import net.javaguides.springboot.controller.dto.UserDTO
import net.javaguides.springboot.exception.model.EmailAlreadyExistsException
import net.javaguides.springboot.exception.model.ResourceNotFoundException
import net.javaguides.springboot.mapper.toDTOModel
import net.javaguides.springboot.mapper.toJPAModel
import net.javaguides.springboot.repository.UserRepository
import net.javaguides.springboot.service.UserService
import org.springframework.stereotype.Service

private const val RESOURCE_USER = "user"

private const val FIELD_ID = "id"

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    override fun createUser(userDto: UserDTO): UserDTO {
        userRepository
            .findByEmail(userDto.email)
            .ifPresent { throw EmailAlreadyExistsException("Email already exists for a user") }

        return userRepository.save(userDto.toJPAModel()).toDTOModel()
    }

    override fun getUserById(userId: Long): UserDTO =
        userRepository.findById(userId)
            .map { it.toDTOModel() }
            .orElseThrow { ResourceNotFoundException(RESOURCE_USER, FIELD_ID, userId) }

    override fun getAllUsers(): List<UserDTO> =
        userRepository.findAll()
            .sortedBy { user -> user.id }
            .map { it.toDTOModel() }

    @Throws(ResourceNotFoundException::class)
    override fun updateUser(userDto: UserDTO): UserDTO {
        val updatedUser = userRepository.findById(userDto.id)
            .map { it.copy(firstName = userDto.firstName, lastName = userDto.lastName, email = userDto.email) }
            .orElseThrow { ResourceNotFoundException(RESOURCE_USER, FIELD_ID, userDto.id) }

        return userRepository.save(updatedUser).toDTOModel()
    }

    @Throws(ResourceNotFoundException::class)
    override fun deleteUser(userId: Long) {
        userRepository.findById(userId).ifPresentOrElse(
            { user -> userRepository.deleteById(user.id) },
            { throw ResourceNotFoundException(RESOURCE_USER, FIELD_ID, userId) }
        )
    }
}
