package org.flyfish.springstudy.controler

import org.flyfish.springstudy.app.CommonResp
import org.flyfish.springstudy.app.ServerResponse
import org.flyfish.springstudy.beans.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController {

    @GetMapping("/login/{userId}")
    fun getUserDetails(@PathVariable("userId") userId: String): ServerResponse? {
        val user = User()
        user.userId = userId
        user.nickName = "fee"
        user.age = 30
        user.userName = "8888888"
        return CommonResp.respSuccess(user)
    }
}