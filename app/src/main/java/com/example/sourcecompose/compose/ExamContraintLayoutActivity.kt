package com.example.sourcecompose.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sourcecompose.R
import com.example.sourcecompose.compose.theme.EmptyComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmptyComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier) {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWith = configuration.screenWidthDp.dp
}

@Composable
fun Ingredient(@DrawableRes icon: Int
               ,value: Int,
                unit: String?,
                name:String,
               modifier: Modifier) {

    val backgroundColor = Color(0xFFFEF9E4)
    val borderColor = Color(0xFFFEF9E4).copy(alpha = 0.7f)

    ConstraintLayout(
        modifier = modifier
            .background(color = backgroundColor, shape = CircleShape)
            .border(BorderStroke(width = 1.dp, color = borderColor))
    ) {
        val horizontalGuideLine50 = createGuidelineFromTop(0.5f)

        val imgIcon = createRef()
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.constrainAs(imgIcon) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(horizontalGuideLine50)
                height = Dimension.fillToConstraints
            },
            contentScale = ContentScale.FillHeight
        )

        val (tvValue, tvUnit, tvName) = createRefs()

        val verticalGuideLine = createGuidelineFromStart(0.5f)
        val valueTextColor = Color(0xFFFB7DBA)
        Text(text = value.toString(), style = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            lineHeight = 14.sp,
            color = valueTextColor,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        ),
        modifier = Modifier.constrainAs(tvValue){
            top.linkTo(horizontalGuideLine50, margin = 2.dp)
            end.linkTo(verticalGuideLine, margin = 2.dp)
        })


        unit?.let {
            Text(text = it, style = TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                lineHeight = 14.sp,
                color = valueTextColor
            ),
            modifier = Modifier.constrainAs(tvUnit){
                    top.linkTo(tvValue.bottom, margin = 2.dp)
                    end.linkTo(tvValue.end)
            })
        }

        val bottomBarrier = createBottomBarrier(tvUnit, tvName)
        val endGuideLine = createGuidelineFromEnd(0.2f)
        Text(text = name, style = TextStyle(
            color = Color(0xFF1E2742),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 14.sp
        ), modifier = Modifier.constrainAs(tvName){
                start.linkTo(verticalGuideLine)
                bottom.linkTo(bottomBarrier)
                top.linkTo(tvValue.top)
                end.linkTo(endGuideLine)
                width = Dimension.fillToConstraints
            },
            maxLines = 2,
            textAlign = TextAlign.Start
        )
    }

}

@Preview(showBackground = true)
@Composable
fun IngredientPreview() {
    Row {
        Ingredient(
            icon = R.drawable.icon_lemond,
            modifier = Modifier.size(150.dp),
            unit = "ml",
            value = 30 ,
            name = "Lemon juice"
        )
    }
}
/*

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreview() {
    EmptyComposeTheme {

    }
}*/
