package br.com.AdrianoDev.dynamoxquiz.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val componentShapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(0.dp)
)

//val Padding1 = if (screenHeight > 630.dp) {
//    30.dp // Se a tela for maior que 600 dp
//}else if (screenHeight < 585.dp){
//    20.dp
//}else{
//    0.dp
//}
//
//val Padding3 = if (screenHeight > 630.dp) {
//    15.dp // Se a tela for maior que 600 dp
//}else if (screenHeight < 585.dp){
//    5.dp
//}else{
//    0.dp
//}
//
//val Pading4 = if (screenHeight > 630.dp) {
//    10.dp // Se a tela for maior que 600 dp
//}else if (screenHeight < 585.dp){
//    5.dp
//}else{
//    0.dp
//}