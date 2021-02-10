package com.suwonccc.csmproject.firstpage_fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.suwonccc.csmproject.R
import kotlinx.android.synthetic.main.fragment_login_mentor.*
import kotlinx.android.synthetic.main.fragment_login_mentor.next_btn
import kotlinx.android.synthetic.main.fragment_login_profile.*

class LoginMentor : Fragment() {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
        return inflater.inflate(R.layout.fragment_login_mentor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        /* 입력창 유효성 체크 */
        next_btn.setOnClickListener {
            next_btn.isSelected = true

            if (TextUtils.isEmpty(mentor_school_edittext.getText()) ||
                TextUtils.isEmpty(mentor_major_edittext.getText())
            ) {
                Toast.makeText(getActivity(), "작성하지 않은 항목이 있습니다", Toast.LENGTH_SHORT).show()
                next_btn.isSelected = false
            } else {
                Toast.makeText(getActivity(), "모든 항목 완료", Toast.LENGTH_SHORT).show()
                val action = LoginMentorDirections.actionLoginMentorToLoginExtraInfo(true)
                navController.navigate(action)
            }
        }

        /* 학교 검색 자동완성 */
        val textView = view.findViewById(R.id.mentor_school_edittext) as AutoCompleteTextView
        val universities: Array<out String> = resources.getStringArray(R.array.universities_array)
        ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, universities).also { adapter ->
            textView.setAdapter(adapter)
        }

        back_btn.setOnClickListener {
            navController.navigate(R.id.action_loginMentor_to_loginProfile_back)
        }



    }

}