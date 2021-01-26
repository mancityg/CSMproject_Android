package com.suwonccc.csmproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.suwonccc.csmproject.R

class Etcpage_nomenti : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etcpage_nomenti)

        val return_btn= findViewById(R.id.return_btn) as LinearLayout
        return_btn.setOnClickListener{
            //Toast.makeText(this@Etcpage, "You clicked on TextView 'Click Me'.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
