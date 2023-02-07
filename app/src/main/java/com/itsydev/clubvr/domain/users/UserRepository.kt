package com.itsydev.clubvr.domain.users

import com.itsydev.clubvr.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {

    fun getAllUsers(): Flow<List<UserEntity>> {
        return userDao.getAllUsers()
    }

    suspend fun addUser(task: UserEntity) {
        userDao.addUser(task)
    }


}