package com.itsydev.clubvr.domain.users

import android.support.annotation.WorkerThread
import androidx.room.*
import com.itsydev.clubvr.UserEntity
import com.itsydev.clubvr.data.AsyncResult
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @WorkerThread
    suspend fun addUser(user: UserEntity)

    @Query("SELECT * FROM UserEntity ORDER BY id ASC")
    fun getAllUsers() : Flow<List<UserEntity>>

    /*
    @Query("SELECT * FROM UserEntity ORDER BY photoId ASC")
    fun getAllUsers() : Flow<List<UserEntity>>

    @Query("SELECT * FROM UserEntity WHERE pokemonId = :pokId ORDER BY photoId ASC")
    fun getUserById(pokId: Int) : Flow<List<UserEntity>>

    @Query("DELETE FROM UserEntity WHERE photoId = :photoId")
    fun deleteUser(photoId : Int)

    @Query("UPDATE PokemonPhotoEntity SET pokemonUri = :pokemonUri WHERE pokemonId =:pokemonId")
    fun updateUser(pokemonId: Int, pokemonUri: String)
     */


}