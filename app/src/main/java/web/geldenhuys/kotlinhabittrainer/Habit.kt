package web.geldenhuys.kotlinhabittrainer

data class Habit(val title: String, val description:String, val image: Int) {


}

fun getSampleHabits(): List<Habit> {
    return listOf(
        Habit("Go for a walk", "a Nice walk in thge sun gets you ready for the day ahead", R.drawable.walk),
        Habit("Drink a glass of water", "a Refreshing glass of water gets you hydrated", R.drawable.water)
    )
}