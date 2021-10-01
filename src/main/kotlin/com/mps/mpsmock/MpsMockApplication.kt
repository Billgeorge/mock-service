package com.mps.mpsmock

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MpsMockApplication

fun main(args: Array<String>) {
    runApplication<MpsMockApplication>(*args)
}
