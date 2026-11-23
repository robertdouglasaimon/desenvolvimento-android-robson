package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuadrantesScreen()
                }
            }
        }
    }
}

@Composable
fun QuadrantesScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Primeira linha
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Quadrante(
                titulo = "Text composable",
                descricao = "Displays text and follows the recommended Material Design guidelines.",
                cor = Color(0xFFEADDFF),
                modifier = Modifier.weight(1f)
            )

            Quadrante(
                titulo = "Image composable",
                descricao = "Creates a composable that lays out and draws a given Painter class object.",
                cor = Color(0xFFD0BCFF),
                modifier = Modifier.weight(1f)
            )
        }

        // Segunda linha
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Quadrante(
                titulo = "Row composable",
                descricao = "A layout composable that places its children in a horizontal sequence.",
                cor = Color(0xFFB69DF8),
                modifier = Modifier.weight(1f)
            )

            Quadrante(
                titulo = "Column composable",
                descricao = "A layout composable that places its children in a vertical sequence.",
                cor = Color(0xFFF6EDFF),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun Quadrante(
    titulo: String,
    descricao: String,
    cor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(cor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Primeiro texto: título em negrito
        Text(
            text = titulo,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Segundo texto: tamanho padrão
        Text(
            text = descricao,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuadrantesScreenPreview() {
    MyApplicationTheme {
        QuadrantesScreen()
    }
}
