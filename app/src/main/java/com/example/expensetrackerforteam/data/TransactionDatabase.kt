package com.example.expensetrackerforteam.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.expensetrackerforteam.data.local.converter.DateConverter
import com.example.expensetrackerforteam.data.local.entity.ParticipantDto
import com.example.expensetrackerforteam.data.local.entity.TransactionDto

@TypeConverters(DateConverter::class)
@Database(entities = [TransactionDto::class, ParticipantDto::class], version = 1, exportSchema = true)
abstract class TransactionDatabase: RoomDatabase(){
    abstract val transactionDao: TransactionDao

//    companion object {
//        @Volatile
//        private var Instance: TransactionDatabase? = null
//
//        fun getDatabase(context: Context): TransactionDatabase {
//            // if the Instance is not null, return it, otherwise create a new database instance.
//            return Instance ?: synchronized(this) {
//                Room.databaseBuilder(context, TransactionDatabase::class.java, "item_database")
//                    .build()
//                    .also { Instance = it }
//            }
//        }
//    }
}