package com.example.todo.application.service

import com.example.todo.domain.model.api.header.TodoAppNoAuthHeaders
import org.openapitools.spring.models.AuthPostParameter
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthenticationService() {

  @Transactional(rollbackFor = [Exception::class])
  fun signup(
    header: TodoAppNoAuthHeaders,
    signupParam: AuthPostParameter
  ): HttpStatus {
    return HttpStatus.OK
  }
}
