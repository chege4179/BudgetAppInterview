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
import com.peterchege.dummyproject.core.database.entity.ExpenseEntity

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
            .clickable {
                onTransactionNavigate(transaction.transactionId)
            },
        shape = RoundedCornerShape(15),
    ) {
        Row(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.onBackground)
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ){

                Spacer(modifier = Modifier.width(11.dp))
                Image(
                    modifier = Modifier
                        .width(48.dp)
                        .height(48.dp),
                    painter = rememberImagePainter(
                        data = generateAvatarURL(transaction.transactionName),
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
                        text = transaction.transactionName,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(color = MaterialTheme.colorScheme.primary)

                    )
                    Text(
                        text = transaction.transactionCreatedOn,
                        style = TextStyle(color = MaterialTheme.colorScheme.primary)
                    )

                }
                Text(
                    text = "KES ${transaction.transactionAmount} /=",
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(color = MaterialTheme.colorScheme.primary)
                )
            }
            IconButton(onClick = {
                onTransactionNavigate(transaction.transactionId)
            }) {
                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    tint = Color.Black,
                    contentDescription = "More "
                )

            }
        }

    }

}