package com.suwonccc.csmproject.alarmpage_fragment

import android.annotation.SuppressLint
import com.suwonccc.csmproject.R
import java.text.SimpleDateFormat


// TODO: 2021-02-14 Room DAO로 바꾸기
class AlarmInfoDAO {
    @SuppressLint("SimpleDateFormat")
    private val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun findAll(): List<AlarmInfo> {
        val result = mutableListOf<AlarmInfo>()
        result += AlarmInfo(
            "전체공지",
            "안녕하세요. 크선멘 개발팀 여러분 힘내십시오!",
            R.drawable.alarm_icon_notification,
            dateFormat.parse("2021-02-09 20:50")
        )
        result += AlarmInfo(
            "댓글",
            "우와~ 너무 기대가 되네요!!\n좋은 정보 정말 감사합니다ㅎㅎ",
            R.drawable.alarm_icon_reply,
            dateFormat.parse("2021-02-08 11:49")
        )
        result += AlarmInfo(
            "맨토관계 성립",
            "축하합니다. 김 민토와 관계가 성립되었습니다.",
            R.drawable.alarm_icon_relationship_formed,
            dateFormat.parse("2021-02-03 23:49")
        )
        result += AlarmInfo(
            "맨토 신청",
            "고은서 멘티로부터 멘토링 신청이 왔습니다.",
            R.drawable.alarm_icon_apply,
            dateFormat.parse("2021-01-28 11:49")
        )
        return result
    }
}