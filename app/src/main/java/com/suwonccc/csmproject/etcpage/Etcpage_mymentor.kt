package com.suwonccc.csmproject.etcpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.suwonccc.csmproject.R
import kotlinx.android.synthetic.main.activity_etcpage_mymentor.*

class Etcpage_mymentor : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etcpage_mymentor)

        text1.text = "홍길동"
        text2.text = "성균관대학교"
        text3.text = "소프트웨어학과"
        text4.text = "서쪽에서 뿅 동쪽에서 뿅~"

        return_btn.setOnClickListener{
            this.finish()
        }
        val disconnect_btn = findViewById(R.id.disconnect_mentor_btn) as Button
        disconnect_btn.setOnClickListener{
            //Toast.makeText(this@Etcpage, "You clicked on TextView 'Click Me'.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Etcpage_disconnect::class.java)
            startActivity(intent)
        }
    }
}