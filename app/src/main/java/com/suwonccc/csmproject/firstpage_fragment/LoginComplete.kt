package com.suwonccc.csmproject.firstpage_fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.suwonccc.csmproject.MainActivity
import com.suwonccc.csmproject.R
import kotlinx.android.synthetic.main.fragment_login_complete.*

class LoginComplete : Fragment() {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_complete, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        start_btn.setOnClickListener {
            start_btn.isSelected = true
            Toast.makeText(getActivity(), "시작", Toast.LENGTH_LONG).show()

            // Mainpage activity로 이동
            activity?.let{
                val intent = Intent (it, MainActivity::class.java)

                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                it.startActivity(intent)
            }
        }
    }
}