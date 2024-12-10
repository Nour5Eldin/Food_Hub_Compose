package com.noureldin.foodhub

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.noureldin.foodhub.data.FoodApi
import com.noureldin.foodhub.ui.features.auth.AuthScreen
import com.noureldin.foodhub.ui.features.auth.login.SignInScreen
import com.noureldin.foodhub.ui.features.auth.signup.SignUpScreen
import com.noureldin.foodhub.ui.navigation.AuthScreen
import com.noureldin.foodhub.ui.navigation.Home
import com.noureldin.foodhub.ui.navigation.Login
import com.noureldin.foodhub.ui.navigation.SignUp
import com.noureldin.foodhub.ui.theme.FoodHubTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var showSplashScreen = true
    @Inject
    lateinit var foodApi: FoodApi
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                showSplashScreen
            }

            setOnExitAnimationListener{screen->
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    0.5f,
                    0f
                )
                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    0.5f,
                    0f
                )
                zoomX.duration = 500
                zoomY.duration = 500
                zoomX.interpolator = OvershootInterpolator()
                zoomY.interpolator = OvershootInterpolator()
                zoomX.doOnEnd {
                    screen.remove()
                }
                zoomY.doOnEnd {
                    screen.remove()
                }
                zoomY.start()
                zoomX.start()
            }
        }
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            FoodHubTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   Box(modifier = Modifier.padding(innerPadding))
                   val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = AuthScreen,
                        modifier = Modifier.padding(innerPadding)){
                        composable<SignUp> {
                            SignUpScreen(navController)
                        }
                        composable<AuthScreen>{
                            AuthScreen(navController)
                        }
                        composable<Login>{
                            SignInScreen(navController)
                        }
                        composable<Home>{
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Green)){
                            }
                        }


                    }
                }
            }
        }
        if (::foodApi.isInitialized){
            Log.d("MainActivity", "FoodApi initialized")
        }
        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
            showSplashScreen = false
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodHubTheme {
        Greeting("Android")
    }
}