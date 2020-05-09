package com.example.todo.util

import javax.servlet.http.HttpServletRequest

object PathParamRemover {
  private val PATH_PARAM_REMOVER_REGEX = "(^v[0-9]*$|^[^0-9]*$)".toRegex()

  fun remove(request: HttpServletRequest): String {
    return request.requestURI.split("/")
      .map { pathSegment ->
        if (pathSegment.matches(PATH_PARAM_REMOVER_REGEX)) {
          pathSegment
        } else {
          "*"
        }
      }.toList().joinToString(separator = "/")
  }
}
