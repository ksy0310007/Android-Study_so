package renomedia.co.kr.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    public static final String MY_ACTION = "renomedia.co.kr.broadcast.action.ACTION_MY_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {

//        throw new UnsupportedOperationException("Not yet implemented");

        if (Intent.ACTION_POWER_CONNECTED.equals(intent.getAction())) {
            Toast.makeText(context, "전원이 연결 됨",Toast.LENGTH_SHORT).show();

        }
        if(Intent.ACTION_POWER_DISCONNECTED.equals(intent.getAction())) {
            Toast.makeText(context, "전원이 해제 됨",Toast.LENGTH_SHORT).show();
        }

        // 나만의 방송 => 수신하기
        else if (MY_ACTION.equals(intent.getAction())){
            Toast.makeText(context, "나만의 방송 입니다.",Toast.LENGTH_SHORT).show();

            // 이후의 브로드캐스트의 전파 막기
            abortBroadcast();
        }
    }
}
