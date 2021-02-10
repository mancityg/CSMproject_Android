package com.suwonccc.csmproject.firstpage_fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.suwonccc.csmproject.MainActivity
import com.suwonccc.csmproject.R
import kotlinx.android.synthetic.main.fragment_login_main.*

class LoginMain : Fragment() {

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
            //카카오톡 로그인 구현

            /*
            *프로필 생성 화면 확인하기 위해
            *우선 카카오톡 로그인 버튼 누르면 프로필 생성 화면으로 이동하게 해놓음
             */
            navController.navigate(R.id.action_loginMain_to_loginProfile)
        }

        facebook_btn.setOnClickListener {
            //페이스북 로그인 구현

            /*
            *개발 용이하게 하기 위해
            *우선 페이스북 로그인 버튼 누르면 메인 화면으로 이동하게 해놓음
             */
            activity?.let{
                val intent = Intent (it, MainActivity::class.java)

                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                it.startActivity(intent)
            }
        }

        naver_btn.setOnClickListener {
            //인스타그램 로그인 구현
        }

        google_btn.setOnClickListener {
            //구글 로그인 구현
        }

    }
}