package com.itsydev.clubvr.presentation.settings

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.itsydev.clubvr.data.models.users.UserBo
import com.itsydev.clubvr.domain.users.UserRepository
import com.itsydev.clubvr.utils.BearEncrypt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Math.random
import java.util.*
import javax.inject.Inject
import kotlin.random.Random.Default.nextInt

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val isAdmin: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private val userCreated: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private val bear = BearEncrypt()

    fun changePassword(oldPass: String, newPass: String, reNewPass: String): Int {
        Log.d("5cos", "Entered!")
        if(oldPass.isEmpty() || newPass.isEmpty() || reNewPass.isEmpty()){
            Log.d("5cos", "0")
            return 0
        } else if(oldPass == newPass){
            Log.d("5cos", "1")
            return 1
        } else if(!isValidPassword(newPass)) {
            Log.d("5cos", "2")
            return 2
        } else {
            Log.d("5cos", "5")

            val auth = FirebaseAuth.getInstance()
            val user = auth.currentUser

            user?.updatePassword(newPass)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("5cos", "Contraseña actualizada exitosamente")
                    } else {
                        Log.w("5cos", "Error al actualizar la contraseña", task.exception)
                    }
                }

            return 5
        }
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[A-Z]).{6,}$".toRegex()
        return passwordPattern.matches(password)
    }

    fun isUserAdmin() {
        viewModelScope.launch {
            userRepository.getAllUsers.collect{
                if (it.isNotEmpty()){
                    isAdmin.value = bear.decrypt(it[0].admin) == "true"
                }
            }
        }
    }

    fun getUserAdmin(): LiveData<Boolean>{
        return isAdmin
    }

    fun getUserCreationState(): LiveData<Boolean> {
        return userCreated
    }

    fun createUser(
        name: String,
        surname: String,
        username: String,
        mail: String,
        phone: String,
        password: String
    ) {
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener { task: Task<AuthResult> ->
            userCreated.value = task.isSuccessful
            if(task.isSuccessful) {
                val user = hashMapOf(
                    "id" to bear.encrypt((0..99999).random().toString()),
                    "name" to bear.encrypt(name),
                    "surname" to bear.encrypt(surname),
                    "username" to bear.encrypt(username),
                    "mail" to bear.encrypt(mail),
                    "telf" to bear.encrypt(phone),
                    "len" to bear.encrypt("en"),
                    "userLevel" to bear.encrypt("1"),
                    "userPoints" to bear.encrypt("1"),
                    "admin" to bear.encrypt("0")
                )

                val db = FirebaseFirestore.getInstance()
                db.collection("profiles")
                    .add(user)
                    .addOnSuccessListener { documentReference -> userCreated.value = true }
                    .addOnFailureListener { e -> userCreated.value = false }
            }
        }
    }


}