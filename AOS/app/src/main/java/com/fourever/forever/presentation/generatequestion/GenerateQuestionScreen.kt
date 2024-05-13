package com.fourever.forever.presentation.generatequestion

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fourever.forever.R
import com.fourever.forever.presentation.SCREEN_MARGIN
import com.fourever.forever.presentation.component.ProgressIndicator
import com.fourever.forever.presentation.component.btmsheet.AnswerBtmSheet
import com.fourever.forever.presentation.component.btmsheet.BTM_SHEET_PEEK_HEIGHT
import com.fourever.forever.presentation.component.btmsheet.BTM_SHEET_RADIUS
import com.fourever.forever.presentation.component.btmsheet.BTM_SHEET_SHADOW_ELEVATION
import com.fourever.forever.presentation.component.btmsheet.BTM_SHEET_TONAL_ELEVATION
import com.fourever.forever.presentation.component.buttons.LongColorBtn
import com.fourever.forever.presentation.component.buttons.LongWhiteBtn
import com.fourever.forever.presentation.component.card.ExpectationCard
import com.fourever.forever.presentation.component.card.QuestionCard
import com.fourever.forever.presentation.component.topappbar.FileNameTopAppBar

private const val SPACE_BETWEEN_COMPONENTS = 17
private const val SPACE_BETWEEN_BUTTONS = 10

private const val MAX_QUESTION_INDEX = 5

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenerateQuestionScreen(
    fileName: String,
    navigateUp: () -> Unit
) {
    val questionIndex = rememberSaveable { mutableStateOf(1) }

    BottomSheetScaffold(
        sheetContent = {
            Column(
                modifier = Modifier.padding(horizontal = SCREEN_MARGIN.dp)
            ) {
                AnswerBtmSheet(answer = "3/24 계획서, 5월 발표, 6월 최종 평가로 구성되어 있습니다.")
            }
        },
        topBar = { FileNameTopAppBar(fileName = fileName, onBackButtonClick = navigateUp) },
        sheetPeekHeight = BTM_SHEET_PEEK_HEIGHT.dp,
        sheetShape = RoundedCornerShape(
            topStart = BTM_SHEET_RADIUS.dp,
            topEnd = BTM_SHEET_RADIUS.dp
        ),
        sheetContainerColor = colorResource(id = R.color.white),
        sheetTonalElevation = BTM_SHEET_TONAL_ELEVATION.dp,
        sheetShadowElevation = BTM_SHEET_SHADOW_ELEVATION.dp
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = SCREEN_MARGIN.dp)
        ) {
            ProgressIndicator(progress = questionIndex.value, questionListSize = MAX_QUESTION_INDEX)
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                QuestionCard(question = "캡스톤 디자인 강의의 주요 일정은 어떻게 구성되어 있나요? 정말 궁금합니다. 얼른 답변을 입력해주세요.")
                Spacer(modifier = Modifier.size(SPACE_BETWEEN_COMPONENTS.dp))
                ExpectationCard()
                Spacer(modifier = Modifier.size(SPACE_BETWEEN_COMPONENTS.dp))
                LongWhiteBtn(enabled = false)
                Spacer(modifier = Modifier.size(SPACE_BETWEEN_BUTTONS.dp))
                LongColorBtn(
                    text = stringResource(id = R.string.question_done_button),
                    enabled = true,
                    onClick = {
                        if (questionIndex.value < MAX_QUESTION_INDEX) {
                            questionIndex.value++
                        }
                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = false, showBackground = false)
@Composable
private fun QuestionPreview() {
    MaterialTheme {
        GenerateQuestionScreen(
            fileName = "",
            navigateUp = {}
        )
    }
}