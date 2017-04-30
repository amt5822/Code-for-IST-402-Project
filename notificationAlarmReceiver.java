import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Anjelica on 4/22/2017.
 */
public class NotificationAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent service1 = new Intent(context, BookmarkAlarmService.class);
        context.startService(service1);

    }
}
