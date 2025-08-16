package com.example.navigationdemo


sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object ModifierUse : NavRoutes("modifierUse")
    object CompositionLocal : NavRoutes("compositionLocal")
    object CascadeLayout : NavRoutes("cascadeLayout")
    object RowColumn : NavRoutes("rowColumn")
    object BoxComposable : NavRoutes("boxComposable")
    object CustomLayout : NavRoutes("customLayout")
    object ListComposable : NavRoutes("listComposable")
    object AnimateVisibility : NavRoutes("animateVisibility")
    object AnimateState : NavRoutes("animateState")
}