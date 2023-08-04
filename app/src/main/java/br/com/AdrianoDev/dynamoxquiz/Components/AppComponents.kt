package br.com.AdrianoDev.dynamoxquiz.Components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.AdrianoDev.dynamoxquiz.Model.nome
import br.com.AdrianoDev.dynamoxquiz.R
import br.com.AdrianoDev.dynamoxquiz.theme.BgColor
import br.com.AdrianoDev.dynamoxquiz.theme.Primary
import br.com.AdrianoDev.dynamoxquiz.theme.Secondary
import br.com.AdrianoDev.dynamoxquiz.theme.componentShapes


import kotlinx.coroutines.delay


@Composable
fun LogoDynamox() {

    Image(
        painter = painterResource(id = R.drawable.dynamoxlogo),
        contentDescription = "Logo do App",
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 200.dp)
    )
}


@Composable
fun NormalTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ), color = colorResource(id = R.color.colorText),
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ), color = colorResource(id = R.color.colorPrimary),
        textAlign = TextAlign.Center
    )
}


@Composable
fun MyTextFieldComponent(
    labelValue: String, painterResource: Painter,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean = false,
//  dao: nomeDao

) {

    val textValue = remember {
        mutableStateOf("")
    }
    val localFocusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            backgroundColor = BgColor
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextChanged(it)

//                dao.insert(nome(type = "algum_tipo", nome = it)) // Substitua "algum_tipo" pelo valor apropriado

        },

        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = !errorStatus
    )
}


//// Custom VisualTransformation to hide the formatting characters
//class CustomVisualTransformation : VisualTransformation {
//    override fun filter(text: AnnotatedString): TransformedText {
//        val originalText = text.text
//        val formattedText = formatCpf(originalText)
//        val offsetTranslator = object : OffsetMapping {
//            override fun originalToTransformed(offset: Int): Int {
//                // Mapeia o offset do texto original para o texto formatado
//                if (offset <= 11) return offset
//                if (offset <= 13) return offset + 1
//                if (offset <= 16) return offset + 2
//                return offset + 3
//            }
//
//            override fun transformedToOriginal(offset: Int): Int {
//                // Mapeia o offset do texto formatado para o texto original
//                if (offset <= 11) return offset
//                if (offset <= 13) return offset - 1
//                if (offset <= 16) return offset - 2
//                return offset - 3
//            }
//        }
//
//        return TransformedText(AnnotatedString(formattedText), offsetTranslator)
//    }
//}


@Composable
fun ButtonComponent(value: String, onButtonClicked: () -> Unit, isEnabled: Boolean = false) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        onClick = {
            onButtonClicked.invoke()
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp),
        enabled = isEnabled
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

        }

    }
}

@Composable
fun Resposta(
    value: String,
    onButtonClicked: () -> Unit,
    isEnabled: Boolean = false,
    isButtonEnabled: Boolean = true // Novo argumento para controle da opacidade do botão
) {
    val opacity = if (isEnabled && isButtonEnabled) {
        1f // Mantém a opacidade normal se isEnabled e isButtonEnabled forem verdadeiros
    } else {
        0.5f // Reduz a opacidade caso algum dos dois seja falso
    }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        onClick = {
            onButtonClicked.invoke()
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp),
        enabled = isEnabled && isButtonEnabled // Combina isEnabled e isButtonEnabled para habilitar ou desabilitar o botão
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                color = Color.White.copy(alpha = opacity), // Aplica a opacidade ao texto do botão
                fontWeight = FontWeight.Bold
            )

        }

    }
}







