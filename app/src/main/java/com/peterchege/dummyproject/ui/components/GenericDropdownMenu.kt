package com.peterchege.dummyproject.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> GenericDropDownMenu(
    items: List<T>,
    selectedOption: T? = null,
    isLoading:Boolean = false,
    onClick: (T) -> Unit,
    dropDownTitle: String,
    title: (T) -> String,
    applyFullHeight: Boolean = false,
    emptyMessageText: String = "Nothing found here ",
    enableMarque: Boolean = false,
    filterFunction: (query: String, items: List<T>) -> List<T> = { query, items -> items },
) {
    val scope = rememberCoroutineScope()
    var searchTerm by remember {
        mutableStateOf("")
    }
    var searchResults by remember {
        mutableStateOf(emptyList<T>())
    }
    LaunchedEffect(key1 = searchTerm) {
        if (searchTerm.isNotBlank()) {
            searchResults = filterFunction(searchTerm, items)
        }
    }
    val itemsToBeDisplay = remember(key1 = searchTerm, key2 = searchResults, key3 = items) {
        if (searchTerm.isBlank()) items else searchResults
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .background(Color.Transparent)
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxHeight(if (applyFullHeight) 1f else 0.7f)
            .animateContentSize(),
    ) {
        Text(
            text = dropDownTitle,
            style = TextStyle(
                color = colorScheme.secondary,
                fontSize = 18.sp,
                fontFamily = typography.bodyLarge.fontFamily
            )
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            if (isLoading) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier,
                        )
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .fillMaxWidth(),
                            text = "Please wait...",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = colorScheme.secondary,
                                fontSize = 14.sp,
                                fontFamily = typography.bodyLarge.fontFamily
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }else{
                if (itemsToBeDisplay.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = emptyMessageText,
                                style = TextStyle(
                                    color = colorScheme.secondary,
                                    fontSize = 18.sp,
                                    fontFamily = typography.bodyLarge.fontFamily
                                )
                            )
                        }
                    }
                } else {
                    items(items = itemsToBeDisplay) { item -> // Use filteredCountries list
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    border = BorderStroke(width = 1.dp, color = Color.Gray),
                                    shape = MaterialTheme.shapes.small
                                )
                                .padding(8.dp)
                                .animateItemPlacement()
                                .clickable {
                                    onClick(item)
                                }
                        ) {
                            Row(
                                modifier = Modifier,
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    val textModifier = if (enableMarque)
                                        Modifier
                                            .padding(horizontal = 8.dp)
                                            .fillMaxWidth(0.9f)
                                            .basicMarquee()
                                    else Modifier
                                        .padding(horizontal = 8.dp)
                                        .fillMaxWidth(0.9f)
                                    Text(
                                        modifier = textModifier,
                                        text = title(item),
                                        style = TextStyle(
                                            color = colorScheme.secondary,
                                            fontSize = 18.sp,
                                            fontFamily = typography.bodyLarge.fontFamily
                                        ),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )

                                }
                                RadioButton(
                                    selected = selectedOption == item,
                                    onClick = {
                                        scope.launch {
                                            onClick(item)
                                        }
                                    },
                                    enabled = true,
                                    modifier = Modifier
                                        .size(50.dp)
                                        .padding(10.dp),
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = colorScheme.primary,
                                        unselectedColor = Color.Gray,
                                    ),
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}
