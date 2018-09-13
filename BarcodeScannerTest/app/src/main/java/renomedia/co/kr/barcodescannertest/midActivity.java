package renomedia.co.kr.barcodescannertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class midActivity extends AppCompatActivity {

    Button button,libtest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mid);

        button = (Button)findViewById(R.id.button);
        libtest = (Button)findViewById(R.id.libtest);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(midActivity.this,QRScanActivity.class);
                startActivity(i);
            }
        });


    }
}
