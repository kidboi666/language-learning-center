package com.kidboi666.viewmodeldemo

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun DemoView(viewModel: DemoViewModel) {
    DemoView(
        isFahrenheit = viewModel.isFahrenheit,
        result = viewModel.result,
        convertTemp = { viewModel.convertTemp(it) },
        switchChange = { viewModel.switchChange() })
}

@Composable
fun DemoView(
    isFahrenheit: Boolean, result: String, convertTemp: (String) -> Unit, switchChange: () -> Unit
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        var textState by remember { mutableStateOf("") }

        fun onTextChange(text: String) {
            textState = text
        }
        Text(
            "Temperature Converter",
            modifier = Modifier.padding(20.dp),
            style = MaterialTheme.typography.headlineMedium
        )
        InputRow(
            isFahrenheit = isFahrenheit,
            switchChange = switchChange,
            onTextChange = ::onTextChange,
            textState = textState
        )
        Text(
            result,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(20.dp)
        )
        Button(onClick = { convertTemp(textState) }) {
            Text("Convert Temperature")
        }
    }
}

@Composable
fun InputRow(
    isFahrenheit: Boolean,
    switchChange: () -> Unit,
    onTextChange: (String) -> Unit,
    textState: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Switch(
            checked = isFahrenheit, onCheckedChange = { switchChange() }
        )

        OutlinedTextField(
            value = textState,
            onValueChange = { onTextChange(it) },
            label = { Text(text = "Enter temperature") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.padding(10.dp),
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_ac_unit_24),
                    contentDescription = "frost",
                    modifier = Modifier.size(40.dp)
                )
            })

        Crossfade(targetState = isFahrenheit, animationSpec = tween(300)) { visible ->
            when (visible) {
                true -> Text(text = "\u2109")
                false -> Text(text = "\u2103")
            }
        }
    }
}