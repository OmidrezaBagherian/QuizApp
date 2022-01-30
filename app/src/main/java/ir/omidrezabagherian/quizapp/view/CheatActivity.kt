package ir.omidrezabagherian.quizapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import ir.omidrezabagherian.quizapp.R
import ir.omidrezabagherian.quizapp.data.Data
import ir.omidrezabagherian.quizapp.data.StatusAnswer
import ir.omidrezabagherian.quizapp.databinding.ActivityCheatBinding

class CheatActivity : AppCompatActivity() {

    private lateinit var bindingCheat: ActivityCheatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingCheat = ActivityCheatBinding.inflate(layoutInflater)

        bindingCheat.buttonShowAnswer.setOnClickListener {
            if (Data.isCompletes[Data.round] == StatusAnswer.None) {
                if (Data.answers[Data.round]) {
                    Toast.makeText(this, R.string.text_toast_correct, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, R.string.text_toast_incorrect, Toast.LENGTH_SHORT).show()
                }
                Data.isCompletes[Data.round] = StatusAnswer.Cheat
            } else {
                Toast.makeText(this, R.string.text_no_cheat, Toast.LENGTH_SHORT).show()
            }

        }

        setContentView(bindingCheat.root)
    }
}