package com.example.navigationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationdemo.screens.AnimateState
import com.example.navigationdemo.screens.AnimateVisibility
import com.example.navigationdemo.screens.BoxComposable
import com.example.navigationdemo.screens.CascadeLayout
import com.example.navigationdemo.screens.CompositionLocal
import com.example.navigationdemo.screens.CustomLayout
import com.example.navigationdemo.screens.ListComposable
import com.example.navigationdemo.screens.ModifierUse
import com.example.navigationdemo.screens.MyComposable
import com.example.navigationdemo.screens.Practice
import com.example.navigationdemo.screens.RowColumn
import com.example.navigationdemo.ui.AppScaffold
import com.example.navigationdemo.ui.theme.NavigationDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    AppScaffold(navController = navController) {
        NavHost(navController = navController, startDestination = NavRoutes.Practice.route) {
            composable(NavRoutes.Practice.route) { Practice() }
            composable(NavRoutes.Composable.route) { MyComposable(navController = navController) }
            composable(NavRoutes.ModifierUse.route) { ModifierUse() }
            composable(NavRoutes.CompositionLocal.route) { CompositionLocal() }
            composable(NavRoutes.CascadeLayout.route) { CascadeLayout() }
            composable(NavRoutes.RowColumn.route) { RowColumn() }
            composable(NavRoutes.BoxComposable.route) { BoxComposable() }
            composable(NavRoutes.CustomLayout.route) { CustomLayout() }
            composable(NavRoutes.ListComposable.route) { ListComposable() }
            composable(NavRoutes.AnimateVisibility.route) { AnimateVisibility() }
            composable(NavRoutes.AnimateState.route) { AnimateState() }
        }
    }
}
