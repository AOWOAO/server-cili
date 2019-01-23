package com.cili.server.controller

import com.cili.server.dao.UserDao
import com.cili.server.entiy.BaseJson
import com.google.gson.Gson
import org.apache.ibatis.session.SqlSessionException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.sql.SQLException

@RestController
@RequestMapping("test")
class TestController {

    val gson = Gson()

    @Autowired
    lateinit var userDao: UserDao

    @GetMapping("/")
    fun index(): String {
        return "test"
    }

    @GetMapping("sel")
    fun findAll(): Any {
        val userList = userDao.findAll()
        val gson = Gson()

        val json = gson.toJson(BaseJson(1, "success", userList))
        return json
    }

    @GetMapping("add")
    fun add(@RequestParam u: String, @RequestParam p: String): String {

        if (!isFind(u)) {
            userDao.addUser(u, p)?.let {
                return gson.toJson(BaseJson(msg = "$u 添加成功"))
            }
            return gson.toJson(BaseJson(msg = "$u 添加失败"))
        }
        return gson.toJson(BaseJson(-1, "$u 已存在"))

    }

    @GetMapping("find")
    fun findOne(@RequestParam name:String): String? {
        if (isFind(name)) return gson.toJson(BaseJson( msg = "查询到 $name"))

        return gson.toJson(BaseJson(-1, "$name 不存在"))
    }

    fun isFind(name: String?): Boolean {
        userDao.findName(name)?.let {
            return true
        }
        return false
    }
}