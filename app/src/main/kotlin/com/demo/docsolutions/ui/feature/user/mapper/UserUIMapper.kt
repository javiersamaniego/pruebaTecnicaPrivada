package com.demo.docsolutions.ui.feature.user.mapper

import com.demo.docsolutions.core.domain.user.model.User
import com.demo.docsolutions.ui.feature.user.model.UserUI

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 *
 */
fun User.toUI() = UserUI(
    id = id,
    username = username,
    name = name,
    fatherLastName = fatherLastName,
    creationDate = creationDate,
    email = email,
    phone = phone
)