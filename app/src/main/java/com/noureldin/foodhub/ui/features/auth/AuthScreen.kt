package com.noureldin.foodhub.ui.features.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.noureldin.foodhub.R
import com.noureldin.foodhub.ui.theme.orange
@Composable
fun AuthScreen() {
    val imageSize = remember {
        mutableStateOf(IntSize.Zero)
    }

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color.Transparent,
            colorResource(R.color.darkBlue).copy(alpha = 0.9f)
        ),
        startY = imageSize.value.height.toFloat() / 3,
        endY = Float.POSITIVE_INFINITY
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned { coordinates ->
                    imageSize.value = coordinates.size
                },
            contentScale = ContentScale.Crop // Ensures image covers the screen proportionally
        )

        // Gradient Overlay
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(brush = gradientBrush) // Apply the gradient here
        )

        // Skip Button
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .padding(top = 24.dp) // Add padding to make sure it's not cropped on small screens
        ) {
            Text(text = stringResource(id = R.string.skip), color = orange)
        }

        // Welcome Text Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 120.dp, start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.welcome),
                color = colorResource(R.color.darkBlue),
                fontSize = 48.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.food_hub),
                color = orange,
                fontSize = 48.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.food_hub_desc),
                color = colorResource(R.color.darkBlue),
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        // Bottom Buttons Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Sign In Section with 2 lines in between the text
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    Divider(
                        color = Color.White,
                        thickness = 1.dp,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }
                Text(
                    text = stringResource(id = R.string.sign_in_title),
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Box(modifier = Modifier.weight(1f)) {
                    Divider(
                        color = Color.White,
                        thickness = 1.dp,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                }
            }

            // Facebook and Google Buttons Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Facebook Button
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(32.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_facebook),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.sign_with_facebook),
                        color = Color.Black
                    )
                }

                // Google Button
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(32.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.sign_with_google),
                        color = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Email Button
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(32.dp),
                border = BorderStroke(1.dp, Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.sign_with_email), color = Color.White)
            }

            // Already Have Account Text
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.alread_have_account),
                    color = Color.White
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    AuthScreen()
}