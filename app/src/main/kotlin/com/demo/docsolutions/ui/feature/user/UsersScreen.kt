package com.demo.docsolutions.ui.feature.user

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demo.docsolutions.ui.feature.user.model.UserState
import com.demo.docsolutions.ui.feature.user.model.UserUI

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 *
 */

@Composable
fun UsersRoute(
    state: UserState = UserState.Idle,
    searchText: String = "",
    onUpdateSearchText: (String) -> Unit = {},
    onSearch: () -> Unit = {},
    onCreateUser: () -> Unit = {}
) {

    UsersScreen(
        users = if (state is UserState.OnSearchUser) state.users else emptyList(),
        onUpdateSearchText = onUpdateSearchText,
        onSearch = onSearch,
        searchText = searchText,
        onCreateUser = onCreateUser
    )
}

@Composable
fun UsersScreen(
    users: List<UserUI>,
    searchText: String,
    onUpdateSearchText: (String) -> Unit,
    onSearch: () -> Unit,
    onCreateUser: () -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Column {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                OutlinedTextField(
                    modifier = Modifier.width(200.dp),
                    value = searchText,
                    onValueChange = onUpdateSearchText,
                    label = { Text(text = "Buscar") })

                Spacer(modifier = Modifier.width(10.dp))

                Button(onClick = {
                    keyboardController?.hide()
                    onSearch()
                }) {
                    Text(text = "Buscar")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = onCreateUser, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Crear usuario")
            }

            Spacer(modifier = Modifier.height(32.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "Seleccione un usuario para ver su informacion")

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(users) { user ->

                    UserItemRow(user.username)
                }
            }
        }
    }

}

@Composable
fun UserItemRow(username: String) {
    Column(modifier = Modifier.padding(top = 8.dp)) {
        HorizontalDivider()
        Column {

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = username)

            TextButton(onClick = {}) {
                Text(text = "Ver detalles")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UsersPreview() {
    UsersRoute()
}