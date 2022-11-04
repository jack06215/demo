package com.example.demo

import example1
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication class DemoApplication

fun main(args: Array<String>) {
	example1()
  runApplication<DemoApplication>(*args)
}
