package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.text.NumberFormat
import java.util.Locale

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    LayoutCalculadoraGorjeta()
                }
            }
        }
    }
}

@Composable
fun LayoutCalculadoraGorjeta() {
    var entradaValor by remember {
        mutableStateOf("")
    }

    val valorConta = entradaValor.replace(',', '.').toDoubleOrNull() ?: 0.0
    val valorGorjeta = calcularGorjeta(valorConta)

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "💰",
            fontSize = 80.sp
        )
        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier
                .padding(
                    bottom = 16.dp,
                    top = 40.dp
                )
                .align(Alignment.Start)
        )

        CampoEntradaNumero(
            valor = entradaValor,
            aoMudarValor = {
                entradaValor = it
            },
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )

        Text(
            text = stringResource(
                R.string.tip_amount,
                valorGorjeta
            ),
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(
            modifier = Modifier.height(150.dp)
        )
    }
}

@Composable
fun CampoEntradaNumero(
    valor: String,
    aoMudarValor: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = valor,
        onValueChange = aoMudarValor,
        singleLine = true,
        label = {
            Text(
                stringResource(R.string.bill_amount)
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal
        ),
        modifier = modifier
    )
}

private fun calcularGorjeta(
    valorConta: Double,
    porcentagemGorjeta: Double = 15.0
): String {
    val gorjeta = porcentagemGorjeta / 100 * valorConta

    return NumberFormat
        .getCurrencyInstance(Locale.forLanguageTag("pt-BR"))
        .format(gorjeta)
}

@Preview(showBackground = true)
@Composable
fun LayoutCalculadoraGorjetaPreview() {
    MyApplicationTheme {
        LayoutCalculadoraGorjeta()
    }
}
