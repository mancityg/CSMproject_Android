package com.suwonccc.csmproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make
import com.suwonccc.csmproject.mainpage_fragment.MainpageFragment
import com.suwonccc.csmproject.recyclerview.Mentee
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Time

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment)?.findNavController()!!
        navController?.let {
            main_nav_view.setupWithNavController(navController)
        }



    }
    var mBackWait : Long = 0
    lateinit var toast: Toast

    @SuppressLint("RestrictedApi")
    override fun onBackPressed() {
        val count = navController.backStack.count()
        if (count <= 2) {
            if(System.currentTimeMillis() - mBackWait >=2000 ) {
                Toast.makeText(this, "종료하시려면 한번 더 누르세요.", Toast.LENGTH_SHORT).show()
                mBackWait = System.currentTimeMillis()

            } else {
                finish() //액티비티 종료
            }
        } else {
            navController.popBackStack()
        }
    }

}