package com.example.demo.presentation.controller

import com.example.demo.application.service.BookService
import com.example.demo.presentation.form.BookInfo
import com.example.demo.presentation.form.GetBookDetailResponse
import com.example.demo.presentation.form.GetBookListResponse
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("book")
@CrossOrigin
class BookController(private val bookService: BookService) {
  @GetMapping("/list")
  fun getList(): GetBookListResponse {
    val bookList = bookService.getList().map { BookInfo(it) }
    return GetBookListResponse(bookList)
  }

  @GetMapping("/detail/{book_id}")
  fun getDetail(@PathVariable("book_id") bookId: Long): GetBookDetailResponse {
    val book = bookService.getDetail(bookId)
    return GetBookDetailResponse(book)
  }
}
