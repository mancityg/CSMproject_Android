package com.suwonccc.csmproject.alarmpage_fragment

import android.annotation.SuppressLint
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.suwonccc.csmproject.R
import kotlinx.android.synthetic.main.alarm_item.view.*
import java.util.*

class AlarmRecyclerViewAdapter(private val alarmInfoList: List<AlarmInfo>) :
    RecyclerView.Adapter<AlarmViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.alarm_item, parent, false)
        return AlarmViewHolder(view)
    }

    override fun getItemCount() = alarmInfoList.size

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int)
        = holder.bind(alarmInfoList[position])

}

class AlarmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    @SuppressLint("ShowToast")
    fun bind(alarmInfo: AlarmInfo) {
        itemView.alarm_TV_category.text = alarmInfo.category
        itemView.alarm_TV_msg.text = alarmInfo.msg
        itemView.alarm_IV_category.setImageResource(alarmInfo.image)
        itemView.alarm_TV_time.text = DateUtils.getRelativeTimeSpanString(alarmInfo.alarmDate.time, Calendar.getInstance().time.time, 0).toString()

        itemView.setOnClickListener{
            // TODO: 2021-02-09 다른 화면으로 이동 가능하게 만들기
            Toast.makeText(itemView.context, "클릭", Toast.LENGTH_SHORT).show()
        }
    }
}