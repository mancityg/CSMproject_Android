package com.suwonccc.csmproject.recyclerview
import android.view.View

class Mentee(val name: String, val thumb: String, val age: Int, val man: Boolean, val tag1: String, val tag2: String, val tag3: String, val tag4: String)


fun View.setHeight(value: Int) {
    val lp = layoutParams
    lp?.let {
        lp.height = value
        layoutParams = lp
    }
}