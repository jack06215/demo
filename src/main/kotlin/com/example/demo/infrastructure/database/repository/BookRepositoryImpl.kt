package com.example.demo.infrastructure.database.repository

import com.example.demo.domain.model.Book
import com.example.demo.domain.model.BookWithRental
import com.example.demo.domain.model.Rental
import com.example.demo.domain.repository.BookRepository
import com.example.demo.infrastructure.database.mapper.BookMapper
import com.example.demo.infrastructure.database.mapper.custom.BookWithRentalMapper
import com.example.demo.infrastructure.database.mapper.custom.select
import com.example.demo.infrastructure.database.mapper.custom.selectByPrimaryKey
import com.example.demo.infrastructure.database.mapper.deleteByPrimaryKey
import com.example.demo.infrastructure.database.mapper.insert
import com.example.demo.infrastructure.database.mapper.updateByPrimaryKeySelective
import com.example.demo.infrastructure.database.record.BookRecord
import com.example.demo.infrastructure.database.record.custom.BookWithRentalRecord
import java.time.LocalDate
import org.springframework.stereotype.Repository

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Repository
class BookRepositoryImpl(
    private val bookWithRentalMapper: BookWithRentalMapper,
    private val bookMapper: BookMapper
) : BookRepository {
  override fun findAllWithRental(): List<BookWithRental> {
    return bookWithRentalMapper.select().map { toModel(it) }
  }

  override fun findWithRental(id: Long): BookWithRental? {
    return bookWithRentalMapper.selectByPrimaryKey(id)?.let { toModel(it) }
  }

  override fun register(book: Book) {
    bookMapper.insert(toRecord(book))
  }

  override fun update(id: Long, title: String?, author: String?, releaseDate: LocalDate?) {
    bookMapper.updateByPrimaryKeySelective(BookRecord(id, title, author, releaseDate))
  }

  override fun delete(id: Long) {
    bookMapper.deleteByPrimaryKey(id)
  }
  private fun toModel(record: BookWithRentalRecord): BookWithRental {
    val book = Book(record.id!!, record.title!!, record.author!!, record.releaseDate!!)
    val rental =
        record.userId?.let {
          Rental(record.id!!, record.userId!!, record.rentalDatetime!!, record.returnDeadline!!)
        }
    return BookWithRental(book, rental)
  }

  private fun toRecord(model: Book): BookRecord {
    return BookRecord(model.id, model.title, model.author, model.releaseDate)
  }
}
