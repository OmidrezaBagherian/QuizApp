package ir.omidrezabagherian.quizapp.data

object Data {
    var round = 0

    val questions = arrayOf(
        "Quiz1",
        "Quiz2",
        "Quiz3",
        "Quiz4",
        "Quiz5",
        "Quiz6",
        "Quiz7",
        "Quiz8",
        "Quiz9",
        "Quiz10"
    )

    val answers = arrayOf(
        false,
        true,
        false,
        true,
        false,
        true,
        false,
        true,
        false,
        true
    )

    val isCompletes = arrayOf(
        StatusAnswer.None,
        StatusAnswer.None,
        StatusAnswer.None,
        StatusAnswer.None,
        StatusAnswer.None,
        StatusAnswer.None,
        StatusAnswer.None,
        StatusAnswer.None,
        StatusAnswer.None,
        StatusAnswer.None
    )

}