package com.ahmetkaragunlu.favflixapp.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp


@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value : String,
    onValueChange : (String) -> Unit,
    @StringRes label : Int,
    keyboardOptions: KeyboardOptions,

) {
    OutlinedTextField(
        value = value,
        onValueChange=onValueChange,
        keyboardOptions=keyboardOptions,
        label= { Text(text = stringResource(label)) },
        modifier = modifier.fillMaxWidth().padding(16.dp),
        shape = RoundedCornerShape(16.dp)
    )

}