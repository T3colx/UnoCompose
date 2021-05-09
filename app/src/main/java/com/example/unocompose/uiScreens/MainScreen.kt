package com.example.unocompose.uiScreens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.navArgument
import com.example.unocompose.R
import com.example.unocompose.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController


val padding = 10.dp
var userName = ""

@Composable
fun MainScreen(
    navController: NavController
) {
    val systemUiController = rememberSystemUiController()

    Surface(
        color = cardBlack,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
        ) {
            val constraints = ConstraintSet {
                val logo = createRefFor("logo")
                val userMenu = createRefFor("userMenu")
                constrain(userMenu) {
                    end.linkTo(parent.end, 30.dp)
                    start.linkTo(logo.end, 40.dp)
                }
                constrain(logo) {
                    start.linkTo(parent.start, 30.dp)
                    end.linkTo(userMenu.start, 40.dp)
                    bottom.linkTo(parent.bottom, 30.dp)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }
            }

            NameField(hint = "Enter your name") {

            }
            Spacer(modifier = Modifier.size(padding))
            ConstraintLayout(constraints, modifier = Modifier
                .fillMaxSize()
                /*.background(cardWhite)*/) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .layoutId("logo")
                        /*.background(bgSecondary)*/
                ) { 
                    LogoUno()
                }
                Box(modifier = Modifier
                    .layoutId("userMenu")
                    /*.background(cardGreen)*/
                ) {
                    UserMenu(navController = navController)
                }
            }
        }
    }
}


@Composable
fun NameField(hint: String, onConfirm: (String) -> Unit = {}) {
    var text by remember { mutableStateOf("") }
    var isHintDisplayed by remember { mutableStateOf(hint != "") }
    Box(modifier = Modifier) {
        SideEffect {
            userName = text
            Log.d("name", userName)
        }
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(
                fontFamily = NunitoSans,
                color = textWhite,
                fontWeight = FontWeight.Light,
                fontSize = 20.sp),
            modifier = Modifier
                .background(Color.Transparent, CircleShape)
                .padding(horizontal = 20.dp, vertical = 5.dp)
                .onFocusChanged {
                    isHintDisplayed = it != FocusState.Active && text.isEmpty()
                }
                .wrapContentSize()
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                style = TextStyle(
                    fontFamily = NunitoSans,
                    color = textWhite,
                    fontWeight = FontWeight.Light,
                    fontSize = 20.sp,
                ),
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp)
            )
        }

    }
}



@Composable
fun LogoUno() {
    val painter = painterResource(id = R.drawable.uno_logo)
    Image(painter = painter, contentDescription = "")
}

@Composable
fun UserMenu(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyButton(
            navController = navController,
            text = "Create",
            isMainButton = true
        )
        Spacer(modifier = Modifier.padding(padding))
        MyButton(
            navController = navController,
            text = "Find",
            isMainButton = false
        )

    }
}


