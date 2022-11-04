package com.example.demo

import com.example.demo.database.UserDynamicSqlSupport
import com.example.demo.database.UserMapper
import com.example.demo.database.select
import com.example.demo.database.selectByPrimaryKey
import org.apache.ibatis.io.Resources
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.mybatis.dynamic.sql.SqlBuilder
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

fun createSessionFactory(): SqlSessionFactory {
  val resource = "mybatisConfig.xml"
  val inputStream = Resources.getResourceAsStream(resource)
  return SqlSessionFactoryBuilder().build(inputStream)
}

data class HelloResponse(val message: String)

data class HelloRequest(val name: String)

interface Greeter {
  fun sayHello(name: String): String
}

@Component
class GreeterImpl : Greeter {
  override fun sayHello(name: String) = "Hello $name in Greeter Service"
}

@Controller
@RequestMapping("hello")
class HelloController {
  // hello/world
  @GetMapping("/world")
  fun index(model: Model): String {
    model.addAttribute("message", "Hello World in index.html !!")
    return "index"
  }
}

@RestController
class SampleController {
  @GetMapping("/")
  // root
  fun getHello(): String {
    return "Hello World"
  }
}

@RestController
@RequestMapping("greeter")
class GreeterController(
    private val greeter: Greeter,
) {
  @GetMapping("/hello")
  // e.g. greeter/hello?name=Jack
  fun hello(@RequestParam("name") name: String): HelloResponse {
    return HelloResponse("Hello ${name} in RequestParam")
  }

  @GetMapping("/hello/{name}")
  // e.g. greeter/Jack
  fun helloPathValue(@PathVariable("name") name: String): HelloResponse {
    return HelloResponse("Hello ${name}")
  }

  @PostMapping("/hello")
  fun helloByPost(@RequestBody request: HelloRequest): HelloResponse {
    return HelloResponse("Hello ${request.name} in RequestBody")
  }

  @GetMapping("hello/byservice/{name}")
  fun helloByService(@PathVariable("name") name: String): HelloResponse {
    val message = greeter.sayHello(name)
    return HelloResponse(message)
  }

  @GetMapping("/hello/user/{user_id}")
  fun getUser(@PathVariable("user_id") user_id: Int): HelloResponse {
    createSessionFactory().openSession().use { session ->
      val mapper = session.getMapper(UserMapper::class.java)
      val user = mapper.selectByPrimaryKey(user_id)
      println(user)

      return HelloResponse("Hello ${user!!.name}")
    }
  }

  @GetMapping("/hello/user/name/{username}")
  fun getUserName(@PathVariable("username") username: String): HelloResponse {
    createSessionFactory().openSession().use { session ->
      val mapper = session.getMapper(UserMapper::class.java)
      val user =
          mapper.select { where(UserDynamicSqlSupport.User.name, SqlBuilder.isEqualTo(username)) }
      println(user)
      return HelloResponse("Hello ${user[0].name}")
    }
  }
}
