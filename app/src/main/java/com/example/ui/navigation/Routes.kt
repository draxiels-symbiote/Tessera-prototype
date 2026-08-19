package com.example.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Closet : Screen("closet")
    object Archive : Screen("archive")
    object Settings : Screen("settings")
    object HairSetup : Screen("hair_setup")
    object AddItemCapture : Screen("add_item_capture")
    object AddItemProcessing : Screen("add_item_processing")
    object AddItemReview : Screen("add_item_review")
    object ItemDetail : Screen("item_detail/{itemId}") {
        fun createRoute(itemId: Long) = "item_detail/$itemId"
    }
    object StyleAnchorVibePicker : Screen("style_anchor_vibe_picker/{itemId}") {
        fun createRoute(itemId: Long) = "style_anchor_vibe_picker/$itemId"
    }
}
