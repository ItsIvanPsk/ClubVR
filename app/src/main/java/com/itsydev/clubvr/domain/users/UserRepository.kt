package com.itsydev.clubvr.domain.users

import com.google.firebase.firestore.auth.User
import com.itsydev.clubvr.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {

    val getAllUsers : Flow<List<UserEntity>> get() = userDao.getAllUsers()

    fun addUser(user : UserEntity){
        userDao.addUser(user)
    }

    fun deleteUser() {
        userDao.deleteUser()
    }


}