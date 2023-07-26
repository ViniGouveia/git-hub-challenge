package dev.vinigouveia.git_hub_challenge.utils.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.vinigouveia.git_hub_challenge.R
import dev.vinigouveia.git_hub_challenge.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarCompose(
    modifier: Modifier = Modifier,
    onTextChange: (String) -> Unit
) {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }
    val history = remember { mutableStateListOf<String>() }

    SearchBar(
        modifier = modifier.fillMaxWidth(),
        query = text,
        onQueryChange = { text = it },
        onSearch = {
            history.add(text)
            onTextChange(text)
            active = false
        },
        active = active,
        onActiveChange = { active = it },
        colors = SearchBarDefaults.colors(containerColor = MaterialTheme.colorScheme.white),
        placeholder = { Text(text = stringResource(id = R.string.search_bar_placeholder)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        },
        trailingIcon = {
            if (active || text.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Search Icon",
                    modifier = Modifier.clickable {
                        text = ""
                        onTextChange(text)
                        active = false
                    }
                )
            }
        },
        content = {
            history.forEach {
                Row(
                    modifier = Modifier
                        .padding(14.dp)
                        .fillMaxWidth()
                        .clickable { onTextChange(it) },
                    verticalAlignment = CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "History icon")
                    Text(modifier = Modifier.padding(start = 8.dp), text = it)
                }
            }
        }
    )
}
