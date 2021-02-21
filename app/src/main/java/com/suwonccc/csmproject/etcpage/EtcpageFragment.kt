package com.suwonccc.csmproject.etcpage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.suwonccc.csmproject.Firstpage
import com.suwonccc.csmproject.R
import kotlinx.android.synthetic.main.fragment_etcpage.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EtcpageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EtcpageFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var type: String? = "menti"
    private var is_men: Boolean? = true

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    */

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_etcpage, container, false)
    }

    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        go_modify_btn.setOnClickListener {
            //Toast.makeText(this@Etcpage, "You clicked on TextView 'Click Me'.", Toast.LENGTH_SHORT).show()
            if (type == "mento") {
                activity?.let {
                    val intent = Intent(it, Etcpage_modify_mentor::class.java)
                    it.startActivity(intent)
                }
            } else if (type == "menti") {
                activity?.let {
                    val intent = Intent(it, Etcpage_modify_mentee::class.java)
                    it.startActivity(intent)
                }
            }
        }

        go_meninfo_btn.setOnClickListener {
            if (type == "mento") {
                if (is_men == true) {
                    val intent = Intent(getActivity(), Etcpage_mymentee_list::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(getActivity(), Etcpage_nomentee::class.java)
                    startActivity(intent)
                }
            } else if (type == "menti") {
                if (is_men == true) {
                    val intent = Intent(getActivity(), Etcpage_mymentor::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(getActivity(), Etcpage_nomentor::class.java)
                    startActivity(intent)
                }
            }
        }
        go_universitylife_btn.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://cccvlm.com/sns/"))
            startActivity(intent)
        }
        go_bibleessence_btn.setOnClickListener {
            var intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/c/BibleEssence/featured")
            )
            startActivity(intent)
        }

        logout_btn.setOnClickListener {
            activity?.let{
                val intent = Intent (it, Firstpage::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                it.startActivity(intent)
            }
            // todo: '종료하시겠습니까?' 팝업창 필요
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EtcpageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(): EtcpageFragment {
            return EtcpageFragment()
        }
    }
}