package com.example.expensetrackerforteam.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expensetrackerforteam.domain.model.Participant

@Entity(tableName = "participant_table")
data class ParticipantDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int,
    @ColumnInfo(name = "participant")
    val participantName: String,
    @ColumnInfo(name = "balance")
    var balance: Double,
    @ColumnInfo(name = "income")
    var income: Double,
    @ColumnInfo(name = "expense")
    var expense: Double
) {
    fun toParticipant() : Participant = Participant(participantName, balance, income, expense)
}
