package com.example.todo.application.service

import com.example.todo.application.repository.TokenRepository
import com.example.todo.application.repository.UserRepository
import com.example.todo.domain.model.api.header.TodoAppNoAuthHeaders
import com.example.todo.domain.model.api.token.Token
import com.example.todo.domain.model.api.user.User
import org.openapitools.spring.models.AuthPostParameter
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthenticationService(
  private val userRepository: UserRepository,
  private val tokenRepository: TokenRepository
) {

  @Transactional(rollbackFor = [Exception::class])
  fun signup(
    header: TodoAppNoAuthHeaders,
    signupParam: AuthPostParameter
  ): org.openapitools.spring.models.Token {
    val user = User.of(header, signupParam)
    userRepository.store(user.toRecord)
    val token = Token.of(user.id)
    tokenRepository.store(token.toRecord)
    return token.dto
  }
}
