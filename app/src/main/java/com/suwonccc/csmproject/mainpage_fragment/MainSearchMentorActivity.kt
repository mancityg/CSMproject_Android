package com.suwonccc.csmproject.mainpage_fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.suwonccc.csmproject.R
import com.suwonccc.csmproject.recyclerview.Mentor
import com.suwonccc.csmproject.recyclerview.SearchMentorlistAdapter
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_main_search_mentor.*
import kotlinx.android.synthetic.main.popup_info_mentor_search.*
import kotlinx.android.synthetic.main.popup_mentee_agreement_.*
import kotlinx.android.synthetic.main.popup_mentee_req_finished.*

class MainSearchMentorActivity : AppCompatActivity() {
    var mentorList = arrayListOf<Mentor>(
        Mentor("한지명", "main_face_default", 23, true, "성균관대", "소프트웨어학과", "남자", "#하이하이"),
        Mentor("채세이", "main_face_default", 24, false, "성균관대", "소프트웨어학과", "여자", "#하이하이"),
        Mentor("한지은", "main_face_default", 24, false, "아주대", "소프트웨어학과", "여자", "#하이하이"),
        Mentor("고은서1", "main_face_default", 23, false, "성균관대 자과캠", "소프트웨어학과", "여자", "#하이하이"),
        Mentor("이상호", "main_face_default", 24, true, "성균관대", "소프트웨어학과", "남자", "#하이하이"),
        Mentor("한지은2", "main_face_default", 24, false, "아주대", "소프트웨어학과", "여자", "#하이하이"),
        Mentor("고은서2", "main_face_default", 23, false, "성균관대 자과캠", "소프트웨어학과", "여자", "#하이하이"),
        Mentor("이상호2", "main_face_default", 24, true, "성균관대", "소프트웨어학과", "남자", "#하이하이"),
        Mentor("한지은3", "main_face_default", 24, false, "아주대", "소프트웨어학과", "여자", "#하이하이"),
        Mentor("고은서3", "main_face_default", 23, false, "성균관대 자과캠", "소프트웨어학과", "여자", "#하이하이"),
        Mentor("이상호3", "main_face_default", 24, true, "성균관대", "소프트웨어학과", "남자", "#하이하이")
    )

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_search_mentor)



        back_btn.setOnClickListener{
            this.finish()
        }

        search_btn.setOnClickListener {
            search_btn.isPressed = true

            //검색 기능 구현


            val mAdapter = SearchMentorlistAdapter(this, mentorList)
            mentor_RecvView.adapter = mAdapter

            val lm = LinearLayoutManager(this)
            mentor_RecvView.layoutManager = lm
            mentor_RecvView.setHasFixedSize(true)

            mAdapter.setItemClickListener(object: SearchMentorlistAdapter.ItemClickListener{
                override fun onClick(view: View, position: Int){
                    // 해당 멘토 팝업창 띄우기
                    val target = mentorList[position]
                    show_mentor(target)


                }
            })
            search_btn.isSelected = false
            search_btn.isPressed = false
        }
    }

    private fun show_mentor(mentor: Mentor){
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.popup_info_mentor_search, null)

        val mentorImg = dialogView.findViewById<CircleImageView>(R.id.profile_photo)
        val mentorname = dialogView?.findViewById<AppCompatTextView>(R.id.item_name)
        val mentorcamp = dialogView?.findViewById<AppCompatTextView>(R.id.campus)
        val mentordept = dialogView?.findViewById<AppCompatTextView>(R.id.dept)
        val mentorage = dialogView?.findViewById<AppCompatTextView>(R.id.item_basic)
        val mentorgender = dialogView?.findViewById<AppCompatImageView>(R.id.item_gender)

        val resourceId = this.resources.getIdentifier(mentor.thumb, "drawable", this.packageName)
        val genderImg : String = if(mentor.man){
            "main_male_icon"
        }else{
            "main_female_icon"
        }
        val genderImgId = this.resources.getIdentifier(genderImg, "drawable", this.packageName)

        var basic = " ( "
        basic += mentor.age.toString()
        basic += " /"

        mentorname?.text = mentor.name
        mentorcamp?.text = mentor.campus
        mentordept?.text = mentor.dept
        mentorage?.text = basic
        mentorImg?.setImageResource(resourceId)
        mentorgender?.setImageResource(genderImgId)

        builder.setView(dialogView)

        val infoDialog = builder.create()
        infoDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        infoDialog.setContentView(dialogView)
        infoDialog.show()

        infoDialog.popup_back_btn.setOnClickListener {
            infoDialog.popup_back_btn.isPressed = true
            infoDialog.dismiss()
        }
        infoDialog.popup_req_btn.setOnClickListener {
            //신청하기 버튼 클릭시
            infoDialog.popup_req_btn.isPressed = true

            val agrbuilder = AlertDialog.Builder(this)
            val agrdialogView = layoutInflater.inflate(R.layout.popup_mentee_agreement_, null)

            agrbuilder.setView(agrdialogView)

            val agrDialog = agrbuilder.create()
            agrDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            agrDialog.setContentView(agrdialogView)
            agrDialog.show()

            agrDialog.popup_agree_back_btn.setOnClickListener {
                agrDialog.popup_agree_back_btn.isPressed = true
                agrDialog.dismiss()
            }
            agrDialog.popup_agree_req_btn.setOnClickListener {
                agrDialog.popup_agree_req_btn.isPressed = true

                regist_mentoring(mentor)
                //멘티 신청 완료
                val finbuilder = AlertDialog.Builder(this)
                val findialogView = layoutInflater.inflate(R.layout.popup_mentee_req_finished, null)

                finbuilder.setView(findialogView)

                val finDialog = finbuilder.create()
                finDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                finDialog.setContentView(findialogView)
                finDialog.setCancelable(false)
                finDialog.show()
                finDialog.fin_btn.setOnClickListener {
                    finDialog.fin_btn.isPressed = true

                    infoDialog.dismiss()
                    agrDialog.dismiss()
                    finDialog.dismiss()
                    finish()
                }
            }
        }

    }

    fun regist_mentoring(mentor: Mentor){
        Toast.makeText(this, mentor.name+" 님에게 멘토링 신청", Toast.LENGTH_SHORT).show()
    }
}

