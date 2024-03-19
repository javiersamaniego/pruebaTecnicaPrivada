package com.demo.docsolutions.ui.feature.user

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.demo.docsolutions.ui.composable.InformativeDialog
import com.demo.docsolutions.ui.feature.user.model.UserCreationState

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 *
 */
@Composable
fun UserCreationScreenRoute(
    onFinish: () -> Unit = {}
) {

    UserCreationScreen(onFinish = onFinish)
}

@Composable
fun UserCreationScreen(
    viewModel: UserCreationViewModel = hiltViewModel(),
    onFinish: () -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        when (state) {
            UserCreationState.Loading -> {
                Dialog(onDismissRequest = { viewModel.resetState() }, content = {
                    Card {

                        Box(modifier = Modifier.padding(16.dp)) {
                            CircularProgressIndicator()
                        }
                    }
                })
            }

            UserCreationState.OnCreated -> {

                InformativeDialog(
                    message = "Usuario creado!",
                    onDismiss = {
                        onFinish()
                    },
                    icon = Icons.Outlined.AccountCircle
                )
            }

            is UserCreationState.OnError -> {
                val state2 = state as UserCreationState.OnError
                InformativeDialog(message = state2.message, onDismiss = {
                    viewModel.resetState()
                })
            }

            else -> {

            }
        }

        val modifier = Modifier.fillMaxWidth()

        Column {

            Text(text = "Creación de usuario", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = viewModel.username,
                onValueChange = viewModel::onUpdateUserName,
                label = { Text(text = "Nombre de usuario") },
                modifier = modifier,
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = viewModel.password,
                onValueChange = viewModel::onUpdatePassword,
                label = { Text(text = "Password") },
                modifier = modifier,
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = viewModel.name,
                onValueChange = viewModel::onUpdateName,
                label = { Text(text = "Nombre") },
                modifier = modifier,
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = viewModel.fatherLastName,
                onValueChange = viewModel::onUpdateFatherLastName,
                label = { Text(text = "Apellido paterno") },
                modifier = modifier,
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = viewModel.motherLastName,
                onValueChange = viewModel::onUpdateMotherLastName,
                label = { Text(text = "Apellido Materno") },
                modifier = modifier,
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = viewModel.email,
                onValueChange = viewModel::onUpdateEmail,
                label = { Text(text = "Email") },
                modifier = modifier,
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = viewModel.phone,
                onValueChange = viewModel::onUpdatePhone,
                label = { Text(text = "Teléfono") },
                modifier = modifier,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = viewModel::createUser, modifier = modifier) {
                Text(text = "Crear usuario")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserCreationScreenPreview() {

    UserCreationScreenRoute()
}