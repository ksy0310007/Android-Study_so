package renomedia.co.kr.aestests;

import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;
import android.util.Log;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
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
    private byte[] iv = null;

    private static String MY_ALIAS = "TestPASSALIAS";

    // 싱글톤으로 생성
    private static KeystoreAES instance = null;

    public static KeystoreAES getInstance() throws Exception {
        if ( instance == null ) {
            instance = new KeystoreAES();
        }
        return instance;
    }

    /*
     * 싱글톤을 위한 생성자.
     * 키스토어를 로딩한다.
     * @throws Exception
     */
    public KeystoreAES() throws KeyStoreException, CertificateException,
            NoSuchAlgorithmException, IOException {
        keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
        keyStore.load(null);

        iv = "c46dd68f90b3aea4f7de7cbca43c7d9df0af06471a53b70bf0be458e1205c121".getBytes("UTF-8");


    }
//    public static boolean isSigningKey(String alias) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
//            try {
//                KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
//                keyStore.load(null);
//                return keyStore.containsAlias(alias);
//            } catch (Exception e) {
//                Log.d("", e.getMessage(), e);
//                return false;
//            }
//        } else {
//            return false;
//        }
//    }
/*
 * 암호화된 문자열을 base64디코딩 한 후 복호화 한 원문 문자열을 반환한다.
 * @param encryptedText 암호화된 문자열
 * @return 복호화 된 문자열
 * @throws Exception
 */

    public String decryptData(String encryptedText)
            throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException,
            NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IOException,
            BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKey secretKey = ((KeyStore.SecretKeyEntry) keyStore.getEntry(MY_ALIAS, null)).getSecretKey();
//        if( iv == null){
//            iv = cipher.getIV();
//        }

        final GCMParameterSpec spec = new GCMParameterSpec(128, iv,0,12);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec);
        byte[] data2 = Base64.decode(encryptedText, Base64.DEFAULT);
        return new String(cipher.doFinal(data2), "UTF-8");

    }

    /*
     * 주어진 문자열을 암호화 한 다음 base64인코딩한 문자열을 반환
     * @param textToEncrypt 암호화할 문자열
     * @return 암호화 된 후 base64로 인코딩 된 문자열
     * @throws Exception 예외
     */

    public String encryptText(String textToEncrypt)
            throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException,
            NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IOException,
            InvalidAlgorithmParameterException, SignatureException, BadPaddingException,
            IllegalBlockSizeException {

        // 키스토어에 해당 alias가 존재 하는지 확인하고 없으면 생성

        if(keyStore.containsAlias(MY_ALIAS) == false){
            KeyGenerator keyGenerator= KeyGenerator
                    .getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE);

                keyGenerator.init(new KeyGenParameterSpec.Builder(MY_ALIAS,
                        KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                        .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                        .setRandomizedEncryptionRequired(false)
                        .build());

            keyGenerator.generateKey();
        }

        // 키를 가져온다.
        Key secretKey = keyStore.getKey(MY_ALIAS, null);
        if ( secretKey == null ) {
            return null;
        }

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        final GCMParameterSpec spec = new GCMParameterSpec(128, iv,0,12);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey,spec);

        encryption = cipher.doFinal(textToEncrypt.getBytes("UTF-8"));
        String EncStringData = Base64.encodeToString(encryption, Base64.DEFAULT);
        return EncStringData;

    }

}

