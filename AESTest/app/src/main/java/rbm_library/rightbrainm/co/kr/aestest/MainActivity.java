package rbm_library.rightbrainm.co.kr.aestest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText mTargetMsg;
    private Button Encbutton,Decbutton;

    private TextView targetmsg;

    public KeystoreAES keystoreAES;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            keystoreAES = new KeystoreAES();
        } catch (Exception e) {
            e.printStackTrace();

        }

        mTargetMsg = (EditText) findViewById(R.id.editText);
        Encbutton = (Button) findViewById(R.id.Encbutton);
        Decbutton = (Button) findViewById(R.id.Decbutton);
        targetmsg = (TextView)findViewById(R.id.targetmsg);

        Encbutton.setOnClickListener(this);
        Decbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {

        switch(view.getId())
        {
            case R.id.Encbutton:
                // 암호화
                    try
                    {
                        String mdata = mTargetMsg.getText().toString();
                        String data1 = keystoreAES.encryptText(mdata);
                        Log.d("DATA","<<<<< mdata1 : "+data1);
                        targetmsg.setText(data1);

                    }
                    catch(Exception e)
                    {
                        Log.d("ERROR",""+e.getMessage());
                    }
                    break;

            case R.id.Decbutton:
                // 복호화
                    try
                    {
                        String mdata = targetmsg.getText().toString();
                        String mdata3 = keystoreAES.decryptData(mdata);
                        Log.d("DATA","<<<<< mdata2 : "+mdata3);
                        targetmsg.setText(mdata3);
                    }
                    catch(Exception e)
                    {
                        Log.d("ERROR",""+e.getMessage());
                    }

                break;
        }
    }

}
