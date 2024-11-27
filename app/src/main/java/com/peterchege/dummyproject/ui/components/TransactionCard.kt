package com.peterchege.dummyproject.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.peterchege.dummyproject.core.database.entity.ExpenseEntity
import com.peterchege.dummyproject.core.util.formatCurrency
import com.peterchege.dummyproject.core.util.generateAvatarURL
import com.peterchege.dummyproject.core.util.safeToDouble

@ExperimentalCoilApi
@Composable
fun TransactionCard(
    transaction: ExpenseEntity,
    onTransactionNavigate: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .height(70.dp)
            .clickable {},
        shape = RoundedCornerShape(15),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Spacer(modifier = Modifier.width(11.dp))
                Image(
                    modifier = Modifier
                        .width(48.dp)
                        .height(48.dp),
                    painter = rememberImagePainter(
                        data = generateAvatarURL(transaction.expenseName),
                        builder = {
                            crossfade(true)
                        },
                    ),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .width(140.dp)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = transaction.expenseName,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(color = MaterialTheme.colorScheme.primary)

                    )

                }
                Text(
                    text = formatCurrency(amount = transaction.expenseAmount.safeToDouble(), "KES"),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(color = MaterialTheme.colorScheme.primary)
                )
            }
        }

    }

}