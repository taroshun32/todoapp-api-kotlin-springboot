package com.example.todo.application.service

import com.example.todo.application.repository.TokenRepository
import com.example.todo.application.repository.UserRepository
import com.example.todo.domain.model.api.header.TodoAppNoAuthHeaders
import com.example.todo.domain.model.api.token.Token
import com.example.todo.domain.model.api.user.User
import com.example.todo.domain.model.exception.BadRequestException
import com.example.todo.domain.model.exception.DatabaseException
import com.example.todo.domain.model.exception.NotFoundException
import com.example.todo.domain.service.TokenDomainService
import org.openapitools.spring.models.AuthPostParameter
import org.openapitools.spring.models.TokenRefreshPostParameter
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthenticationService(
  private val userRepository: UserRepository,
  private val tokenRepository: TokenRepository,
  private val tokenDomainService: TokenDomainService
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

  @Transactional(rollbackFor = [Exception::class])
  fun login(
    header: TodoAppNoAuthHeaders,
    loginParam: AuthPostParameter
  ): org.openapitools.spring.models.Token {
    val user = userRepository.findByName(loginParam.userName)?.toDomain
      ?: throw NotFoundException("ユーザ名が間違っています")
    if (user.password != loginParam.password) {
      throw BadRequestException("不正なパスワードです")
    }
    return tokenRepository.findByUserId(user.id)?.let {
      tokenRepository.update(it.update()).toDomain.dto
    } ?: throw DatabaseException("ユーザに紐づくトークンが存在しません")
  }

  @Transactional(rollbackFor = [Exception::class])
  fun refresh(
    tokenRefreshParam: TokenRefreshPostParameter
  ): org.openapitools.spring.models.Token {

    val tokenRecord = tokenDomainService.validateRefreshToken(tokenRefreshParam)
    val refreshedToken = tokenRepository.update(tokenRecord.update())
    return refreshedToken.toDomain.dto
  }
}
