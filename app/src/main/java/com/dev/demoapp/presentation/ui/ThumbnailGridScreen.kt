package com.dev.demoapp.presentation.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.dev.demoapp.R
import com.dev.demoapp.data.model.Items
import com.dev.demoapp.presentation.viewmodel.ThumbnailViewModel

@Composable
fun ThumbnailGridScreen(viewModel: ThumbnailViewModel = viewModel()) {
    /* val viewModel: ThumbnailViewModel = viewModel(
         factory = ThumbnailViewModelFactory(repository)  // Pass the required repository
     )*/
    val thumbnails by viewModel.thumbnails.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            query = searchQuery,
            onQueryChanged = { query -> viewModel.onSearchQueryChanged(query) },
            onSearch = { query -> viewModel.searchThumbnails(query) }
        )


        if (thumbnails != null && thumbnails?.items!!.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3), // Adjust to the number of items per row
                modifier = Modifier.fillMaxSize()
            ) {
                items(thumbnails?.items!!) { items ->
                    ThumbnailItem(items)
                }
            }
        }

    }
}

@Composable
fun SearchBar(query: String, onQueryChanged: (String) -> Unit, onSearch: (String) -> Unit) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(Color.Gray.copy(alpha = 0.2f))
            .clip(RoundedCornerShape(8.dp))
    ) {
        BasicTextField(
            value = query,
            onValueChange = onQueryChanged,
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        )
        IconButton(onClick = { onSearch(query) }) {
            Icon(Icons.Default.Search, contentDescription = "Search")
        }
    }
}

@Composable
fun ThumbnailItem(thumbnail: Items) {

    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Log.d("Images>>", "ThumbnailItem: ${thumbnail.link}")
        Column {
            var imageUrl = thumbnail.media.m

            AsyncImage(
                model = imageUrl,
                placeholder = painterResource(id = R.drawable.images),
                contentDescription = thumbnail.title,
                modifier = Modifier.size(150.dp) // Optional: Set a size for the image
            )

            Text(text = thumbnail.title, modifier = Modifier.padding(8.dp))
        }
    }
}
