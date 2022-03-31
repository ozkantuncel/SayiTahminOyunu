package com.ozkan.sayitahminkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.ozkan.sayitahminkotlin.ui.theme.SayiTahminKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SayiTahminKotlinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Pages()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SayiTahminKotlinTheme {
        //Pages()
    }
}

@Composable
fun Pages(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "mainpage" ){
        composable("mainpage"){
            PageMain(navController)
        }
        composable("page2/{rnd}",
        arguments = listOf(
            navArgument("rnd"){type = NavType.IntType}
        )){
            val rnd = it.arguments?.getInt("rnd")!!
            PageGame(navController,rnd)
        }


        composable("page3/{nesne}",
            arguments = listOf(
                navArgument("nesne"){type = NavType.StringType}
        )){
            val json = it.arguments?.getString("nesne")
            var nesne =Gson().fromJson(json,Status::class.java)
            PageResult(nesne)
        }
    }
}

@Composable
fun PageMain(navController: NavController) {

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Tahmin Oyunu", fontSize = 50.sp, fontWeight = FontWeight.Bold)
        Image(painter = painterResource(id = R.drawable.lucky), contentDescription = "")
        Button(onClick = {
            val rnd = (0..100).random()
            navController.navigate("page2/$rnd")

        }) {
            Text(text = "Oyuna başla")
        }
    }
}



