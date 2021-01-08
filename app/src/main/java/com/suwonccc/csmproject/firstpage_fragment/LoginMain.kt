package com.suwonccc.csmproject.firstpage_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.suwonccc.csmproject.R
import kotlinx.android.synthetic.main.fragment_login_main.*

class LoginMain : Fragment() {

    companion object {
        private const val TAG = "LoginMain"
    }

    lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        kakao_btn.setOnClickListener {

        }

        facebook_btn.setOnClickListener {

        }

        insta_btn.setOnClickListener {

        }

        google_btn.setOnClickListener {

        }

    }
}