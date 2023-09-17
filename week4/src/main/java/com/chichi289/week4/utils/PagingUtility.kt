package com.chichi289.week4.utils

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems

inline fun <T : Any> LazyListScope.items(
    items: LazyPagingItems<T>,
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit
) = items(
    count = items.itemCount,
) {
    items[it]?.let { it1 -> itemContent(it1) }
}

inline fun <T : Any> LazyGridScope.items(
    items: LazyPagingItems<T>,
    crossinline itemContent: @Composable LazyGridItemScope.(item: T) -> Unit
) = items(
    count = items.itemCount,
) {
    items[it]?.let {
            it1 -> itemContent(it1)
    }
}