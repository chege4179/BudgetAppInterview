package com.peterchege.dummyproject.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.sp


data class Slice(val value: Float, val color: Color, val text: String = "")


@Composable
fun StackedBar(modifier: Modifier, slices: List<Slice>) {

    val textMeasurer = rememberTextMeasurer()

    val textLayoutResults = remember {
        mutableListOf<TextLayoutResult>().apply {
            slices.forEach {
                val textLayoutResult: TextLayoutResult =
                    textMeasurer.measure(
                        text = AnnotatedString(it.text),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    )
                add(textLayoutResult)
            }
        }
    }

    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        var currentX = 0f
        slices.forEachIndexed { index: Int, slice: Slice ->
            val width = (slice.value) / 100f * canvasWidth

            // Draw Rectangles
            drawRect(
                color = slice.color, topLeft = Offset(currentX, 0f), size = Size(
                    width,
                    canvasHeight
                )
            )

            // Draw Text
            val textSize = textLayoutResults[index].size
            val style = textLayoutResults[index].layoutInput.style
            drawText(
                textMeasurer = textMeasurer, text = slice.text, topLeft = Offset(
                    x = currentX + (width - textSize.height) / 2,
                    y = (canvasHeight - textSize.height) / 2
                ),
                style = style
            )

            // Update start position of next rectangle
            currentX += width
        }
    }
}