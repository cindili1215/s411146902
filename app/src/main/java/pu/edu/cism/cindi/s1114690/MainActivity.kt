package pu.edu.cism.cindi.s1114690

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pu.edu.cism.cindi.s1114690.ui.theme.S1114690Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            S1114690Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Main()
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    val navController = rememberNavController()
    val context = LocalContext.current
    var showMenu by remember { mutableStateOf(false) }

    Column {
        TopAppBar(
            title = { Image(
                painter = painterResource(id = R.drawable.maria),
                contentDescription = "圖片",
                alpha = 0.7f,
                modifier = Modifier
                    .clip(RectangleShape)
                    .background(Color.Black)
            )
            },

            /*navigationIcon = {
                IconButton(onClick = {
                    Toast.makeText(context, "您點選了導覽圖示", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(Icons.Default.Menu, contentDescription = "Navigation icon")
                }
            },*/

            actions = {
                /*IconButton(
                    onClick = { Toast.makeText(context, "作者：李欣諦", Toast.LENGTH_SHORT).show() }
                ) {
                    Icon(Icons.Rounded.AccountBox, contentDescription = "Author")
                }*/

                IconButton(
                    onClick = { showMenu = true }
                ) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More")
                }

                DropdownMenu(
                    expanded = showMenu, onDismissRequest = { showMenu = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("簡介") },
                        onClick = { navController.navigate("JumpFirst")})

                    DropdownMenuItem(
                        text = { Text("主要機構") },
                        onClick = { navController.navigate("JumpSecond")})
                }


            }
        )

        NavHost(navController = navController, startDestination = "JumpFirst"){
            composable("JumpFirst"){
                FirstScreen(navController = navController)
            }
            composable("JumpSecond"){
                SecondScreen(navController = navController)
            }
        }
    }
}


@Composable
fun FirstScreen(navController: NavController) {
    var showTitle by remember { mutableStateOf(true) }
    var expanded by remember { mutableStateOf(true) }
    var currentImage by remember { mutableStateOf(R.drawable.service) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = if (currentImage == R.drawable.service) "瑪利亞基金會服務總覽" else "關於App作者",
            fontSize = 15.sp,
            color = Color.Blue
        )

        var alpha by remember { mutableStateOf(1f) }

        LaunchedEffect(currentImage) {
            val animatable = Animatable(0f)
            animatable.animateTo(1f, animationSpec = tween(500)) {
                alpha = value
            }
        }

        Image(
            painter = painterResource(id = currentImage),
            contentDescription = "圖片",
            modifier = Modifier
                .clip(RectangleShape)
                .fillMaxWidth()
                .height(if (expanded) 600.dp else 400.dp)
                .clickable { expanded = !expanded }
                .alpha(alpha)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            showTitle = !showTitle
            currentImage = if (currentImage == R.drawable.service) {
                R.drawable.cindi
            } else {
                R.drawable.service
            }
        }) {
            Text(text = if (currentImage == R.drawable.service) "作者：資管系李欣諦" else "服務總覽")
        }
    }
}


@Composable
fun SecondScreen(navController: NavController) {
    var selected by remember { mutableStateOf<String?>("lovehome") } // 將 selected 設置為 "lovehome"
    val context = LocalContext.current



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "主要機構",
            fontSize = 15.sp,
            color = Color.Red
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { selected = "lovehome" }) {
                Text(text = "台中市愛心家園")
            }
            Button(onClick = { selected = "campus" }) {
                Text(text = "瑪利亞學園")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (selected) {
            "lovehome" -> {
                Column {
                    Text(
                        text = "「台中市愛心家園」經市政府公開評選後，委託瑪利亞基金會經營管理，於91年啟用，整棟建築物有四個樓層，目前開辦就醫、就養、就學、就業四大領域的十項業務，提供身心障礙者全方位的服務。",
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "長按以下圖片，可以觀看愛心家園地圖",
                        color = Color.Blue,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    val image: Painter = painterResource(id = R.drawable.lovehome)
                    Image(
                        painter = image,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .pointerInput(Unit) {
                                detectDragGesturesAfterLongPress(
                                    onDrag = { change, dragAmount ->
                                        // 當拖曳進行中時的操作
                                    },
                                    onDragStart = {
                                        // 當拖曳開始時的操作
                                    },
                                    onDragEnd = {
                                        // 當拖曳結束時的操作
                                    }
                                )
                            }
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onLongPress = {
                                        // 當長按時打開 Google 地圖
                                        val uri = Uri.parse("https://maps.google.com/?q=台中市南屯區東興路一段450號")
                                        val mapIntent = Intent(Intent.ACTION_VIEW, uri).apply {
                                            setPackage("com.google.android.apps.maps")
                                        }
                                        //val context = LocalContext.current
                                        context.startActivity(mapIntent)
                                    }
                                )



                            },
                        contentScale = ContentScale.Crop
                    )
                }
            }
            "campus" -> {
                Column {
                    Text(
                        text = "「瑪利亞學園」提供重度以及極重度多重障礙者日間照顧服務，以健康照護為基礎，支持生活多面向參與及學習概念，輔助發展重度身心障礙者自我概念為最終服務目標。",
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "雙擊以下圖片，可以觀看瑪利亞學園地圖",
                        color = Color.Blue,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    val image: Painter = painterResource(id = R.drawable.campus)
                    Image(
                        painter = image,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onDoubleTap = {
                                        val uri = Uri.parse("https://maps.google.com/?q=台中市北屯區經貿東路365號")
                                        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
                                        mapIntent.setPackage("com.google.android.apps.maps")
                                        context.startActivity(mapIntent)
                                    }
                                )

                            },
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}