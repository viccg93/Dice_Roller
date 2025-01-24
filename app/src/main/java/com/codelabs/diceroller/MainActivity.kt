package com.codelabs.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
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
import com.codelabs.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollerTheme {
                DiceRollerApp()
            }
        }
    }
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    //por defecto los composables son stateless
    //para almacenar result en memoria, se le asigna el delegate remember
    //y remember puede recibir una lambda donde se le puede pasar mutableStateOf
    //mutableStateOf retorna un observable
    var result: Int by remember { mutableStateOf(1) }
    //se asume que este codigo se va a ejecutar cada que se modifique result
    //y que por defecto es 1
    val imageResource = when (result){
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = result.toString()
        )
        Spacer(modifier = Modifier.height(16.dp))
        //random es una funcion de IntRange
        Button(onClick = {result = (1..6).random()}) {
            Text(
                text = stringResource(R.string.roll)
            )
        }
    }
}

//esta funcion es la app en si misma
@Preview(showBackground = true)
@Composable
fun DiceRollerApp() {
    //wrapContentSize() especifica que el contenido debe de ser al menos del mismo tamaño que el contenido
    //fillMaxSize hace que el espacio sea el total del area disponible
    //en ese escenario se puede pasar un objeto Alignment a wrapContentSize() para indicar como se alinearan
    //los elementos children dentro del espacio disponible
    //Alignment dentro de ese metodo alinea de manera vertical y horizontal
    DiceWithButtonAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}