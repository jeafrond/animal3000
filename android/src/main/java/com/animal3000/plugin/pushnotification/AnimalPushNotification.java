package com.animal3000.plugin.pushnotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.NotificationTarget;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.NativePlugin;

import java.util.concurrent.atomic.AtomicInteger;

@NativePlugin()
public class AnimalPushNotification extends Plugin {

    //private AnimalPushNotification implementation = new AnimalPushNotification();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
      //  ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }


    @PluginMethod()
    public void showNotification(PluginCall call) {
        String urlImg = call.getString("urlImg");
        String Color = call.getString("Color");
        String Text = call.getString("Text");
        String urlImagePartenaire = call.getString("urlImagePartenaire");

        createNotificationChannel();

        int layoutId = R.layout.notif_found;
        if(Color.equals("red"))
            layoutId = R.layout.notif_lost;

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());
        RemoteViews view = new RemoteViews(getContext().getPackageName(), layoutId);
        AtomicInteger integer = new AtomicInteger(200);
       // Intent intent = new Intent(getContext(), MainActivity.class);
       // PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), getContext().getString(R.string.app_name))
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(getContext().getString(R.string.app_name))
                .setContentText("Alerte")
                .setCustomContentView(view)
                .setCustomBigContentView(view)
                //.setContentIntent(pendingIntent)
                .setPriority(NotificationManager.IMPORTANCE_MAX);

        Notification notification = builder.build();
        view.setTextViewText(R.id.content_banner, Text);

        //set partenaire image
//    NotificationTarget notificationTarget2 = new NotificationTarget(
//            this,
//            R.id.big_icon,
//            view,
//            notification,
//            integer.get());
//    RequestOptions options2 = new RequestOptions()
//            .placeholder(R.drawable.royalcanin)
//            .error(R.drawable.royalcanin);
//    Glide.with(getApplicationContext())
//            .asBitmap()
//            .load(urlImagePartenaire)
//            .apply(options2)
//            .into( notificationTarget2 );

        notificationManager.notify(integer.incrementAndGet(), notification);
        //set animal image
        NotificationTarget notificationTarget = new NotificationTarget(
                getContext(),
                R.id.big_icon,
                view,
                notification,
                integer.get());
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo);
        Glide.with(getContext())
                .asBitmap()
                .load(urlImg)
                .apply(options)
                .into( notificationTarget );

        JSObject ret = new JSObject();
        ret.put("success", "true");
        call.resolve(ret);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getContext().getString(R.string.app_name);
            String description = getContext().getString(R.string.app_name);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(getContext().getString(R.string.app_name), name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
