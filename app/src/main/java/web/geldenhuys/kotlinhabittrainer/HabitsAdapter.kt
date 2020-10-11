package web.geldenhuys.kotlinhabittrainer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.habit_card.view.*

class HabitsAdapter(private val sampleHabits: List<Habit>) : RecyclerView.Adapter<HabitsAdapter.HabitViewHolder>() {

    class HabitViewHolder(val card: View) : RecyclerView.ViewHolder(card)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.habit_card, parent, false)
        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = sampleHabits[position]
        with (holder.card) {
            tv_description.text = habit.description
            iv_icon.setImageBitmap(habit.image)
            iv_title.text = habit.title
        }
    }

    override fun getItemCount() = sampleHabits.size
}
