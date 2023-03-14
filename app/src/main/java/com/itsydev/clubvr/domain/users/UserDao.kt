package com.itsydev.clubvr.domain.users

import android.support.annotation.WorkerThread
import androidx.room.*
import com.itsydev.clubvr.data.models.users.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: UserEntity)

    @Query("SELECT * FROM UserEntity ORDER BY id ASC")
    fun getAllUsers() : Flow<List<UserEntity>>

    @Query("DELETE FROM UserEntity")
    fun deleteUser()

}