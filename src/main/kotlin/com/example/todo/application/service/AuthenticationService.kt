package com.example.todo.application.service

import com.example.todo.application.repository.TokenRepository
import com.example.todo.application.repository.UserRepository
import com.example.todo.domain.model.api.header.TodoAppHeaders
import com.example.todo.domain.model.api.header.TodoAppNoAuthHeaders
import com.example.todo.domain.model.api.token.Token
import com.example.todo.domain.model.api.user.User
import com.example.todo.domain.service.TokenDomainService
import com.example.todo.domain.service.UserDomainService
import org.openapitools.spring.models.AuthPostParameter
import org.openapitools.spring.models.TokenRefreshPostParameter
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthenticationService(
  private val userRepository: UserRepository,
  private val userDomainService: UserDomainService,
  private val tokenRepository: TokenRepository,
  private val tokenDomainService: TokenDomainService
) {

  @Transactional(rollbackFor = [Exception::class])
  fun signup(
    header: TodoAppNoAuthHeaders,
    signupParam: AuthPostParameter
  ): org.openapitools.spring.models.Token {
    val user = User.of(header, signupParam)
    userDomainService.validateUserName(user)
    userRepository.store(user.toRecord)

    val token = Token.of(user.id)
    tokenRepository.store(token.toRecord)

    return token.dto
  }

  @Transactional(rollbackFor = [Exception::class])
  fun login(loginParam: AuthPostParameter): org.openapitools.spring.models.Token {
    val user = userDomainService.findUserByLoginParam(loginParam)

    return tokenDomainService.updateToken(user.id).dto
  }

  @Transactional(rollbackFor = [Exception::class])
  fun refresh(tokenRefreshParam: TokenRefreshPostParameter): org.openapitools.spring.models.Token {
    val tokenRecord = tokenDomainService.validateRefreshToken(tokenRefreshParam)
    val refreshedToken = tokenRepository.update(tokenRecord.update())

    return refreshedToken.toDomain.dto
  }

  @Transactional(rollbackFor = [Exception::class])
  fun logout(header: TodoAppHeaders): HttpStatus {
    tokenDomainService.logout(header.authorization.accessToken)

    return HttpStatus.OK
  }
}
