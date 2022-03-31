package com.ozkan.sayitahminkotlin

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson


@Composable
fun PageGame(navController: NavController,rnd:Int) {
    var kalanHak = remember { mutableStateOf(10)}
    var help = remember { mutableStateOf("")}
    var puan = remember { mutableStateOf(100)}
    var txtValue = remember { mutableStateOf("0")}
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Kalan hak: ${kalanHak.value}", color = Color.Red, fontSize = 35.sp, fontWeight = FontWeight.Bold)
        Text(text = "Yardım: ${help.value}", fontSize = 25.sp)
        Text(text = rnd.toString())
        Text(text = "Puan: ${puan.value}", fontSize = 25.sp,)
        TextField(
            value = txtValue.value ,
            onValueChange = {txtValue.value=it },
            label = { Text(text = "Tahmin")},
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Gray,
                textColor = Color.Black,
                focusedLabelColor = Color.LightGray,
                focusedIndicatorColor = Color.DarkGray
            ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(onClick = {
            kalanHak.value--
            if(kalanHak.value>0){
                if(txtValue.value.isNotEmpty()){
                    if(txtValue.value.toInt() ==rnd){
                        fct("Kazandiniz","right",puan.value, navController)
                    }
                    else if (txtValue.value.toInt()<rnd){
                        help.value = "Artir"
                    }
                    else if(txtValue.value.toInt()>rnd){
                        help.value = "Düsür"
                    }

                }
                else{
                    Toast.makeText(context,"Deger giriniz",Toast.LENGTH_SHORT).show()
                }
                puan.value = puan.value - 10
            }
            else{
                puan.value = puan.value - 10
                fct("Kaybetiniz","false2",puan.value, navController)
            }
        },colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Blue,
            contentColor = Color.White
        ), modifier = Modifier.size(250.dp,50.dp)) {
            Text(text = "Tahmin Et", fontSize = 18.sp)
        }
    }
}

fun fct(statusS:String,picturString:String,puan:Int,navController: NavController){
    var status = Status(statusS,picturString, puan)
    var statuseJson = Gson().toJson(status)
    navController.navigate("page3/$statuseJson")

}