
package com.example.touristexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> get() = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> get() = _password

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _loginResult = MutableStateFlow("")
    val loginResult: StateFlow<String> get() = _loginResult

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun login(onResult: (Boolean) -> Unit) {
        val emailValue = _email.value
        val passwordValue = _password.value

        if (emailValue.isBlank() || passwordValue.isBlank()) {
            _loginResult.value = "Correo y contraseña son requeridos."
            return
        }

        _isLoading.value = true
        _loginResult.value = ""

        auth.signInWithEmailAndPassword(emailValue, passwordValue)
            .addOnCompleteListener { task ->
                _isLoading.value = false
                if (task.isSuccessful) {
                    onResult(true)
                } else {
                    _loginResult.value = task.exception?.localizedMessage ?: "Error al iniciar sesión."
                    onResult(false)
                }
            }
    }
}
