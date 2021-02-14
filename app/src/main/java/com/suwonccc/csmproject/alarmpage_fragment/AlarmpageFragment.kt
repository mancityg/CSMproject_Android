package com.suwonccc.csmproject.alarmpage_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.suwonccc.csmproject.R
import kotlinx.android.synthetic.main.fragment_alarmpage.*

class AlarmpageFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_alarmpage, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        RV_alarm.apply {
            layoutManager = LinearLayoutManager(this@AlarmpageFragment.context)
            adapter = AlarmRecyclerViewAdapter(AlarmInfoDAO().findAll())
        }
    }
}