package com.example.demo.domain.model

data class BookWithRental(val book: Book, val rental: Rental?) {
  val isRental: Boolean
    get() = rental != null
}
