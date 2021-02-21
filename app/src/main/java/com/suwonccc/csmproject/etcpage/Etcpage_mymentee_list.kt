package com.suwonccc.csmproject.etcpage
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.suwonccc.csmproject.R
import kotlinx.android.synthetic.main.activity_etcpage_mymentee_list.*

class Etcpage_mymentee_list : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etcpage_mymentee_list)

        return_btn.setOnClickListener{
            this.finish()
        }


        val button1 = findViewById(R.id.button1) as Button
        button1.setOnClickListener {
            //Toast.makeText(this@Etcpage, "You clicked on TextView 'Click Me'.", Toast.LENGTH_SHORT).show()
            this.finish()
            val intent = Intent(this, Etcpage_mymentee::class.java)
            startActivity(intent)
        }
    }
}