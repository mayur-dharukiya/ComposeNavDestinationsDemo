package com.plcoding.composenavdestinationsdemo

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable

fun Navigation()
{
    val navController= rememberNavController()
    
    NavHost(navController = navController, startDestination =Screen.MainScreen.route){


        composable(route=Screen.MainScreen.route)
        {
                MainScreen(navController = navController)
        }
        composable(
            route=Screen.DetailScreen.route+"/{name}",
            arguments = listOf(

                navArgument("name")
                {
                    type= NavType.StringType
                    defaultValue="Mayur"
                    nullable=true
                }
            )
        ){entry->
            entry.arguments?.getString("name")?.let { DetailScreen(name = it) }
        }


    }

    
}

@Composable
fun MainScreen(navController: NavController)
{
    var text by remember{

        mutableStateOf("")
    }
    Column (
        verticalArrangement=Arrangement.Center,
        modifier= Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
            ){
                     TextField(value = text, onValueChange ={
                         text=it
                     },
                     modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {

                         navController.navigate(Screen.DetailScreen.route)

        },
        modifier =Modifier.align(Alignment.End) ) {

            Text(text="To Details Screen")
        }
        
    }
}

@Composable
fun DetailScreen(name:String)
{
    Box(
        modifier = Modifier.fillMaxSize()
    )
    {
        Text(text = "Hello, $name")
    }
}