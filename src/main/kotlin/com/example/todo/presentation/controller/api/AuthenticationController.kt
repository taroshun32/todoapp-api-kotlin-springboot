package com.example.todo.presentation.controller.api

import com.example.todo.application.service.AuthenticationService
import com.example.todo.domain.model.api.header.TodoAppNoAuthHeaders
import org.openapitools.spring.models.AuthPostParameter
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@Validated
class AuthenticationController(
  private val authService: AuthenticationService
) {

  @RequestMapping(
    value = ["/api/signup"],
    produces = ["application/json"],
    method = [RequestMethod.POST]
  )
  @ResponseStatus(HttpStatus.OK)
  fun signup(
    @RequestHeader(value = "X-OS-TYPE", required = true) xOsType: String,
    @RequestHeader(value = "X-APP-VERSION", required = true) xAppVersion: String,
    @Valid @RequestBody authPostParameter: AuthPostParameter
  ): HttpStatus {
    val header = TodoAppNoAuthHeaders.of(xOsType, xAppVersion)
    return authService.signup(header, authPostParameter)
  }
}
