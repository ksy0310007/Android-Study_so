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

    private static final String CRYPTO_MODE_CRYPT = "암호화";
    private static final String CRYPTO_MODE_DECRYPT = "복호화";

    private EditText mTargetMsg;
    private Button mButton;
    private TextView targetmsg;
    private boolean mCryptoMode;
    public KeystoreAES keystoreAES;
    public KeystoreAES.EnCryptor encryptor;
    public KeystoreAES.DeCryptor decryptor;


    private static final String SAMPLE_ALIAS = "PASSALIAS";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        keystoreAES = new KeystoreAES();

        encryptor = new KeystoreAES.EnCryptor();
        try {
            decryptor = new KeystoreAES.DeCryptor();
        } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException |
                IOException e) {
            e.printStackTrace();
        }


        mTargetMsg = (EditText) findViewById(R.id.editText);
        mButton = (Button) findViewById(R.id.button);
        targetmsg = (TextView)findViewById(R.id.targetmsg);

        mCryptoMode = false;
        mButton.setText(CRYPTO_MODE_CRYPT);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.button:
                // 암호화
                if(mCryptoMode)
                {
                    try
                    {
                        mCryptoMode = false;
                        mButton.setText(CRYPTO_MODE_DECRYPT);
                        String mdata = mTargetMsg.getText().toString();
                        mdata = encryptText(mdata);
                        Log.d("DATA","<<<<< mdata : "+mdata);
                        targetmsg.setText(mdata);

                    }
                    catch(Exception e)
                    {
                    }
                }
                // 복호화
                else
                {
                    try
                    {
                        mCryptoMode = true;
                        mButton.setText(CRYPTO_MODE_CRYPT);
                        decryptText();
//                        Log.d("DATA","<<<<< mdata : "+mdata);
                    }
                    catch(Exception e)
                    {
                    }
                }

                break;
        }
    }
    // 복호화
    private void decryptText() {
        try {
            String Decdata = decryptor.decryptData(SAMPLE_ALIAS, encryptor.getEncryption(), encryptor.getIv());
            targetmsg.setText(Decdata);
        } catch (UnrecoverableEntryException | NoSuchAlgorithmException |
                KeyStoreException | NoSuchPaddingException | NoSuchProviderException |
                IOException | InvalidKeyException e) {
            Log.d("", "decryptData() called with: " + e.getMessage(), e);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }

    // 암호화
    private String encryptText(String data) {
        String Encdata=null;
        try {
            final byte[] encryptedText = encryptor
                    .encryptText(SAMPLE_ALIAS, data);
            Encdata = Base64.encodeToString(encryptedText, Base64.DEFAULT);
//            targetmsg.setText(Encdata);
        } catch (UnrecoverableEntryException | NoSuchAlgorithmException | NoSuchProviderException |
                KeyStoreException | IOException | NoSuchPaddingException | InvalidKeyException e) {
            Log.d("", "onClick() called with: " + e.getMessage(), e);
        } catch (InvalidAlgorithmParameterException | SignatureException |
                IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return Encdata;
    }
}
