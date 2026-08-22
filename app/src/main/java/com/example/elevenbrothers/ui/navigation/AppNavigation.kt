package com.example.elevenbrothers.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.elevenbrothers.R
import com.example.elevenbrothers.ui.theme.BrandGold
import com.example.elevenbrothers.ui.theme.BrandGoldLight
import com.example.elevenbrothers.ui.theme.BrandNavy
import com.example.elevenbrothers.ui.theme.BrandNavyDark

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object About : Screen("about", "About", Icons.Default.Info)
    object Squad : Screen("squad", "Squad", Icons.Default.Group)
    object Players : Screen("players", "Players", Icons.Default.Person)
    object Venues : Screen("venues", "Venues", Icons.Default.LocationOn)
    object Join : Screen("join", "Join", Icons.Default.PersonAdd)
}

@Composable
fun AppBottomNavBar(
    currentScreen: Screen,
    onNavigate: (Screen) -> Unit
) {
    val items = listOf(
        Screen.About,
        Screen.Squad,
        Screen.Players,
        Screen.Venues,
        Screen.Join
    )

    NavigationBar(
        // Matching bg-brand-navy-dark
        containerColor = BrandNavyDark,
        contentColor = Color.White
    ) {
        items.forEach { screen ->
            val selected = currentScreen == screen
            val isJoin = screen == Screen.Join

            NavigationBarItem(
                selected = selected,
                onClick = { onNavigate(screen) },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.title
                    )
                },
                label = {
                    Text(
                        text = screen.title,
                        fontSize = 12.sp,
                        fontWeight = if (isJoin) FontWeight.Bold else FontWeight.Medium
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    // Brand Gold active accents; gold highlight for "Join"
                    selectedIconColor = if (isJoin) BrandGoldLight else BrandGold,
                    selectedTextColor = if (isJoin) BrandGoldLight else BrandGold,
                    indicatorColor = BrandNavy,
                    unselectedIconColor = if (isJoin) BrandGold else Color.White.copy(alpha = 0.7f),
                    unselectedTextColor = if (isJoin) BrandGold else Color.White.copy(alpha = 0.7f)
                )
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.About) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Replace R.drawable.ic_logo with your logo resource
                        Image(
                            painter = painterResource(id = R.drawable.ic_logo),
                            contentDescription = "11 Brothers FC crest",
                            modifier = Modifier.size(36.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "11 BROTHERS FC",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.05.sp
                            ),
                            color = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BrandNavyDark,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            AppBottomNavBar(
                currentScreen = currentScreen,
                onNavigate = { screen -> currentScreen = screen }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${currentScreen.title} Screen Content",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}