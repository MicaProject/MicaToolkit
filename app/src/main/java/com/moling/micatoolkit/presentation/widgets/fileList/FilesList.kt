package com.moling.micatoolkit.presentation.widgets.fileList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.CreateNewFolder
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.UploadFile
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.items
import com.moling.micatoolkit.presentation.Constants
import com.moling.micatoolkit.presentation.navigator.navToFileDownloadFromTarget
import com.moling.micatoolkit.presentation.navigator.navToFileInfo
import com.moling.micatoolkit.presentation.navigator.navToFileUploadFromTarget
import com.moling.micatoolkit.presentation.navigator.navToFolderCreate
import com.moling.micatoolkit.presentation.widgets.functionList.FuncChip

@Composable
fun FileList(
    navController: NavController,
    location: String,
    fileList: List<FileItem>,
    isRemoteMode: Boolean = true,
    browseMode: String,
    onFileSelect: (path: String) -> Unit,
    onDirChange: (path: String) -> Unit
) {
    ScalingLazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            ListHeader {
                Text(
                    text = location,
                    color = Color.White,
                    modifier = Modifier.clickable { /* TODO: File Manage Options */ }
                )
            }
        }
        item {
            Chip(
                onClick = { /* IGNORE */ },
                colors = ChipDefaults.secondaryChipColors(backgroundColor = Color.Transparent),
                label = {
                    BackChip {
                        onDirChange(location.split("/").dropLast(1).joinToString("/"))
                    }
                    if (isRemoteMode) {
                        CreateFolderChip(navController, location)
                        UploadChip(navController, location)
                    }
                    if (browseMode == Constants.BROWSER_MODE_DIRECTORY) {
                        DirSelectChip(navController, location)
                    }
                }
            )

        }
        items(fileList) {
            FuncChip(
                onClick = {
                    when (it.type) {
                        Constants.DIRECTORY -> {
                            onDirChange("${location}/${it.name}")
                        }
                        else -> {
                            onFileSelect("${location}/${it.name}")
                        }
                    }
                },
                onLongClick = {
                    navToFileInfo(navController, "${location}/${it.name}", it.type)
                },
                colors = ChipDefaults.secondaryChipColors(),
                modifier = Modifier.fillMaxWidth(),
                label = {
                    FileChip(icon = it.icon, name = it.name)
                }
            )
        }
    }
}

@Composable
fun BackChip(
    onClick: () -> Unit,
) {
    Chip(
        onClick = onClick,
        colors = ChipDefaults.secondaryChipColors(),
        modifier = Modifier.padding(end = 5.dp),
        label = {
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "")
            }
        }
    )
}

@Composable
fun CreateFolderChip(
    navController: NavController,
    location: String
) {
    Chip(
        onClick = {
            navToFolderCreate(navController, location)
        },
        colors = ChipDefaults.secondaryChipColors(),
        modifier = Modifier.padding(start = 5.dp, end = 5.dp),
        label = {
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {
                Icon(imageVector = Icons.Outlined.CreateNewFolder, contentDescription = "")
            }
        }
    )
}

@Composable
fun UploadChip(
    navController: NavController,
    location: String
) {
    Chip(
        onClick = {
            navToFileUploadFromTarget(navController, location)
        },
        colors = ChipDefaults.secondaryChipColors(),
        modifier = Modifier.padding(start = 5.dp),
        label = {
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {
                Icon(imageVector = Icons.Outlined.UploadFile, contentDescription = "")
            }
        }
    )
}

@Composable
fun DirSelectChip(
    navController: NavController,
    location: String
) {
    Chip(
        onClick = {
            navToFileDownloadFromTarget(navController, location)
        },
        colors = ChipDefaults.secondaryChipColors(),
        modifier = Modifier.padding(start = 5.dp),
        label = {
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {
                Icon(imageVector = Icons.Outlined.Done, contentDescription = "")
            }
        }
    )
}

@Composable
fun FileChip(
    icon: ImageVector,
    name: String
) {
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier
        .fillMaxHeight()) {
        Icon(imageVector = icon, contentDescription = "")
    }
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier
        .fillMaxHeight()
        .padding(start = 10.dp)) {
        Text(
            text = name,
            color = Color.White,
            modifier = Modifier.padding(start = 5.dp)
        )
    }
}