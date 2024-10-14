
package com.example.touristexplorer.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.touristexplorer.viewmodel.LoginViewModel

@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val loginResult by viewModel.loginResult.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Iniciar Sesión") })
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = email,
                        onValueChange = { viewModel.onEmailChange(it) },
                        label = { Text("Correo Electrónico") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = { viewModel.onPasswordChange(it) },
                        label = { Text("Contraseña") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { viewModel.login { success ->
                            if (success) {
                                navController.navigate("home") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        } },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Iniciar Sesión")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    TextButton(onClick = { /* Navegar a pantalla de registro si la hay */ }) {
                        Text("¿No tienes una cuenta? Regístrate")
                    }
                    if (loginResult.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = loginResult,
                            color = MaterialTheme.colors.error
                        )
                    }
                }
            }
        }
    }
}
