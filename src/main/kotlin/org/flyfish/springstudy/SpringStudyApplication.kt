package org.flyfish.springstudy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringStudyApplication

fun main(args: Array<String>) {
	runApplication<SpringStudyApplication>(*args)
}
