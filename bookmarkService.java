package manotifications;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Anjelica on 4/19/2017.
 */
public class BookmarkAlarmService extends IntentService {

    NotificationCompat.Builder mBuilder;

    public BookmarkAlarmService(String name) {
        super(name);
    }

    public BookmarkAlarmService() {
        super("BookMarkAlarmService");
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handleStart(intent, startId);
        return START_NOT_STICKY;


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    void handleStart(Intent intent, int startId) {
        final NotificationManager mManager = (NotificationManager) this.getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(this.getApplicationContext(), MainActivity.class);
        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(this.getApplicationContext(), 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);


        mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(largeIcon)
                .setContentTitle(getString(R.string.parking_busy))
                .setContentText(getString(R.string.parking_busy_text))
                .setOngoing(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Parking "))
                .setContentIntent(pendingNotificationIntent);
        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        mManager.notify(1, mBuilder.build());

        Handler h = new Handler();
        long delayInMilliseconds = 1000*60*80;
        h.postDelayed(new Runnable() {
            public void run() {
                mManager.cancel(1);
            }
        }, delayInMilliseconds);
    }

}
