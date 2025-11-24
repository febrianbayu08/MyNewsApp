package com.example.mynewsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mynewsapp.ui.theme.MyNewsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNewsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewsAppNavigation()
                }
            }
        }
    }
}

@Composable
fun NewsAppNavigation() {
    val navController = rememberNavController()
    val viewModel: NewsViewModel = viewModel()

    NavHost(navController = navController, startDestination = "dashboard") {
        composable("dashboard") {
            DashboardScreen(
                viewModel = viewModel,
                onItemClicked = { article ->
                    viewModel.selectedArticle = article
                    navController.navigate("detail")
                }
            )
        }

        composable("detail") {
            val article = viewModel.selectedArticle
            if (article != null) {
                DetailScreen(
                    article = article,
                    onNavigateUp = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}