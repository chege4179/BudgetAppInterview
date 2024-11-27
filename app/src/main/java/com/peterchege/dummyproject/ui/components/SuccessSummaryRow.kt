package com.peterchege.dummyproject.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SuccessSummaryRow(
    expenseName: String,
    expenseAmount: String,
    isValueHighlighted: Boolean = false,
    isValueBold: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 30.dp)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = expenseName,
            style = TextStyle(
                color = colorScheme.secondary,
                fontWeight = if (isValueBold) FontWeight.Bold else FontWeight.Normal,
                fontSize = 14.sp,
                textAlign = TextAlign.Start

            )
        )
        Text(
            text = expenseAmount,
            style = TextStyle(
                color = if (isValueHighlighted) colorScheme.primary else colorScheme.secondary,
                fontWeight = if (isValueBold) FontWeight.Bold else FontWeight.Normal,
                fontSize = if (isValueHighlighted) 15.sp else 14.sp,
                textAlign = TextAlign.End
            )
        )

    }
}