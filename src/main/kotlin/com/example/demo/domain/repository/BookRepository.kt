package com.example.demo.domain.repository

import com.example.demo.domain.model.Book
import com.example.demo.domain.model.BookWithRental
import java.time.LocalDate

interface BookRepository {
  fun findAllWithRental(): List<BookWithRental>
  fun findWithRental(id: Long): BookWithRental?
  fun register(book: Book)
  fun update(id: Long, title: String?, author: String?, releaseDate: LocalDate?)
  fun delete(id: Long)
}
