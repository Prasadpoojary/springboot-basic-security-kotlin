package com.prasad.auth.controller

import com.prasad.auth.dto.UserDTO
import com.prasad.auth.model.User
import com.prasad.auth.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Objects

@RestController
@RequestMapping("/auth")
class AuthController(private val authService:AuthService)
{

    @GetMapping("/hello")
    fun sayHello():ResponseEntity<String>
    {
        return ResponseEntity.ok("Hello world")
    }


    @PostMapping("/add")
    fun register(@RequestBody user:User):ResponseEntity<Any>
    {
        return try {
            val responseUser:User=authService.register(user)
            val response:UserDTO=UserDTO("Registered Successfully",responseUser.name,responseUser.email,responseUser.role)
            ResponseEntity.status(HttpStatus.CREATED).body(response)
        } catch (e:Exception) {
            println(e.message)
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to register")
        }

    }

    @GetMapping("/secure")
    fun secureTest():ResponseEntity<String>
    {
        return ResponseEntity.ok("Secured endpoint")
    }
}