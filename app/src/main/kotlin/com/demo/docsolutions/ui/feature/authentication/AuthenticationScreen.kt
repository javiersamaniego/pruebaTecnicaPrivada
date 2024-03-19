package com.demo.docsolutions.ui.feature.authentication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demo.docsolutions.ui.composable.InformativeDialog
import com.demo.docsolutions.ui.feature.authentication.model.AuthenticationState

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 *
 */
@Composable
fun AuthenticationRoute(
    authenticationState: AuthenticationState = AuthenticationState.Idle,
    username: String = "",
    password: String = "",
    onUpdateUserName: (String) -> Unit = {},
    onUpdatePassword: (String) -> Unit = {},
    onLogin: () -> Unit = {},
    onResetState: () -> Unit = {}
) {

    AuthenticationScreen(
        authenticationState = authenticationState,
        username = username,
        password = password,
        onUpdateUserName = onUpdateUserName,
        onUpdatePassword = onUpdatePassword,
        onLogin = onLogin,
        onResetState = onResetState
    )
}

@Composable
fun AuthenticationScreen(
    authenticationState: AuthenticationState = AuthenticationState.Idle,
    username: String,
    password: String,
    onUpdateUserName: (String) -> Unit,
    onUpdatePassword: (String) -> Unit,
    onLogin: () -> Unit,
    onResetState: () -> Unit
) {

    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {

        if (authenticationState is AuthenticationState.OnError) {
            InformativeDialog(message = authenticationState.message, onDismiss = {
                onResetState()
            })
        }

        Column {
            Text(
                text = "Inicio de sesion",
                style = MaterialTheme.typography.titleMedium
            ) // TODO Ponerlo en strings

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                enabled = !authenticationState.isLoading(),
                value = username, onValueChange = onUpdateUserName, label = {
                    Text(text = "Usuario")
                }, keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ), modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                enabled = !authenticationState.isLoading(),
                value = password, onValueChange = onUpdatePassword, label = {
                    Text(text = "Password")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onLogin,
                modifier = Modifier.fillMaxWidth(),
                enabled = !authenticationState.isLoading()
            ) {
                Text(text = "OK")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (authenticationState.isLoading())
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AuthenticationScreenPreview() {
    AuthenticationRoute()
}