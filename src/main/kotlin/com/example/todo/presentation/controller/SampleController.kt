package com.example.todo.presentation.controller

import com.example.todo.domain.model.exception.BadRequestException
import org.openapitools.spring.models.Token
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sample")
class SampleController() {

  @GetMapping
  fun ok(): HttpStatus = HttpStatus.OK

  @GetMapping("/error")
  fun error(): HttpStatus {
    throw BadRequestException("Sample Exception")
  }

  @GetMapping("/model")
  fun model(): Token {
    return Token(
      tokenType = "Bearer",
      accessToken = "accessToken",
      refreshToken = "refreshToken",
      expiresIn = 3600
    )
  }
}
