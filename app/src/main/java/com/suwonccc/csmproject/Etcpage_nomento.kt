package com.suwonccc.csmproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.suwonccc.csmproject.R
import kotlinx.android.synthetic.main.activity_etcpage_nomento.*

class Etcpage_nomento : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etcpage_nomento)

        return_btn.setOnClickListener{
            this.finish()
        }
    }
}