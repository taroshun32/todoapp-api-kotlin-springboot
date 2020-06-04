package com.example.todo.presentation.controller.api

import org.openapitools.spring.apis.TaskApiBaseController
import org.openapitools.spring.models.Task
import org.openapitools.spring.models.TaskDonePutParameter
import org.openapitools.spring.models.TaskPostAndPutParameter
import org.openapitools.spring.models.Tasks
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RestController

@RestController
@Validated
class TaskController : TaskApiBaseController() {

  override fun apiV1TasksGet(authorization: String, xminusOSMinusTYPE: String, xminusAPPMinusVERSION: String): Tasks {
    TODO("Not yet implemented")
  }

  override fun apiV1TasksPost(authorization: String, xminusOSMinusTYPE: String, xminusAPPMinusVERSION: String, taskPostAndPutParameter: TaskPostAndPutParameter?): Task {
    TODO("Not yet implemented")
  }

  override fun apiV1TasksTaskIdDelete(authorization: String, xminusOSMinusTYPE: String, xminusAPPMinusVERSION: String, taskId: String): HttpStatus {
    TODO("Not yet implemented")
  }

  override fun apiV1TasksTaskIdDonePut(authorization: String, xminusOSMinusTYPE: String, xminusAPPMinusVERSION: String, taskId: String, taskDonePutParameter: TaskDonePutParameter?): HttpStatus {
    TODO("Not yet implemented")
  }

  override fun apiV1TasksTaskIdPut(authorization: String, xminusOSMinusTYPE: String, xminusAPPMinusVERSION: String, taskId: String, taskPostAndPutParameter: TaskPostAndPutParameter?): Task {
    TODO("Not yet implemented")
  }
}
