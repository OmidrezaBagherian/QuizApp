package ir.omidrezabagherian.quizapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ir.omidrezabagherian.quizapp.R
import ir.omidrezabagherian.quizapp.data.Data
import ir.omidrezabagherian.quizapp.data.Question
import ir.omidrezabagherian.quizapp.data.StatusAnswer
import ir.omidrezabagherian.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bindingMain: ActivityMainBinding
    private lateinit var question: Question

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        question = Question(
            Data.question[Data.round][0],
            Data.question[Data.round][1]
        )

        bindingMain = ActivityMainBinding.inflate(layoutInflater)

        showQuestion()

        buttonPrevDisable()


        bindingMain.buttonCheat.setOnClickListener {
            val cheat = Intent(this, CheatActivity::class.java)

            startActivity(cheat)
        }

        bindingMain.buttonPrev.setOnClickListener {
            questionPrev()
        }

        bindingMain.buttonNext.setOnClickListener {
            questionNext()
        }

        setContentView(bindingMain.root)
    }

    private fun buttonIsTrue() {
        if (Data.isCompletes[Data.round] == StatusAnswer.Cheat) {
            Toast.makeText(this, R.string.text_toast_cheat, Toast.LENGTH_SHORT).show()
        } else {
            if (getAnswer(Data.round)) {
                Toast.makeText(this, R.string.text_toast_correct, Toast.LENGTH_SHORT).show()
                Data.isCompletes[Data.round] = StatusAnswer.Correct
            } else {
                Toast.makeText(this, R.string.text_toast_incorrect, Toast.LENGTH_SHORT)
                    .show()
                Data.isCompletes[Data.round] = StatusAnswer.Incorrect
            }
            buttonIsEnable(false)
        }
    }

    private fun buttonIsFalse() {
        if (Data.isCompletes[Data.round] == StatusAnswer.Cheat) {
            Toast.makeText(this, R.string.text_toast_cheat, Toast.LENGTH_SHORT).show()
        } else {
            if (!getAnswer(Data.round)) {
                Toast.makeText(this, R.string.text_toast_correct, Toast.LENGTH_SHORT).show()
                Data.isCompletes[Data.round] = StatusAnswer.Correct
            } else {
                Toast.makeText(this, R.string.text_toast_incorrect, Toast.LENGTH_SHORT)
                    .show()
                Data.isCompletes[Data.round] = StatusAnswer.Incorrect
            }
            buttonIsEnable(false)
        }

    }

    private fun buttonPrevDisable() {
        if (Data.round == 0) {
            bindingMain.buttonPrev.isEnabled = false
        }
    }

    private fun showQuestion() {
        bindingMain.textviewQuiz.text = getQuestion(Data.round)
        setAnswer()
    }

    private fun buttonIsEnable(status: Boolean) {
        bindingMain.buttonTrue.isEnabled = status
        bindingMain.buttonFalse.isEnabled = status
    }

    private fun setAnswer() {
        if (Data.isCompletes[Data.round] == StatusAnswer.None || Data.isCompletes[Data.round] == StatusAnswer.Cheat) {
            buttonIsEnable(true)
        } else {
            buttonIsEnable(false)
        }

        bindingMain.buttonTrue.setOnClickListener {
            buttonIsTrue()
        }
        bindingMain.buttonFalse.setOnClickListener {
            buttonIsFalse()
        }
    }

    private fun getAnswer(round: Int): Boolean {
        return question.answer.toBoolean()
    }

    private fun getQuestion(round: Int): String {
        return question.quest
    }

    private fun questionNext() {
        bindingMain.buttonPrev.isEnabled = true

        Data.round += 1

        if (Data.round >= 9) {
            Data.round = 9
            bindingMain.buttonNext.isEnabled = false
        }

        question = Question(
            Data.question[Data.round][0],
            Data.question[Data.round][1]
        )

        showQuestion()
    }

    private fun questionPrev() {
        bindingMain.buttonNext.isEnabled = true

        Data.round -= 1

        if (Data.round <= 0) {
            Data.round = 0
            bindingMain.buttonPrev.isEnabled = false
        }

        question = Question(
            Data.question[Data.round][0],
            Data.question[Data.round][1]
        )

        showQuestion()
    }


}