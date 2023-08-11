package com.moling.micatoolkit.presentation.model

import androidx.compose.ui.graphics.vector.ImageVector

class DetailItem(icon: ImageVector, titleId: Int, content: String, route: String?) {
    val icon: ImageVector
    val titleId: Int
    val content: String
    val route: String?

    init {
        this.icon = icon
        this.titleId = titleId
        this.content = content
        this.route = route
    }
}