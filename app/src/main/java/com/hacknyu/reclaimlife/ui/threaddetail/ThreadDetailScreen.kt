package com.hacknyu.reclaimlife.ui.threaddetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hacknyu.reclaimlife.ui.ViewModelFactory
import com.hacknyu.reclaimlife.ui.common.BackButton
import com.hacknyu.reclaimlife.ui.common.LoadingIndicator
import com.hacknyu.reclaimlife.ui.home.dateheader.Title
import com.hacknyu.reclaimlife.ui.theme.Typography
import com.hacknyu.reclaimlife.utils.Injection
import com.hacknyu.reclaimlife.R
import com.hacknyu.reclaimlife.model.NewComment
import com.hacknyu.reclaimlife.ui.common.CardWithTitleAndIcon
import com.hacknyu.reclaimlife.ui.theme.DarkBlue

@Composable
fun ThreadDetailScreen(
    threadId: String,
    viewModel: ThreadDetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository(LocalContext.current.applicationContext),
            threadId = threadId
        )
    ),
    onClickBack: () -> Unit,
) {
    var comment by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    viewModel.thread.collectAsState().value.let { threadDetailViewState ->
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.align(Alignment.TopCenter)) {
                item {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        BackButton(
                            onClick = { onClickBack() },
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(32.dp)
                                .size(50.dp)
                        )
                        if (threadDetailViewState.thread != null) {
                            Title(
                                text = threadDetailViewState.thread?.title ?: "",
                                style = Typography.h1,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                    if (threadDetailViewState.isLoading) {
                        LoadingIndicator()
                    }
                    val thread = threadDetailViewState.thread
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.icon_person),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 32.dp, top = 5.dp)
                                .size(20.dp)
                        )
                        Title(
                            text = thread?.authorName.toString(),
                            color = DarkBlue,
                            style = Typography.subtitle1,
                            modifier = Modifier.padding(start = 20.dp, top = 3.dp)
                        )
                    }
                    Title(
                        text = thread?.content ?: "",
                        style = Typography.body1,
                        modifier = Modifier.padding(horizontal = 32.dp, vertical = 10.dp)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .wrapContentSize()
                                .align(Alignment.CenterStart)
                                .padding(start = 32.dp)
                        ) {
                            Image(
                                imageVector = Icons.Default.Comment,
                                contentDescription = null,
                            )
                            Title(
                                text = thread?.comments?.size.toString(),
                                style = Typography.subtitle1,
                                modifier = Modifier.padding(start = 10.dp)
                            )
                        }
                    }

                    Divider(
                        color = Color.Gray,
                        thickness = 3.dp,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }

                items(threadDetailViewState.thread?.comments ?: listOf()) {
                    CardWithTitleAndIcon(
                        icon = R.drawable.comment_icon,
                        titleText = it.text,
                        modifier = Modifier.padding(horizontal = 32.dp, vertical = 10.dp),
                        style = Typography.subtitle1
                    ) {
                    }
                }

                item {
                    TextField(
                        value = comment,
                        onValueChange = { newComment ->
                            comment = newComment
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp)
                            .align(Alignment.BottomCenter),
                        placeholder = {
                            Title(text = "Add Your Comments")
                        },
                        trailingIcon = {
                            if (comment.isEmpty().not()) {
                                IconButton(onClick = {
                                    viewModel.sendComment(
                                        NewComment(
                                            text = comment,
                                            author = threadDetailViewState.thread?.authorName.toString()
                                        ), threadId
                                    )
                                    focusManager.clearFocus()
                                    comment = ""
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Send,
                                        contentDescription = null,
                                        tint = DarkBlue
                                    )
                                }
                            }
                        }
                    )
                }
            }


        }

    }
}