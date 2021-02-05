package com.suwonccc.csmproject.firstpage_fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.suwonccc.csmproject.R
import kotlinx.android.synthetic.main.fragment_login_extra_info.*
import kotlinx.android.synthetic.main.fragment_login_extra_info.next_btn

class LoginExtraInfo : Fragment() {

    lateinit var navController: NavController
    private var personalityFlag = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_extra_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)


        /* 이전 Fragment에서 데이터 받아오기 */
        val args: LoginExtraInfoArgs by navArgs()
        val isMentor = args.isMentor


        /* 뒤로가기 버튼 클릭 */
        back_btn.setOnClickListener {
            if (isMentor) {
                navController.navigate(R.id.action_loginExtraInfo_to_loginMentor_back)
            } else {
                navController.navigate(R.id.action_loginExtraInfo_to_loginMentee_back)
            }
        }


        /* 감정 상태 버튼 클릭 */
        emotion_btn_1.setOnClickListener {
            emotion_btn_1.isSelected = true

            emotion_btn_2.isSelected = false
            emotion_btn_3.isSelected = false
            emotion_btn_4.isSelected = false
            emotion_btn_5.isSelected = false
            emotion_btn_6.isSelected = false
            emotion_btn_7.isSelected = false
            emotion_btn_8.isSelected = false
            emotion_btn_9.isSelected = false
        }

        emotion_btn_2.setOnClickListener {
            emotion_btn_2.isSelected = true

            emotion_btn_1.isSelected = false
            emotion_btn_3.isSelected = false
            emotion_btn_4.isSelected = false
            emotion_btn_5.isSelected = false
            emotion_btn_6.isSelected = false
            emotion_btn_7.isSelected = false
            emotion_btn_8.isSelected = false
            emotion_btn_9.isSelected = false
        }

        emotion_btn_3.setOnClickListener {
            emotion_btn_3.isSelected = true

            emotion_btn_1.isSelected = false
            emotion_btn_2.isSelected = false
            emotion_btn_4.isSelected = false
            emotion_btn_5.isSelected = false
            emotion_btn_6.isSelected = false
            emotion_btn_7.isSelected = false
            emotion_btn_8.isSelected = false
            emotion_btn_9.isSelected = false
        }

        emotion_btn_4.setOnClickListener {
            emotion_btn_4.isSelected = true

            emotion_btn_1.isSelected = false
            emotion_btn_2.isSelected = false
            emotion_btn_3.isSelected = false
            emotion_btn_5.isSelected = false
            emotion_btn_6.isSelected = false
            emotion_btn_7.isSelected = false
            emotion_btn_8.isSelected = false
            emotion_btn_9.isSelected = false
        }

        emotion_btn_5.setOnClickListener {
            emotion_btn_5.isSelected = true

            emotion_btn_1.isSelected = false
            emotion_btn_2.isSelected = false
            emotion_btn_3.isSelected = false
            emotion_btn_4.isSelected = false
            emotion_btn_6.isSelected = false
            emotion_btn_7.isSelected = false
            emotion_btn_8.isSelected = false
            emotion_btn_9.isSelected = false
        }

        emotion_btn_6.setOnClickListener {
            emotion_btn_6.isSelected = true

            emotion_btn_1.isSelected = false
            emotion_btn_2.isSelected = false
            emotion_btn_3.isSelected = false
            emotion_btn_4.isSelected = false
            emotion_btn_5.isSelected = false
            emotion_btn_7.isSelected = false
            emotion_btn_8.isSelected = false
            emotion_btn_9.isSelected = false
        }

        emotion_btn_7.setOnClickListener {
            emotion_btn_7.isSelected = true

            emotion_btn_1.isSelected = false
            emotion_btn_2.isSelected = false
            emotion_btn_3.isSelected = false
            emotion_btn_4.isSelected = false
            emotion_btn_5.isSelected = false
            emotion_btn_6.isSelected = false
            emotion_btn_8.isSelected = false
            emotion_btn_9.isSelected = false
        }

        emotion_btn_8.setOnClickListener {
            emotion_btn_8.isSelected = true

            emotion_btn_1.isSelected = false
            emotion_btn_2.isSelected = false
            emotion_btn_3.isSelected = false
            emotion_btn_4.isSelected = false
            emotion_btn_5.isSelected = false
            emotion_btn_6.isSelected = false
            emotion_btn_7.isSelected = false
            emotion_btn_9.isSelected = false
        }

        emotion_btn_9.setOnClickListener {
            emotion_btn_9.isSelected = true

            emotion_btn_1.isSelected = false
            emotion_btn_2.isSelected = false
            emotion_btn_3.isSelected = false
            emotion_btn_4.isSelected = false
            emotion_btn_5.isSelected = false
            emotion_btn_6.isSelected = false
            emotion_btn_7.isSelected = false
            emotion_btn_8.isSelected = false
        }


        /* 성격 버튼 클릭 */
        personality_btn_1.setOnClickListener {
            checkInputPersonality(personality_btn_1)
        }

        personality_btn_2.setOnClickListener {
            checkInputPersonality(personality_btn_2)
        }

        personality_btn_3.setOnClickListener {
            checkInputPersonality(personality_btn_3)
        }

        personality_btn_4.setOnClickListener {
            checkInputPersonality(personality_btn_4)
        }

        personality_btn_5.setOnClickListener {
            checkInputPersonality(personality_btn_5)
        }

        personality_btn_6.setOnClickListener {
            checkInputPersonality(personality_btn_6)
        }

        personality_btn_7.setOnClickListener {
            checkInputPersonality(personality_btn_7)
        }

        personality_btn_8.setOnClickListener {
            checkInputPersonality(personality_btn_8)
        }

        personality_btn_9.setOnClickListener {
            checkInputPersonality(personality_btn_9)
        }


        /* 입력창 유효성 체크 */
        next_btn.setOnClickListener {
            next_btn.isSelected = true
            checkInputBlank()
        }
    } //onViewCreated

    fun checkInputBlank() {
        if (TextUtils.isEmpty(nickname_text.text)) {
            Toast.makeText(requireActivity(), "별명을 작성해주세요", Toast.LENGTH_SHORT).show()
            next_btn.isSelected = false
            return
        } else if (!checkInputEmotionBlank()) {
            Toast.makeText(requireActivity(), "감정상태를 선택해주세요", Toast.LENGTH_SHORT).show()
            next_btn.isSelected = false
            return
        } else if (!checkInputPersonalityBlank()) {
            Toast.makeText(requireActivity(), "성격을 최소 1개 선택해주세요", Toast.LENGTH_SHORT).show()
            next_btn.isSelected = false
            return
        } else {
            navController.navigate(R.id.action_loginExtraInfo_to_loginComplete)
        }
    }

    fun checkInputEmotionBlank(): Boolean {
        return !(!emotion_btn_1.isSelected && !emotion_btn_2.isSelected && !emotion_btn_3.isSelected &&
                !emotion_btn_4.isSelected && !emotion_btn_5.isSelected && !emotion_btn_6.isSelected &&
                !emotion_btn_7.isSelected && !emotion_btn_8.isSelected && !emotion_btn_9.isSelected)
    }

    fun checkInputPersonalityBlank(): Boolean {
        return !(!personality_btn_1.isSelected && !personality_btn_2.isSelected && !personality_btn_3.isSelected &&
                !personality_btn_4.isSelected && !personality_btn_5.isSelected && !personality_btn_6.isSelected &&
                !personality_btn_7.isSelected && !personality_btn_8.isSelected && !personality_btn_9.isSelected)
    }

    fun checkInputPersonality(personality_btn: Button) {
        if (!personality_btn.isSelected) {
            personalityFlag++
            if (personalityFlag >= 3) {
                Toast.makeText(requireActivity(), "성격은 최대 2개까지 선택 가능", Toast.LENGTH_SHORT).show()
                personality_btn_1.isSelected = false
                personality_btn_2.isSelected = false
                personality_btn_3.isSelected = false
                personality_btn_4.isSelected = false
                personality_btn_5.isSelected = false
                personality_btn_6.isSelected = false
                personality_btn_7.isSelected = false
                personality_btn_8.isSelected = false
                personality_btn_9.isSelected = false

//                personality_btn_1.isEnabled = true
//                personality_btn_2.isEnabled = true
//                personality_btn_3.isEnabled = true
//                personality_btn_4.isEnabled = true
//                personality_btn_5.isEnabled = true
//                personality_btn_6.isEnabled = true
//                personality_btn_7.isEnabled = true
//                personality_btn_8.isEnabled = true
//                personality_btn_9.isEnabled = true

                personalityFlag = 0
            } else {
                personality_btn.isSelected = true
            }
        } else {
            personality_btn.isSelected = false
//            personality_btn.isEnabled = true
            personalityFlag--
        }
        return
    }
}