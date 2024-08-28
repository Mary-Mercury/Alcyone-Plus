package com.mary.alcyoneplus.UI.Screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mary.alcyoneplus.R

data class Questions(
    val questionText: String,
    val answers: List<String>,
    val correctAnswerIndex: Int
)

@Composable
fun StartScreen(onStartClicked: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Cover(name = "", imageRes = R.drawable.ushakova)
        Text(
            style = typography.bodySmall,
            fontSize = 15.sp,
            modifier = Modifier
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                    top = 10.dp,
                    bottom = 10.dp

                ),
            text = "Это тестовый модуль приложения, в котором вы сможете проверить свои знания о ГМУ им. Ф. Ф. Ушакова. " +
        "Квиз состоит из нескольких простых вопросов, возможно в будущем этот модуль увидит свое развитие. " +
        "Развлекайесь :)")
        Button(
            onClick = onStartClicked) {
            Text(text = "Start Quiz")
        }
    }
}

@Composable
fun QuizScreen(
    questions: List<Questions>,
    onQuizCompleted: (Int) -> Unit
) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }

    val currentQuestion = questions[currentQuestionIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = currentQuestion.questionText
        )

        Spacer(modifier = Modifier.height(16.dp))

        currentQuestion.answers.forEachIndexed { index, answer ->
            Button(
                onClick = {
                    if (index == currentQuestion.correctAnswerIndex) {
                        score++
                    }
                    if (currentQuestionIndex < questions.size - 1) {
                        currentQuestionIndex++
                    } else {
                        onQuizCompleted(score)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = answer)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LinearProgressIndicator(
            progress = (currentQuestionIndex + 1) / questions.size.toFloat(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ResultScreen(score: Int, totalQuestions: Int, onBackClicked: () -> Unit) {

    val percentage = (score.toFloat() / totalQuestions * 100).toInt()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Ваш результат: $percentage%")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onBackClicked) {
                Text(text = "Back to Start")
            }
        }
    }
}

@Composable
fun QuizApp() {
    val navController = rememberNavController()

    val questions = listOf(
        Questions(
            questionText = "В каком году был основан университет им. адмирала Ф. Ф. Ушакова?",
            answers = listOf("1966", "1975", "1992", "2017"),
            correctAnswerIndex = 1
        ),
        Questions(
            questionText = "В каком из перечисленных городов России университет никогда не имел филиала?",
            answers = listOf("Севастополь", "Ростов-на-Дону", "Мариуполь", "Махачкала"),
            correctAnswerIndex = 3
        ),
        Questions(
            questionText = "В каком году университет стал нести имя адмирала Федора Федоровича Ушакова?",
            answers = listOf("2005", "1992", "2017", "1975"),
            correctAnswerIndex = 0
        ),
        Questions(
            questionText = "Каким из перечисленных названий никогда не именовался Государственный Морской Университет?",
            answers = listOf("Новороссийское высшее инженерное морское училище",
                "Новороссийское государственное морское училище им. адм. Ф. Ф. Ушакова",
                "Новороссийская государственная морская академия им. адм. Ф. Ф. Ушакова",
                "Морская государственная академия им. адм. Ф. Ф. Ушакова"),
            correctAnswerIndex = 1
        ),
        Questions(
            questionText = "Как называется учебный трех мачтовый фрегат принадлежащий одному из филиалов ГМУ им. адм. Ф. Ф. Ушакова ?",
            answers = listOf("Александр Грин", "Адмирал Ушаков", "Херсонес", "Паллада"),
            correctAnswerIndex = 2
        ),
        Questions(
            questionText = "По какому из перечисленных научных исследований, ГМУ им. адм. Ф. Ф. Ушакова входит в группу ведущих научных организаций?",
            answers = listOf("Водородная энергетика",
                "Уменьшение гидродинамического сопротивления кораблей",
                "Аэродинамика в кораблестроении",
                "Автономное судовождение"),
            correctAnswerIndex = 3
        ),
        Questions(
            questionText = "Какое подразделение не входит в структуру ГМУ им. адм. Ф. Ф. Ушакова?",
            answers = listOf("Институт повышения квалификации",
                "Факультет водного транспорта и судовождения",
                "Морской колледж",
                "Факультет военного обучения"),
            correctAnswerIndex = 2
        ),

    )

    NavHost(navController, startDestination = "start") {
        composable("start") {
            StartScreen(onStartClicked = {
                navController.navigate("quiz")
            })
        }
        composable("quiz") {
            QuizScreen(questions = questions, onQuizCompleted = { score ->
                navController.navigate("result/$score")
            })
        }
        composable("result/{score}") { backStackEntry ->
            val score = backStackEntry.arguments?.getString("score")?.toInt() ?: 0
            ResultScreen(score = score, totalQuestions = questions.size, onBackClicked = {
                navController.popBackStack("start", inclusive = false)
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizAppPreview() {
    QuizApp()
}


@Composable
fun Cover(
    name: String,
    @DrawableRes imageRes: Int
) {

    Box {
        Card(shape = RoundedCornerShape(0.dp)) {
            Box {
                Image(
                    painter = painterResource(imageRes),
                    contentDescription = "image $name",
                    modifier = Modifier
                        .align(Alignment.Center),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = name,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                0F to Color.Transparent,
                                0.2F to Color.Black.copy(alpha = 0.5F),
                                1F to Color.Black.copy(alpha = 0.8F)
                            )
                        )
                        .padding(top = 140.dp),
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun ComicsPreview() {
    Cover(
        "",
        R.drawable.ushakova
    )
}