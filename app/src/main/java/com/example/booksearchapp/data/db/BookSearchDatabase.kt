package com.example.booksearchapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.booksearchapp.data.model.Book

@Database(
    entities = [Book::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(OrmConverter::class)
abstract class BookSearchDatabase : RoomDatabase() {
    abstract fun bookSearchDao(): BookSearchDao

    companion object {
        @Volatile
        private var INSTANCE: BookSearchDatabase? = null

        private fun buildDatabase(context: Context): BookSearchDatabase = androidx.room.Room.databaseBuilder(
            context.applicationContext,
            BookSearchDatabase::class.java,
            "book_search_database"
        ).build()

        fun getInstance(context: Context): BookSearchDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }
    }
}