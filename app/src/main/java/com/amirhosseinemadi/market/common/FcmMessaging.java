package com.amirhosseinemadi.market.common;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.amirhosseinemadi.market.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class FcmMessaging extends FirebaseMessagingService {


    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);

    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(AppCls.component.context(),"market");
        notification.setContentTitle(remoteMessage.getNotification().getTitle());
        notification.setContentText(remoteMessage.getNotification().getBody());
        notification.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        notification.setSmallIcon(R.drawable.ic_abstract);


        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(AppCls.component.context());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel("market","market", NotificationManager.IMPORTANCE_DEFAULT);
            managerCompat.createNotificationChannel(channel);
        }

        managerCompat.notify(1,notification.build());

    }
}
