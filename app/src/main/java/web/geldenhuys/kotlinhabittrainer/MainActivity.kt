package web.geldenhuys.kotlinhabittrainer

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        habits_recycler.layoutManager = LinearLayoutManager(this)
        habits_recycler.setHasFixedSize(true)
        habits_recycler.adapter = HabitsAdapter(getSampleHabits())
    }
}