package com.hacknyu.reclaimlife.ui.community

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hacknyu.reclaimlife.R
import com.hacknyu.reclaimlife.model.NewThread
import com.hacknyu.reclaimlife.model.Threads
import com.hacknyu.reclaimlife.model.ThreadsList
import com.hacknyu.reclaimlife.ui.ViewModelFactory
import com.hacknyu.reclaimlife.ui.common.CardWithTitleAndIcon
import com.hacknyu.reclaimlife.ui.common.CustomDialog
import com.hacknyu.reclaimlife.ui.common.LoadingIndicator
import com.hacknyu.reclaimlife.ui.home.dateheader.Title
import com.hacknyu.reclaimlife.ui.theme.Typography
import com.hacknyu.reclaimlife.utils.Injection


@Composable
fun CommunityScreen(
    viewModel: CommunityViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository(LocalContext.current.applicationContext)
        )
    ),
    onClickThread: (threadId: String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        CustomDialog(value = "", setShowDialog = {
            showDialog = it
        }) { threadTitle, threadContent ->
            viewModel.addThread(
                title = threadTitle,
                content = threadContent
            )
        }
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        viewModel.communityViewState.collectAsState().value.let { communityViewState ->
            if (communityViewState.isThreadLoading) {
                LoadingIndicator()
                return@let
            }
            LazyColumn(modifier = Modifier.padding(vertical = 15.dp)) {
                items(communityViewState.threads) { thread ->
                    ThreadItem(thread = thread, onClickThread = {
                        onClickThread(thread.id)
                    })
                }
            }
        }
    }
}

@Composable
fun ThreadItem(
    thread: ThreadsList,
    onClickThread: () -> Unit
) {
    CardWithTitleAndIcon(
        icon = R.drawable.icon_person,
        titleText = thread.authorName,

        modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 10.dp)
            .clickable { onClickThread() },
    ) {
        Column {
            Title(
                text = thread.title,
                modifier = Modifier.padding(start = 20.dp, bottom = 10.dp),
                style = Typography.h1
            )
            Title(
                text = thread.content,
                modifier = Modifier.padding(start = 20.dp, bottom = 20.dp),
                style = Typography.body1
            )
        }


    }
}

@Preview
@Composable
fun ThreadItemPreview() {
    ThreadItem(thread = ThreadsList(
        id = "1",
        authorName = "Palak",
        title = "This is my blog",
        content = "This is my blog content",
        tags = listOf("Motivation", "Test", "Hello"),
        comments = listOf(),
    ), onClickThread = {})
}