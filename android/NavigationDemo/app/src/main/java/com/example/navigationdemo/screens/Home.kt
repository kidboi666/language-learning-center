package com.example.navigationdemo.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.navigationdemo.NavRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController) {

    fun onClick(route: String) {
        navController.navigate(route)
    }

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button({ onClick(NavRoutes.CompositionLocal.route) }) { Text("CompositionLocal") }
            Button({ onClick(NavRoutes.ModifierUse.route) }) { Text("ModifierUse") }
            Button({ onClick(NavRoutes.CascadeLayout.route) }) { Text("CascadeLayout") }
            Button({ onClick(NavRoutes.RowColumn.route) }) { Text("RowColumn") }
            Button({ onClick(NavRoutes.BoxComposable.route) }) { Text("BoxComposable") }
            Button({ onClick(NavRoutes.CustomLayout.route) }) { Text("CustomLayout") }
            Button({ onClick(NavRoutes.ListComposable.route) }) { Text("ListComposable") }
        }
    }
}
