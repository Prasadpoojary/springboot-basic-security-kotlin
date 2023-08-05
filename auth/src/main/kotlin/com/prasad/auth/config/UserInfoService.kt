package com.prasad.auth.config

import com.prasad.auth.model.User
import com.prasad.auth.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserInfoService:UserDetailsService
{
    @Autowired
    private lateinit var authService: AuthService;



    override fun loadUserByUsername(email: String?): UserDetails
    {
        println("================================> start <===================== $email ============")
        println(email)

        val user: User =authService.findUserByEmail(email!!)!!
        println(user)

        println("================================> end <=================================")
        val userDetails:UserInfoDetail=UserInfoDetail(user)
        return userDetails
    }
}