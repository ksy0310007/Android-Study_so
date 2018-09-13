package renomedia.co.kr.broadcast;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 로컬 브로드 캐스트
    private BroadcastReceiver mReceiver;

    // 나만의 방송
    public static final String MY_ACTION = "renomedia.co.kr.broadcast.action.ACTION_MY_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mReceiver = new MyReceiver();
    }

    // 로컬 브로드 캐스트
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(MyReceiver.MY_ACTION);
        registerReceiver(mReceiver,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
        Toast.makeText(MainActivity.this, "전원이 해제 됨",Toast.LENGTH_SHORT).show();
    }


    // 나만의 방송 => 보내기
    public void sendMyBroadcast(View view){
        Intent intent = new Intent(MyReceiver.MY_ACTION);
        sendBroadcast(intent);
    }

}
