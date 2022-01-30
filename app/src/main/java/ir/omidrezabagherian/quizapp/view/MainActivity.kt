package ir.omidrezabagherian.quizapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import ir.omidrezabagherian.quizapp.R
import ir.omidrezabagherian.quizapp.data.Data
import ir.omidrezabagherian.quizapp.data.StatusAnswer
import ir.omidrezabagherian.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bindingMain: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        } else if (Data.isCompletes[Data.round] == StatusAnswer.None) {
            if (getAnswer(Data.round)) {
                Toast.makeText(this, R.string.text_toast_correct, Toast.LENGTH_SHORT).show()
                Data.isCompletes[Data.round] = StatusAnswer.Correct
            } else {
                Toast.makeText(this, R.string.text_toast_incorrect, Toast.LENGTH_SHORT)
                    .show()
                Data.isCompletes[Data.round] = StatusAnswer.Incorrect
            }
            buttonIsEnable(false)
        } else {
            buttonIsEnable(false)
        }
    }

    private fun buttonIsFalse() {
        if (Data.isCompletes[Data.round] == StatusAnswer.Cheat) {
            Toast.makeText(this, R.string.text_toast_cheat, Toast.LENGTH_SHORT).show()
        } else if (Data.isCompletes[Data.round] == StatusAnswer.None) {
            if (!getAnswer(Data.round)) {
                Toast.makeText(this, R.string.text_toast_correct, Toast.LENGTH_SHORT).show()
                Data.isCompletes[Data.round] = StatusAnswer.Correct
            } else {
                Toast.makeText(this, R.string.text_toast_incorrect, Toast.LENGTH_SHORT)
                    .show()
                Data.isCompletes[Data.round] = StatusAnswer.Incorrect
            }
            buttonIsEnable(false)
        } else {
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
        buttonIsEnable(true)

        bindingMain.buttonTrue.setOnClickListener {
            buttonIsTrue()
        }
        bindingMain.buttonFalse.setOnClickListener {
            buttonIsFalse()
        }
    }

    private fun getAnswer(round: Int): Boolean {
        return Data.answers[round].toString().toBoolean()
    }

    private fun getQuestion(round: Int): String {
        return Data.questions[round]
    }

    private fun questionNext() {

        bindingMain.buttonPrev.isEnabled = true

        Data.round += 1

        if (Data.round >= 9) {
            Data.round = 9
            bindingMain.buttonNext.isEnabled = false
        }

        showQuestion()
    }

    private fun questionPrev() {
        bindingMain.buttonNext.isEnabled = true

        Data.round -= 1

        if (Data.round <= 0) {
            Data.round = 0
            bindingMain.buttonPrev.isEnabled = false
        }

        showQuestion()
    }


}