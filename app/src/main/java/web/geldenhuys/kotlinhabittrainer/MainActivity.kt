package web.geldenhuys.kotlinhabittrainer

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import web.geldenhuys.kotlinhabittrainer.db.HabitDbTable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        habits_recycler.layoutManager = LinearLayoutManager(this)
        habits_recycler.setHasFixedSize(true)
        habits_recycler.adapter = HabitsAdapter(HabitDbTable(this).readAllHabits())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_habit) {
            switchTo(CreateHabitActivity::class.java)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun switchTo(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }
}