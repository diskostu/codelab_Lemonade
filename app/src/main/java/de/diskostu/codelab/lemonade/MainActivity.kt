package de.diskostu.codelab.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.diskostu.codelab.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    var currentStep by remember { mutableStateOf(1) }

    var squeezeCount by remember {
        mutableStateOf(0)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (currentStep) {
            1 -> {
                TextAndImage(
                    stringResourceId = R.string.tap_the_lemon_tree,
                    imageResourceId = R.drawable.lemon_tree,
                    contentDescriptionId = R.string.desc_lemon_tree,
                    clickAction = {
                        currentStep = 2
                        squeezeCount = (2..4).random()
                    })
            }

            2 -> {
                TextAndImage(
                    stringResourceId = R.string.tap_the_lemon,
                    imageResourceId = R.drawable.lemon_squeeze,
                    contentDescriptionId = R.string.desc_lemon,
                    clickAction = {
                        squeezeCount--
                        if (squeezeCount == 0) {
                            currentStep = 3
                        }
                    })
            }

            3 -> {
                TextAndImage(
                    stringResourceId = R.string.tap_the_glass,
                    imageResourceId = R.drawable.lemon_drink,
                    contentDescriptionId = R.string.desc_glass_of_lemonade,
                    clickAction = {
                        currentStep = 4
                    })
            }

            else -> {
                TextAndImage(
                    stringResourceId = R.string.tap_the_empty_glass,
                    imageResourceId = R.drawable.lemon_restart,
                    contentDescriptionId = R.string.desc_empty_glass,
                    clickAction = {
                        currentStep = 1
                    })
            }
        }
    }
}


@Composable
fun TextAndImage(
    modifier: Modifier = Modifier,
    stringResourceId: Int,
    imageResourceId: Int,
    contentDescriptionId: Int,
    clickAction: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = stringResourceId))
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = stringResource(id = contentDescriptionId),
            modifier = modifier.clickable(onClick = clickAction)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TextAndImagePreview() {
    TextAndImage(
        stringResourceId = R.string.tap_the_lemon_tree,
        imageResourceId = R.drawable.lemon_tree,
        contentDescriptionId = R.string.desc_lemon_tree,
        clickAction = { }
    )
}