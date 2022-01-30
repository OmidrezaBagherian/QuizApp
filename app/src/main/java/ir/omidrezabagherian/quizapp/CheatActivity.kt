package ir.omidrezabagherian.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ir.omidrezabagherian.quizapp.databinding.ActivityCheatBinding

class CheatActivity : AppCompatActivity() {

    private lateinit var bindingCheat: ActivityCheatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(bindingCheat.root)
    }

}