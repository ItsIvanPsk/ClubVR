package com.itsydev.clubvr.presentation.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(

) : ViewModel() {

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

}