package com.example.artyomkafood.core.castomView

import android.content.Context
import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import com.example.artyomkafood.R
import com.example.artyomkafood.databinding.ItemMenuBinding

class MenuItem @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : CardView(context, attrs) {

    private var _binding: ItemMenuBinding? = null
    private val binding get() = _binding!!

    init {
        initBinding()
        attrs?.let { setAttrs(it) }

    }

    private fun initBinding() {
        LayoutInflater.from(context).inflate(R.layout.item_menu, this, true)
        _binding = ItemMenuBinding.bind(this)
    }

    private fun setAttrs(attrs: AttributeSet){
        val typedArray = context.obtainStyledAttributes(attrs,R.styleable.MenuItem)
        binding.title.text = typedArray.getString(R.styleable.MenuItem_menu_text)
        binding.title.setTextColor(typedArray.getColor(R.styleable.MenuItem_menu_text_color,Color.BLACK))
        binding.icon.setImageResource(typedArray.getResourceId(R.styleable.MenuItem_menu_icon,R.drawable.ic_add))
        typedArray.recycle()
    }

    fun addOnClickListenerInButton(){
        binding.addButton.setOnClickListener {
            binding.expanded.isVisible = !binding.expanded.isVisible
        }
    }

}