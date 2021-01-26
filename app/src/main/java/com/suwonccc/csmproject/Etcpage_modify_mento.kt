package com.suwonccc.csmproject

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.suwonccc.csmproject.R
import kotlinx.android.synthetic.main.activity_etcpage_modify_mento.*

class Etcpage_modify_mento : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etcpage_modify_mento)

        val name = "홍길동"
        val age = "20"
        val school = "성균관대학교"
        val major = "소프트웨어학과"
        val addr = "수원시"
        val email = "0000@gmail.com"
        val birth = "2000.01.01"
        val msg = "안녕하세요:)"

        edit_text1.setText(name)
        edit_text2.setText(age)
        edit_text3.setText(school)
        edit_text4.setText(major)
        edit_text5.setText(addr)
        edit_text6.setText(email)
        edit_text7.setText(birth)
        edit_text8.setText(msg)
        disabled()

        val return_btn= findViewById(R.id.return_btn) as LinearLayout
        val modify_btn = findViewById(R.id.modify_btn) as ImageView
        val save_btn = findViewById(R.id.save_btn) as ImageView

        save_btn.setVisibility(View.INVISIBLE)

        return_btn.setOnClickListener{
            //Toast.makeText(this@Etcpage, "You clicked on TextView 'Click Me'.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        modify_btn.setOnClickListener{
            enabled()
            save_btn.setVisibility(View.VISIBLE)
            modify_btn.setVisibility(View.INVISIBLE)
        }

        save_btn.setOnClickListener{
            val name = edit_text1.getText().toString()
            disabled()
            save_btn.setVisibility(View.INVISIBLE)
            modify_btn.setVisibility(View.VISIBLE)
        }
    }
    fun enabled(){
        edit_text1.isEnabled = true
        edit_text1.setTextColor(Color.parseColor("#666666"))
        edit_text2.isEnabled = true
        edit_text2.setTextColor(Color.parseColor("#666666"))
        edit_text3.isEnabled = true
        edit_text3.setTextColor(Color.parseColor("#666666"))
        edit_text4.isEnabled = true
        edit_text4.setTextColor(Color.parseColor("#666666"))
        edit_text5.isEnabled = true
        edit_text5.setTextColor(Color.parseColor("#666666"))
        edit_text6.isEnabled = true
        edit_text6.setTextColor(Color.parseColor("#666666"))
        edit_text7.isEnabled = true
        edit_text7.setTextColor(Color.parseColor("#666666"))
        edit_text8.isEnabled = true
        edit_text8.setTextColor(Color.parseColor("#666666"))
    }

    fun disabled(){
        edit_text1.isEnabled = false
        edit_text1.setTextColor(Color.parseColor("#333333"))
        edit_text2.isEnabled = false
        edit_text2.setTextColor(Color.parseColor("#333333"))
        edit_text3.isEnabled = false
        edit_text3.setTextColor(Color.parseColor("#333333"))
        edit_text4.isEnabled = false
        edit_text4.setTextColor(Color.parseColor("#333333"))
        edit_text5.isEnabled = false
        edit_text5.setTextColor(Color.parseColor("#333333"))
        edit_text6.isEnabled = false
        edit_text6.setTextColor(Color.parseColor("#333333"))
        edit_text7.isEnabled = false
        edit_text7.setTextColor(Color.parseColor("#333333"))
        edit_text8.isEnabled = false
        edit_text8.setTextColor(Color.parseColor("#333333"))
    }
}