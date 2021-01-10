package com.suwonccc.csmproject.firstpage_fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.suwonccc.csmproject.R
import kotlinx.android.synthetic.main.fragment_login_profile.*

class LoginProfile : Fragment() {

    lateinit var navController : NavController
    private lateinit var addressEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var birthdayEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        addressEditText = view.findViewById(R.id.address_text)
        emailEditText = view.findViewById(R.id.email_text)
        birthdayEditText = view.findViewById(R.id.birthday_text)

        next_btn.setOnClickListener {
            if (TextUtils.isEmpty(addressEditText.getText()) ||
                TextUtils.isEmpty(emailEditText.getText()) ||
                TextUtils.isEmpty(birthdayEditText.getText())) {

            }

            navController.navigate(R.id.action_loginProfile_to_loginMentor)
        }
    }
}