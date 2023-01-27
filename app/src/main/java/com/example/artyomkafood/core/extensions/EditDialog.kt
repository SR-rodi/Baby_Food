package com.example.artyomkafood.core.extensions

import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.artyomkafood.R
import kotlinx.android.synthetic.main.dialog_correct.view.*

fun Fragment.createEditDialog(
    product: String?,
    volume:Int?,
    editMeal: (newVolume: String) -> Unit,
) {
    val view = LayoutInflater.from(requireContext())
        .inflate(R.layout.dialog_correct, null)
    val editButton = view.edit_button
    val title = view.title
    val editText = view.counter

    editText.doOnTextChanged { text, _, _, _ ->
        editButton.isEnabled = text?.length != 0
    }

    title.text = product
    editText.text.insert(0, volume?.toString())

    val dialog = AlertDialog.Builder(requireContext())
        .setView(view)
        .create()

    editButton.setOnClickListener {
        editMeal(editText.text.toString())
        dialog.dismiss()
    }

    dialog.show()
}