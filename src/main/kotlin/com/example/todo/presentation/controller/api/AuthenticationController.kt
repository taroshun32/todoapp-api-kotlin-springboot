package com.example.todo.presentation.controller.api

import com.example.todo.application.service.AuthenticationService
import com.example.todo.domain.model.api.header.TodoAppHeaders
import com.example.todo.domain.model.api.header.TodoAppNoAuthHeaders
import org.openapitools.spring.apis.AuthenticationApiBaseController
import org.openapitools.spring.models.AuthPostParameter
import org.openapitools.spring.models.Token
import org.openapitools.spring.models.TokenRefreshPostParameter
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
  private val service: AuthenticationService
) : AuthenticationApiBaseController() {

  @RequestMapping(
    value = ["/api/signup"],
    produces = ["application/json"],
    consumes = ["application/json"],
    method = [RequestMethod.POST]
  )
  @ResponseStatus(HttpStatus.OK)
  override fun apiV1SignupPost(
    @RequestHeader(value = "X-OS-TYPE", required = true) xminusOSMinusTYPE: String,
    @RequestHeader(value = "X-APP-VERSION", required = true) xminusAPPMinusVERSION: String,
    @Valid @RequestBody authPostParameter: AuthPostParameter
  ): Token {
    val header = TodoAppNoAuthHeaders.of(xminusOSMinusTYPE, xminusAPPMinusVERSION)
    return service.signup(header, authPostParameter)
  }

  @RequestMapping(
    value = ["/api/login"],
    produces = ["application/json"],
    consumes = ["application/json"],
    method = [RequestMethod.POST]
  )
  @ResponseStatus(HttpStatus.OK)
  override fun apiV1LoginPost(
    @RequestHeader(value = "X-OS-TYPE", required = true) xminusOSMinusTYPE: String,
    @RequestHeader(value = "X-APP-VERSION", required = true) xminusAPPMinusVERSION: String,
    @Valid @RequestBody authPostParameter: AuthPostParameter
  ): Token {
    TodoAppNoAuthHeaders.of(xminusOSMinusTYPE, xminusAPPMinusVERSION)
    return service.login(authPostParameter)
  }

  @RequestMapping(
    value = ["/api/token/refresh"],
    produces = ["application/json"],
    consumes = ["application/json"],
    method = [RequestMethod.POST]
  )
  @ResponseStatus(HttpStatus.OK)
  override fun apiV1TokenRefreshPost(
    @RequestHeader(value = "X-OS-TYPE", required = true) xminusOSMinusTYPE: String,
    @RequestHeader(value = "X-APP-VERSION", required = true) xminusAPPMinusVERSION: String,
    @Valid @RequestBody tokenRefreshPostParameter: TokenRefreshPostParameter
  ): Token {
    TodoAppNoAuthHeaders.of(xminusOSMinusTYPE, xminusAPPMinusVERSION)
    return service.refresh(tokenRefreshPostParameter)
  }

  @RequestMapping(
    value = ["/api/logout"],
    produces = ["application/json"],
    consumes = ["application/json"],
    method = [RequestMethod.POST]
  )
  @ResponseStatus(HttpStatus.OK)
  override fun apiV1LogoutPost(
    @RequestHeader(value = "Authorization", required = true) authorization: String,
    @RequestHeader(value = "X-OS-TYPE", required = true) xminusOSMinusTYPE: String,
    @RequestHeader(value = "X-APP-VERSION", required = true) xminusAPPMinusVERSION: String
  ): HttpStatus {
    val header = TodoAppHeaders.of(xminusOSMinusTYPE, xminusAPPMinusVERSION, authorization)
    return service.logout(header)
  }
}
