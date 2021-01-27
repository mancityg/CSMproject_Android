package com.suwonccc.csmproject.mainpage_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.suwonccc.csmproject.R
import com.suwonccc.csmproject.recyclerview.MenteelistAdapter
import com.suwonccc.csmproject.recyclerview.Mentee
import com.suwonccc.csmproject.recyclerview.setHeight
import kotlinx.android.synthetic.main.fragment_mainpage_welcome_mentor.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MainpageWelcomeMentorFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var menteeList = arrayListOf<Mentee>(
        Mentee("한지명", "main_face_default", 23, true, "소프트웨어학과", "23살", "남자", "#하이하이"),
        Mentee("채세이", "main_face_default", 24, false, "소프트웨어학과", "24살", "여자", "#하이하이"),
        Mentee("한지은", "main_face_default", 24, false, "소프트웨어학과", "24살", "여자", "#하이하이"),
        Mentee("고은서", "main_face_default", 23, false, "소프트웨어학과", "23살", "여자", "#하이하이"),
        Mentee("이상호", "main_face_default", 24, true, "소프트웨어학과", "24살", "남자", "#하이하이")
    )




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mainpage_welcome_mentor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (menteeList.isNullOrEmpty()){
            slidingDrawer.setHeight(resources.getDimensionPixelSize(R.dimen.main_emptyview_height))
            mentee_RecvView.visibility = View.GONE
            emptyView.visibility = View.VISIBLE
        }
        else{
            mentee_RecvView.visibility = View.VISIBLE
            emptyView.visibility = View.GONE
        }

        val mAdapter = MenteelistAdapter(this.requireActivity(), menteeList)
        mentee_RecvView.adapter = mAdapter

        val lm = LinearLayoutManager(this.requireActivity())
        mentee_RecvView.layoutManager = lm
        mentee_RecvView.setHasFixedSize(true)
    }



    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainpageWelcomeMentorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}