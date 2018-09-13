package renomedia.co.kr.aestests;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class MainActivity extends AppCompatActivity {

    Button button;
    private static KeystoreAES keystoreAES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            keystoreAES = new KeystoreAES();
        } catch (Exception e) {
            e.printStackTrace();
        }
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AESTests();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (UnrecoverableEntryException e) {
                    e.printStackTrace();
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (NoSuchProviderException e) {
                    e.printStackTrace();
                } catch (SignatureException e) {
                    e.printStackTrace();
                } catch (KeyStoreException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void AESTests () throws IOException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnrecoverableEntryException, InvalidAlgorithmParameterException, NoSuchPaddingException, NoSuchProviderException, SignatureException, KeyStoreException, IllegalBlockSizeException {
        String mydata1 = "sososososo";
        String mydata2 = "youngyoung";

        String endata1 = keystoreAES.encryptText(mydata1);
        String endata2 = keystoreAES.encryptText(mydata2);
        String dedata1 = keystoreAES.decryptData(endata1);
        String dedata2 = keystoreAES.decryptData(endata2);

        Log.d("DATA>>>>","mydata1"+mydata1);
        Log.d("DATA>>>>","mydata1"+mydata2);
        Log.d("DATA>>>>","mydata1"+endata1);
        Log.d("DATA>>>>","mydata1"+endata2);
        Log.d("DATA>>>>","mydata1"+dedata1);
        Log.d("DATA>>>>","mydata1"+dedata2);

    }
}
