package com.example.artyomkafood.core.extensions

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.artyomkafood.R
import com.example.artyomkafood.core.database.dao.ScheduleMeal
import kotlinx.android.synthetic.main.fragment_correct.view.*

fun Fragment.checkFirstStart(): Boolean {
    val pref = requireContext().getSharedPreferences("Pref_firstStart", Context.MODE_PRIVATE)

    val a = pref.contains("isFirstStart")

    if (!a) pref.edit().putBoolean("isFirstStart", false).apply()
    return !a
}

fun Fragment.createEditDialog(
    item: ScheduleMeal?,
    editMeal: (newVolume: String) -> Unit,
) {
    val view = LayoutInflater.from(requireContext())
        .inflate(R.layout.fragment_correct, null)
    val editButton = view.edit_button
    val title = view.title
    val editText = view.counter

   editText.doOnTextChanged { text, _, _, _ ->
       editButton.isEnabled = text?.length != 0
   }

    title.text = item?.product_name
    editText.text.insert(0, item?.meal_volume.toString())

    val dialog = AlertDialog.Builder(requireContext())
        .setView(view)
        .create()

    editButton.setOnClickListener {
        editMeal(editText.text.toString())
        dialog.dismiss()
    }

    dialog.show()

}