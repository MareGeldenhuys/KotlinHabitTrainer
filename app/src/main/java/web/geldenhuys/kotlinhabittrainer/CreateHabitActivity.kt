package web.geldenhuys.kotlinhabittrainer

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_create_habit.*
import java.io.IOException

class CreateHabitActivity : AppCompatActivity() {

    val TAG = CreateHabitActivity::class.simpleName
    val CHOOSE_IMAGE_REQUEST = 1

    private var imageBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_habit)
    }

    fun chooseImage(view: View) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        val chooser = Intent.createChooser(intent, "Choose image for habit")
        startActivityForResult(chooser, CHOOSE_IMAGE_REQUEST)
        Log.d(TAG, "Intent to choose image sent... ")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CHOOSE_IMAGE_REQUEST
            && resultCode == Activity.RESULT_OK
            && data != null) {
            val uri = data.data
            if (uri != null) {
                val bitmap = tryReadBitmap(uri)
                bitmap?.let {
                    this.imageBitmap = bitmap
                    selected_image.setImageBitmap(bitmap)
                    Log.d(TAG, "onActivityResult: Set Image")
                }
            }
            
        }
    }

    private fun tryReadBitmap(data: Uri): Bitmap? {
        return try {
           MediaStore.Images.Media.getBitmap(contentResolver, data)
        } catch (e : IOException) {
            e.printStackTrace()
            null
        }
    }

    private fun EditText.isBlank() = this.text.toString().isBlank()


    fun storeHabit(view: View) {
        if (title_edittext.isBlank() || desciption_edittext.isBlank()) {
            displayErrorMessage("Please provide a title and description")
            Log.d(TAG, "storeHabit: Missing title and description")
            return
        } else if (imageBitmap == null) {
            Log.d(TAG, "storeHabit: Missing image")
            displayErrorMessage("Image")
            return
        }
        error_textview.visibility = View.INVISIBLE
        // Store Habit
    }

    private fun displayErrorMessage(s: String) {
        error_textview.text = s
        error_textview.visibility = View.VISIBLE
    }
}