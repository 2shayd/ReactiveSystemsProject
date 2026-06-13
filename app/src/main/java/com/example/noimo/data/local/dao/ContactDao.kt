package com.example.noimo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.noimo.data.local.entity.ContactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: ContactEntity)

    @Query("SELECT * FROM contacts ORDER BY email ASC")
    fun observeAll(): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE synced = 0")
    suspend fun getUnsyncedContacts(): List<ContactEntity>

    @Query("UPDATE contacts SET synced = 1 WHERE id IN (:ids)")
    suspend fun markSynced(ids: List<String>)

    @Query("DELETE FROM contacts WHERE id = :id")
    suspend fun deleteById(id: String)
}