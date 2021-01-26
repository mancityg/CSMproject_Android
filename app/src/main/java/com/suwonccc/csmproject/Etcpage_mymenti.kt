package com.suwonccc.csmproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import com.suwonccc.csmproject.R
import kotlinx.android.synthetic.main.activity_etcpage_mymenti.*

class Etcpage_mymenti : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etcpage_mymenti)

        text1.text = "홍길동"
        text2.text = "성균관대학교"
        text3.text = "소프트웨어학과"
        text4.text = "서쪽에서 뿅 동쪽에서 뿅~"

        val return_btn= findViewById(R.id.return_btn) as LinearLayout
        return_btn.setOnClickListener{
            //Toast.makeText(this@Etcpage, "You clicked on TextView 'Click Me'.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Etcpage_mymentilist::class.java)
            startActivity(intent)
        }
        val disconnect_btn = findViewById(R.id.disconnect_mento_btn) as ImageView
        disconnect_btn.setOnClickListener{
            //Toast.makeText(this@Etcpage, "You clicked on TextView 'Click Me'.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Etcpage_disconnect::class.java)
            startActivity(intent)
        }
    }
}