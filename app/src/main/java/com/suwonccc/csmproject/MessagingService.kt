package com.suwonccc.csmproject

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.suwonccc.csmproject.alarmpage_fragment.AlarmInfo
import java.util.*

class MessagingService : FirebaseMessagingService() {
    companion object {
        private const val TAG = "MessagingService"
        private const val CHANNEL_ID = "CSM project"
        private const val ID_NORMAL_ALARM = 0
    }
    override fun onCreate() {
        createNotificationChannel()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.notification?.also {n->
            toAlarmInfo(n)?.also{
                // TODO: 2021-02-14 AlarmInfo를 DB에 저장
                createNotification(it)
            }
        }
    }

    override fun onNewToken(token: String) {
        sendToServer(token)
    }

    private fun sendToServer(token: String) {
        // TODO: 2021-02-14 멘토 멘티 관계 성립 - 특정 디바이스에 알람을 보내기 위해서 필요
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "알림 예제"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)

            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).apply {
                createNotificationChannel(channel)
            }
        }
    }

    private fun createNotification(alarmInfo: AlarmInfo) {
        val notification = NotificationCompat.Builder(this, Companion.CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(alarmInfo.category)
            .setContentText(alarmInfo.msg)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .build()

        with(NotificationManagerCompat.from(this)) {
            notify(Companion.ID_NORMAL_ALARM, notification)
        }
    }

    private fun toAlarmInfo(n: RemoteMessage.Notification): AlarmInfo? {
        val t = n.title ?: "전체공지"
        return n.body?.let {
            AlarmInfo(t, it,AlarmInfo.getImageByCategory(t),
                Calendar.getInstance().time)
        }
    }
}