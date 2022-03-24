package com.randys11.r11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.randys11.r11.ui.theme.R11Theme
import com.randys11.r11.view.Navigation
import com.randys11.r11.view.ToolbarWidget

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val playerViewModel by viewModels<PlayerViewModel>()
        super.onCreate(savedInstanceState)
        setContent {
            R11Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Navigation(playerViewModel)
                    if(playerViewModel.initiaLloading.value){
                        Box(modifier = Modifier.fillMaxSize()){
                            CircularProgressIndicator(Modifier.align(Alignment.Center))
                        }
                    }
                    else
                    ToolbarWidget(viewModel = playerViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    R11Theme {
        Greeting("Android")
    }
}