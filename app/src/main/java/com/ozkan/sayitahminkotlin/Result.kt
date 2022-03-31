package com.ozkan.sayitahminkotlin

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun PageResult(status: Status) {

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        val activity = (LocalContext.current as Activity)
        Text(text = status.status, fontSize = 35.sp, fontWeight = FontWeight.Bold,color= Color.Black)
        Image(bitmap = ImageBitmap.imageResource(//
            id = activity.resources.getIdentifier(
                status.statusPicture,
                "drawable",
                activity.packageName
            )
        ), contentDescription = "",Modifier.size(256.dp,256.dp))
        Text(text = "Puaniniz: ${status.puan}", fontSize = 25.sp, fontWeight = FontWeight.Bold,color = Color.Gray)
    }
}