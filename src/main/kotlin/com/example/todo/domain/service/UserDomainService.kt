package com.example.todo.domain.service

import com.example.todo.application.repository.UserRepository
import com.example.todo.domain.model.api.user.User
import com.example.todo.domain.model.exception.BadRequestException
import com.example.todo.domain.model.exception.NotFoundException
import org.openapitools.spring.models.AuthPostParameter
import org.springframework.stereotype.Service

@Service
class UserDomainService(
  private val userRepository: UserRepository
) {

  fun validateUserName(user: User) {
    userRepository.findByName(user.name)?.let {
      throw BadRequestException("ユーザ名がすでに使用されています")
    }
  }

  fun findUserByLoginParam(loginParam: AuthPostParameter): User {
    userRepository.findByName(loginParam.userName)?.toDomain?.let {
      if (it.password != loginParam.password) {
        throw BadRequestException("不正なパスワードです")
      }
      return it
    } ?: throw NotFoundException("ユーザ名が間違っています")
  }
}
