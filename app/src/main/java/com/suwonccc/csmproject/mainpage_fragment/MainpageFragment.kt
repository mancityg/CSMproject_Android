package com.suwonccc.csmproject.mainpage_fragment

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.suwonccc.csmproject.R
import com.suwonccc.csmproject.recyclerview.Mentee
import com.suwonccc.csmproject.recyclerview.Mentor
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_mainpage.*
import kotlinx.android.synthetic.main.fragment_mainpage.drawer_handle
import kotlinx.android.synthetic.main.fragment_mainpage.slidingDrawer
import kotlinx.android.synthetic.main.popup_info_mentor.*
import kotlinx.android.synthetic.main.popup_mentee_agreement_.*
import kotlinx.android.synthetic.main.popup_mentee_req_finished.*

class MainpageFragment : Fragment() {

    var sampleMentors = arrayListOf<Mentor>(
        Mentor("한지명", "prof_sample2", 23, true, "성균관대학교", "소프트웨어학과", "아기자기한", "늘 행복해요"),
        Mentor("이상호", "prof_sample1", 24, true, "성균관대학교", "소프트웨어학과", "남자", "#하이하이"),
        Mentor("한지은", "prof_sample3", 24, false, "아주대학교", "소프트웨어학과", "여자", "#하이하이")
    )

    lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mainpage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        find_mentor_btn.setOnClickListener{
            find_mentor_btn.isPressed = true

            activity?.let{
                val intent = Intent(it, MainSearchMentorActivity::class.java)
                it.startActivity(intent)
            }
            //navController.navigate(R.id.action_mainpage_to_mainpageWelcomeMentorFragment)
        }

        val face1 = this.resources.getIdentifier(sampleMentors[0].thumb, "drawable", this.context?.packageName)
        val face2 = this.resources.getIdentifier(sampleMentors[1].thumb, "drawable", this.context?.packageName)
        val face3 = this.resources.getIdentifier(sampleMentors[2].thumb, "drawable", this.context?.packageName)

        this.face1.setImageResource(face1)
        this.face2.setImageResource(face2)
        this.face3.setImageResource(face3)

        this.face1.setOnClickListener {
            show_mentor(sampleMentors[0])
        }
        this.face2.setOnClickListener {
            show_mentor(sampleMentors[1])
        }
        this.face3.setOnClickListener {
            show_mentor(sampleMentors[2])
        }

        this.slidingDrawer.setOnDrawerOpenListener {
            val resourceId = this.resources.getIdentifier("main_drawer_handle_down", "drawable", this.context?.packageName)
            this.drawer_handle.setImageResource(resourceId)
        }
        this.slidingDrawer.setOnDrawerCloseListener {
            val resourceId = this.resources.getIdentifier("main_drawer_handle", "drawable", this.context?.packageName)
            this.drawer_handle.setImageResource(resourceId)
        }


    }
    private fun show_mentor(mentor: Mentor){
        val builder = AlertDialog.Builder(requireContext())
        val dialogView = layoutInflater.inflate(R.layout.popup_info_mentor, null)

        val mentorImg = dialogView.findViewById<CircleImageView>(R.id.profile_photo)
        val mentorname = dialogView?.findViewById<AppCompatTextView>(R.id.item_name)
        val mentorcamp = dialogView?.findViewById<AppCompatTextView>(R.id.campus)
        val mentordept = dialogView?.findViewById<AppCompatTextView>(R.id.dept)
        val mentorage = dialogView?.findViewById<AppCompatTextView>(R.id.item_basic)
        val mentorgender = dialogView?.findViewById<AppCompatImageView>(R.id.item_gender)

        val resourceId = this.resources.getIdentifier(mentor.thumb, "drawable", this.context?.packageName)
        val genderImg : String = if(mentor.man){
            "main_male_icon"
        }else{
            "main_female_icon"
        }
        val genderImgId = this.resources.getIdentifier(genderImg, "drawable", this.context?.packageName)

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
    }

}