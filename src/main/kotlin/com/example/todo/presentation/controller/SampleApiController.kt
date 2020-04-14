package com.example.todo.presentation.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sample/api")
class SampleApiController() {

    @GetMapping
    fun ok(): HttpStatus = HttpStatus.OK

}
