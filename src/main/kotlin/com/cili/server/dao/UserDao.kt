package com.cili.server.dao

import com.cili.server.entiy.User
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

@Repository
interface UserDao {

    @Select("SELECT * FROM user")
    fun findAll(): List<User>?

    @Select("SELECT name FROM USER WHERE name = #{name}")
    fun findName(name: String?): String?

    @Insert("INSERT INTO user (name, pwd) VALUES (#{name}, #{pwd})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    fun addUser(name: String?, pwd: String?): Int?


}