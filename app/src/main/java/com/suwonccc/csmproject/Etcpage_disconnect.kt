package com.suwonccc.csmproject

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import com.suwonccc.csmproject.R

class Etcpage_disconnect : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etcpage_disconnect)

        val on_btn1 = findViewById(R.id.check1_on) as CheckBox
        val off_btn1 = findViewById(R.id.check1_off) as CheckBox
        val on_btn2 = findViewById(R.id.check2_on) as CheckBox
        val off_btn2 = findViewById(R.id.check2_off) as CheckBox
        val on_btn3 = findViewById(R.id.check3_on) as CheckBox
        val off_btn3 = findViewById(R.id.check3_off) as CheckBox
        val on_btn4 = findViewById(R.id.check4_on) as CheckBox
        val off_btn4= findViewById(R.id.check4_off) as CheckBox

        on_btn1.setVisibility(View.INVISIBLE)
        off_btn1.setOnClickListener{
            on_btn1.setVisibility(View.VISIBLE)
            off_btn1.setVisibility(View.INVISIBLE)
        }
        on_btn1.setOnClickListener{
            off_btn1.setVisibility(View.VISIBLE)
            on_btn1.setVisibility(View.INVISIBLE)
        }

        on_btn2.setVisibility(View.INVISIBLE)
        off_btn2.setOnClickListener{
            on_btn2.setVisibility(View.VISIBLE)
            off_btn2.setVisibility(View.INVISIBLE)
        }
        on_btn2.setOnClickListener{
            off_btn2.setVisibility(View.VISIBLE)
            on_btn2.setVisibility(View.INVISIBLE)
        }

        on_btn3.setVisibility(View.INVISIBLE)
        off_btn3.setOnClickListener{
            on_btn3.setVisibility(View.VISIBLE)
            off_btn3.setVisibility(View.INVISIBLE)
        }
        on_btn3.setOnClickListener{
            off_btn3.setVisibility(View.VISIBLE)
            on_btn3.setVisibility(View.INVISIBLE)
        }

        on_btn4.setVisibility(View.INVISIBLE)
        off_btn4.setOnClickListener{
            on_btn4.setVisibility(View.VISIBLE)
            off_btn4.setVisibility(View.INVISIBLE)
        }
        on_btn4.setOnClickListener{
            off_btn4.setVisibility(View.VISIBLE)
            on_btn4.setVisibility(View.INVISIBLE)
        }

        val disconnect_btn= findViewById(R.id.disconnect_btn) as ImageView

        disconnect_btn.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.popup_etcpage_disconnect, null)
            //builder.setView(dialogView).show()
            builder.setView(dialogView)
            val infoDialog = builder.create()
            infoDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            infoDialog.setContentView(dialogView)
            infoDialog.show()
        }
    }
}
//dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT))
