package com.prasad.auth.service

import com.prasad.auth.dao.UserRepository
import com.prasad.auth.model.User
import org.springframework.stereotype.Service

@Service
class AuthService(private val userRepository: UserRepository) {

    fun findUserByEmail(email: String): User?
    {
        return userRepository.findByEmail(email)
    }

    fun register(user:User):User
    {
        return userRepository.save(user)
    }


}