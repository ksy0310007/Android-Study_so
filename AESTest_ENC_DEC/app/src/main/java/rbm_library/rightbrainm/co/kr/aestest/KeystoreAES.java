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
    public static class DeCryptor {

        private static final String TRANSFORMATION = "AES/PASS/NoPadding";
        private static final String ANDROID_KEY_STORE = "PASSAndroidKeyStore";

        private KeyStore keyStore;

        DeCryptor() throws CertificateException, NoSuchAlgorithmException, KeyStoreException,
                IOException {
            initKeyStore();
        }

        private void initKeyStore() throws KeyStoreException, CertificateException,
                NoSuchAlgorithmException, IOException {
            keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
            keyStore.load(null);
        }

        String decryptData(final String alias, final byte[] encryptedData, final byte[] encryptionIv)
                throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException,
                NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IOException,
                BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

            final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            final GCMParameterSpec spec = new GCMParameterSpec(128, encryptionIv);
            cipher.init(Cipher.DECRYPT_MODE, getDecSecretKey(alias), spec);

            return new String(cipher.doFinal(encryptedData), "UTF-8");
        }

        private SecretKey getDecSecretKey(final String alias) throws NoSuchAlgorithmException,
                UnrecoverableEntryException, KeyStoreException {
            return ((KeyStore.SecretKeyEntry) keyStore.getEntry(alias, null)).getSecretKey();
        }
    }
    static class EnCryptor {

        private static final String TRANSFORMATION = "AES/PASS/NoPadding";
        private static final String ANDROID_KEY_STORE = "PASSAndroidKeyStore";

        private byte[] encryption;
        private byte[] iv;

        EnCryptor() {
        }

        byte[] encryptText(final String alias, final String textToEncrypt)
                throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException,
                NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IOException,
                InvalidAlgorithmParameterException, SignatureException, BadPaddingException,
                IllegalBlockSizeException {

            final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, getEncSecretKey(alias));

            iv = cipher.getIV();

            return (encryption = cipher.doFinal(textToEncrypt.getBytes("UTF-8")));
        }

        @NonNull
        private SecretKey getEncSecretKey(final String alias) throws NoSuchAlgorithmException,
                NoSuchProviderException, InvalidAlgorithmParameterException {

            final KeyGenerator keyGenerator = KeyGenerator
                    .getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE);

            keyGenerator.init(new KeyGenParameterSpec.Builder(alias,
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .build());

            return keyGenerator.generateKey();
        }

        byte[] getEncryption() {
            return encryption;
        }

        byte[] getIv() {
            return iv;
        }
    }
}
