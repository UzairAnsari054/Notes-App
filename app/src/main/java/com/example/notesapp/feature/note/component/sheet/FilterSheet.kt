package com.example.notesapp.feature.note.component.sheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapp.R
import com.example.notesapp.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterSheet(
    isOpened: MutableState<Boolean>,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onFilter: (sortBy: String, orderBy: String) -> Unit
) {
    val title = stringResource(id = R.string.title)
    val createdDate = stringResource(id = R.string.sort_created_date)
    val modifiedDate = stringResource(id = R.string.sort_modified_date)
    val sortByOptionList = listOf(title, createdDate, modifiedDate)

    val ascending = stringResource(id = R.string.order_ascending)
    val descending = stringResource(id = R.string.order_descending)
    val sortByOrderList = listOf(ascending, descending)

    val selectedSortBy = remember { mutableStateOf(createdDate) }
    val selectedOrderBy = remember { mutableStateOf(descending) }

    fun convertSortBy(sortBy: String): String {
        return when (sortBy) {
            title -> Constants.TITLE
            createdDate -> Constants.CREATED_AT
            modifiedDate -> Constants.UPDATED_AT
            else -> Constants.CREATED_AT
        }
    }

    fun convertOrderBy(orderBy: String): String {
        return when (orderBy) {
            ascending -> Constants.ASCENDING
            descending -> Constants.DESCENDING
            else -> Constants.DESCENDING
        }
    }

    if (isOpened.value) {
        ModalBottomSheet(
            onDismissRequest = { isOpened.value = false },
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.background,
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.sort_by),
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    SortByOptions(
                        sortByOptions = sortByOptionList,
                        selectedValue = selectedSortBy.value,
                        onPress = { selectedSortBy.value = it }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(id = R.string.order_by),
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    SortByOrder(
                        sortByOrder = sortByOrderList,
                        selectedValue = selectedOrderBy.value,
                        onPress = { selectedOrderBy.value = it }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        TextButton(
                            onClick = { onDismiss() },
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = stringResource(id = R.string.dismiss))
                        }
                        Button(
                            onClick = {
                                onFilter(
                                    convertSortBy(selectedSortBy.value),
                                    convertOrderBy(selectedOrderBy.value)
                                )
                            },
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = stringResource(id = R.string.confirm))
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun SortByOptions(
    sortByOptions: List<String>,
    selectedValue: String,
    onPress: (String) -> Unit
) {
    Column(modifier = Modifier.selectableGroup()) {
        sortByOptions.forEach {
            SortingItemList(
                title = it,
                selectedValue = selectedValue,
                onPress = { onPress(it) }
            )
        }
    }
}

@Composable
fun SortByOrder(
    sortByOrder: List<String>,
    selectedValue: String,
    onPress: (String) -> Unit
) {
    Column(modifier = Modifier.selectableGroup()) {
        sortByOrder.forEach {
            SortingItemList(
                title = it,
                selectedValue = selectedValue,
                onPress = { onPress(it) }
            )
        }
    }
}

// Panga (2x)
@Composable
fun SortingItemList(
    title: String,
    selectedValue: String,
    onPress: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) { onPress() }
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.weight(1f)
        )
        RadioButton(
            selected = (title == selectedValue),
            onClick = { onPress() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun FilterSheetPreview() {

    val isOpened = rememberSaveable { mutableStateOf(false) }
    val skipPartiallyExpanded = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded.value
    )

    FilterSheet(
        isOpened = isOpened,
        sheetState = sheetState,
        onDismiss = { /*TODO*/ }) { _, _ ->

    }
}