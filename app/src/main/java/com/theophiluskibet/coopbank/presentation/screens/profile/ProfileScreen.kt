package com.theophiluskibet.coopbank.presentation.screens.profile

import android.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border // Added Import
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.theophiluskibet.coopbank.presentation.components.LoadingComponent
import com.theophiluskibet.coopbank.presentation.ui.theme.CoopDarkGreen
import com.theophiluskibet.coopbank.presentation.ui.theme.CoopGreen
import com.theophiluskibet.coopbank.presentation.ui.theme.DividerColor
import com.theophiluskibet.coopbank.presentation.ui.theme.LabelGray
import org.koin.androidx.compose.koinViewModel
@Composable
fun ProfileScreen(
    onBackClick: () -> Unit = {},
    onCloseClick: () -> Unit = {},
    profileViewModel: ProfileViewModel = koinViewModel()
) {
    val uiState by profileViewModel.profileUiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = true) {
        profileViewModel.getProfile()
    }

    Scaffold(
        containerColor = Color.White
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(CoopDarkGreen, CoopGreen)
                            )
                        )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileTopBar(
                    onBackClick = onBackClick,
                    onCloseClick = onCloseClick
                )

                Spacer(modifier = Modifier.height(20.dp))

                ProfileScreenContent(uiState=uiState)
            }
        }
    }
}

@Composable
fun ProfileScreenContent(modifier: Modifier = Modifier, uiState: ProfileUiState) {
    when{
        uiState.isLoading -> {
            LoadingComponent()
        }
        uiState.error.isNotEmpty() ->{
            // show error
        }
        uiState.profile != null -> {
            Surface(
                modifier = Modifier.size(120.dp),
                shape = CircleShape,
                shadowElevation = 8.dp,
                color = Color.White
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                        .border(BorderStroke(2.dp, CoopGreen), CircleShape)
                        .padding(2.dp) // Gap between green ring and image
                        .clip(CircleShape)
                ) {
                    AsyncImage(
                        model = uiState.profile.avatarUrl,
                        contentDescription = "Profile Picture",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(R.drawable.ic_menu_gallery),
                        error = painterResource(R.drawable.ic_menu_gallery)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- Subtitle ---
            Text(
                text = "Check out your profile details",
                color = LabelGray,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = DividerColor, thickness = 1.dp)
            Spacer(modifier = Modifier.height(24.dp))

            // --- Form Details ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // Personal Info
                ProfileInfoItem(label = "Name", value = buildString{
                    append("${uiState.profile.firstName} ${uiState.profile.lastName}")
                })
                Spacer(modifier = Modifier.height(16.dp))

                ProfileInfoItem(label = "Email Address", value = uiState.profile.email)
                Spacer(modifier = Modifier.height(24.dp))

                // Address Section
                Text(
                    text = "Address",
                    color = LabelGray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                ProfileInfoItem(label = "Street", value = "") // TODO: add address
                Spacer(modifier = Modifier.height(16.dp))

                ProfileInfoItem(label = "City", value = "")
                Spacer(modifier = Modifier.height(16.dp))

                ProfileInfoItem(label = "Country", value = "")
                Spacer(modifier = Modifier.height(16.dp))

                ProfileInfoItem(label = "Postal Code", value = "")

                // Bottom Padding
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
fun ProfileTopBar(
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        Text(
            text = "Profile Details",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )

        IconButton(onClick = onCloseClick) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = Color.White
            )
        }
    }
}

@Composable
fun ProfileInfoItem(label: String, value: String) {
    Column {
        Text(
            text = label,
            color = LabelGray,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            color = CoopGreen,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileDetailsScreenPreview() {
    ProfileScreen()
}
