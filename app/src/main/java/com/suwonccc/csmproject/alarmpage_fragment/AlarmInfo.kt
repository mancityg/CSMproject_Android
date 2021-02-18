package com.suwonccc.csmproject.alarmpage_fragment

import com.suwonccc.csmproject.R
import java.util.*

data class AlarmInfo(val category: String, val msg: String, val image: Int, val alarmDate: Date) {
    companion object {
        fun getImageByCategory(category: String): Int {
            return when (category) {
                "전체공지" -> R.drawable.alarm_icon_notification
                "댓글" -> R.drawable.alarm_icon_reply
                "맨토관계 성립" -> R.drawable.alarm_icon_relationship_formed
                "맨토 신청" -> R.drawable.alarm_icon_apply
                else -> R.drawable.alarm_icon_notification
            }
        }
    }
}

