package com.cili.server

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@MapperScan("com.cili.server.dao")
class ServerApplication

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}

