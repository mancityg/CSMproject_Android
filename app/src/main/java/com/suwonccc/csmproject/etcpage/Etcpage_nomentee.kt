package com.suwonccc.csmproject.etcpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.suwonccc.csmproject.R
import kotlinx.android.synthetic.main.activity_etcpage_nomentee.*

class Etcpage_nomentee : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etcpage_nomentee)

        return_btn.setOnClickListener{
            this.finish()
        }
    }
}
