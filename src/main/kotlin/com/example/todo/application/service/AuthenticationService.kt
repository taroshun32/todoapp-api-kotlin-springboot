package com.example.todo.application.service

import com.example.todo.application.repository.UserRepository
import com.example.todo.domain.model.api.header.TodoAppNoAuthHeaders
import com.example.todo.domain.model.api.user.User
import org.openapitools.spring.models.AuthPostParameter
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthenticationService(
  private val userRepository: UserRepository
) {

  @Transactional(rollbackFor = [Exception::class])
  fun signup(
    header: TodoAppNoAuthHeaders,
    signupParam: AuthPostParameter
  ): HttpStatus {
    val user = User.of(header, signupParam)
    userRepository.store(user.toRecord)
    return HttpStatus.OK
  }
}
