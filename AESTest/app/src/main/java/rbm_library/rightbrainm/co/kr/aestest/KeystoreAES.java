package rbm_library.rightbrainm.co.kr.aestest;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class KeystoreAES {

    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";

    private KeyStore keyStore;
    private byte[] encryption;
    private byte[] iv;

    private static  String MY_ALIAS = "PASSALIAS";

    public KeystoreAES() throws KeyStoreException, CertificateException,
            NoSuchAlgorithmException, IOException{
            keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
            keyStore.load(null);
    }


    public String decryptData(String encryptedText)
            throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException,
            NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IOException,
            BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        final GCMParameterSpec spec = new GCMParameterSpec(128, iv);
        SecretKey secretKey = ((KeyStore.SecretKeyEntry) keyStore.getEntry(MY_ALIAS, null)).getSecretKey();
        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec);
        byte[] data2 = Base64.decode(encryptedText,Base64.DEFAULT);
        return new String(cipher.doFinal(data2), "UTF-8");
    }

    public String encryptText(String textToEncrypt)
            throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException,
            NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IOException,
            InvalidAlgorithmParameterException, SignatureException, BadPaddingException,
            IllegalBlockSizeException {


        SecretKey secretKey = null;
        if(keyStore.containsAlias(MY_ALIAS) == false){
            KeyGenerator keyGenerator= KeyGenerator
                    .getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE);

            keyGenerator.init(new KeyGenParameterSpec.Builder(MY_ALIAS,
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .build());
            keyGenerator.generateKey();
        }

        secretKey = ((KeyStore.SecretKeyEntry) keyStore.getEntry(MY_ALIAS, null)).getSecretKey();

        if ( secretKey == null ) {
            return null;
        }

        final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        iv = cipher.getIV();
        encryption = cipher.doFinal(textToEncrypt.getBytes("UTF-8"));
        String EncStringData = Base64.encodeToString(encryption,Base64.DEFAULT);
        return EncStringData;
    }


}
