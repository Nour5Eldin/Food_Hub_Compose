package com.noureldin.foodhub.ui.features.auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.noureldin.foodhub.R
import com.noureldin.foodhub.ui.FoodHubTextField
import com.noureldin.foodhub.ui.GroupSocialButtons
import com.noureldin.foodhub.ui.theme.orange

@Composable
fun SignUpScreen() {
    Box(modifier = Modifier.fillMaxSize()){

        var name  by  remember {
            mutableStateOf("")
        }
        var email  by  remember {
            mutableStateOf("")
        }
        var password  by  remember {
            mutableStateOf("")
        }
        var isPasswordVisible by remember { mutableStateOf(false) }
        Image(
            painter = painterResource(id = R.drawable.auth_background), contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column (modifier = Modifier.fillMaxSize()
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally){
            Box(modifier = Modifier.weight(1f))
            Text(text = stringResource(id = R.string.sign_up),
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.size(26.dp))
            FoodHubTextField(
                value = name,
                onValueChange = {name = it},
                label = {
                    Text(text = stringResource(id = R.string.fullName), color = Color.Gray)
                },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
            )
            Spacer(modifier = Modifier.size(26.dp))
            FoodHubTextField(
                value = email,
                onValueChange = {email = it},
                label = {
                    Text(text = stringResource(id = R.string.email),color = Color.Gray)
                },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
            )
            Spacer(modifier = Modifier.size(26.dp))
            FoodHubTextField(
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(
                        text = stringResource(id = R.string.password),
                        color = Color.Gray
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    Image(
                        painter = painterResource(
                            id = if (isPasswordVisible) R.drawable.ic_eye else R.drawable.ic_eye_off // Toggle icon
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { isPasswordVisible = !isPasswordVisible } // Toggle visibility
                    )
                }
            )
            Spacer(modifier = Modifier.size(26.dp))
            Button(onClick = { }, modifier = Modifier.height(60.dp), colors = ButtonDefaults.buttonColors(containerColor = orange)) {
                Text(text = stringResource(R.string.sign_up),modifier = Modifier.padding(horizontal = 62.dp))
            }
            Spacer(modifier = Modifier.size(16.dp))
            val fullText = stringResource(id = R.string.alread_have_account) // "Already have an account? Login"
            val loginText = "Login"
            // Build the styled text as AnnotatedString
            val styledText: AnnotatedString = buildAnnotatedString {
                append(fullText.substringBefore(loginText)) // Add text before "Login"
                pushStyle(SpanStyle(color = orange)) // Apply red color to "Login"
                append(loginText) // Add "Login"
                pop() // Reset style
                append(fullText.substringAfter(loginText)) // Add text after "Login"
            }
            // Display the styled text
            BasicText(
                text = styledText,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { /* Handle click */ }
                    .fillMaxWidth(),
                style = androidx.compose.ui.text.TextStyle(
                    textAlign = TextAlign.Center
                )
            )
            GroupSocialButtons(color= Color.Black,onFacebookClick = {}) { }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}