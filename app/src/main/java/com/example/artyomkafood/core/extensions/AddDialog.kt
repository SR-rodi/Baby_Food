package com.example.artyomkafood.core.extensions

import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.artyomkafood.R
import com.example.artyomkafood.feature_food.domain.model.FoodCategory
import kotlinx.android.synthetic.main.dialo_correct.view.*
import kotlinx.android.synthetic.main.dialog_add_product.view.*

fun Fragment.createAddProductDialog(
    list: List<String>,
    addDataBase: (newName: String, categoryId: Int) -> Unit,
) {
    val view = LayoutInflater.from(requireContext())
        .inflate(R.layout.dialog_add_product, null)
    val saveButton = view.save_button
    val dropMenu = view.autoCompleteTextView
    val name = view.name
    var index = 0
    val adapter = ArrayAdapter(requireContext(), R.layout.item_category, list)

    saveButton.isEnabled = false

    dropMenu.text.insert(0, list.first())
    dropMenu.setAdapter(adapter)
    dropMenu.setOnItemClickListener { _, _, position, _ -> index = position }

    name.doOnTextChanged { text, _, _, _ ->
        saveButton.isEnabled = text?.length != 0
    }

    val dialog = AlertDialog.Builder(requireContext())
        .setView(view)
        .create()

    saveButton.setOnClickListener {
        addDataBase(name.text.toString(), index+1)
        dialog.dismiss()
    }
    dialog.show()
}