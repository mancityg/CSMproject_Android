package com.suwonccc.csmproject.firstpage_fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.suwonccc.csmproject.R
import kotlinx.android.synthetic.main.fragment_login_mentor.*

class LoginMentor : Fragment() {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_mentor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        //공백 체크
        next_btn.setOnClickListener {
            next_btn.isSelected = true

            if (TextUtils.isEmpty(mentor_school_edittext.getText()) ||
                TextUtils.isEmpty(mentor_major_edittext.getText()) ||
                TextUtils.isEmpty(student_num_edittext.getText())
            ) {
                Toast.makeText(getActivity(), "작성하지 않은 항목이 있습니다", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(getActivity(), "모든 항목 완료", Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.action_loginMentor_to_loginComplete)
            }
        }

//        val autotextView = view.findViewById<AutoCompleteTextView>(R.id.school_text)
//        val schools = resources.getStringArray(R.array.Schools)
//        val adapter = ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, schools)
//
//        autotextView.setAdapter(adapter)
//
//        next_btn.setOnClickListener {
//            next_btn.isSelected = true
//
//            navController.navigate(R.id.action_loginMentor_to_loginComplete)
//        }
//
        back_btn.setOnClickListener {
            navController.navigate(R.id.action_loginMentor_to_loginProfile3)
        }



    }

}