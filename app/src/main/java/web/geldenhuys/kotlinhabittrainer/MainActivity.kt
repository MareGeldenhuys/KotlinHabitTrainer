package web.geldenhuys.kotlinhabittrainer

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_description.text = "Blah blah water is good for you"

        iv_icon.setImageResource(R.drawable.water)
        iv_title.text = "Water"
    }
}