 package com.example.unocompose


import android.content.Context
import android.net.nsd.NsdManager
import android.os.Bundle
import android.util.Log

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.unocompose.screens.FindLobbyScreen

import com.example.unocompose.screens.GameScreen
import com.example.unocompose.screens.LobbyScreen
import com.example.unocompose.screens.MainScreen
import com.example.unocompose.viewmodels.LobbyScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


 class MainActivity : ComponentActivity() {



     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         val nsdManager = getSystemService(Context.NSD_SERVICE) as NsdManager

        Log.d("Tag", "$nsdManager")

//        WindowCompat.setDecorFitsSystemWindows(window, false)
//        TODO()
         setContent {
             Log.d("NSD", "starting activity")


             val navController = rememberNavController()
             NavHost(
                 navController = navController,
                 startDestination = "mainScreen"
             ) {
                 composable("mainScreen") {
                     MainScreen(navController = navController)
                 }
                 composable("createLobbyScreen") {
                     LobbyScreen(navController = navController, isHost = true, nsdManager = nsdManager)
                 }
                 composable("findLobbyScreen") {
                     FindLobbyScreen(navController = navController, nsdManager = nsdManager)
                 }
                 composable("gameScreen") {
                     GameScreen(navController = navController)
                 }
             }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}