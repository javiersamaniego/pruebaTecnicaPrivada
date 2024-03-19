package com.demo.docsolutions.ui.composable

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 *
 */
@Composable
fun InformativeDialog(message: String, onDismiss: () -> Unit, icon: ImageVector = Icons.Outlined.Warning) {
    AlertDialog(onDismissRequest = onDismiss, confirmButton = {
        TextButton(onClick = onDismiss) {
            Text(text = "Cerrar")
        }
    },
        icon = { Icon(imageVector = icon, contentDescription = "") },
        title = { Text(text = "") },
        text = { Text(text = message) })
}