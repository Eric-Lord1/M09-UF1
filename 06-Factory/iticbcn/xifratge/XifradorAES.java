package iticbcn.xifratge;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "narutoUzumak1";

    public byte[] xifraAES(String msg, String clau) throws Exception {

        //Obtenir els bytes de l’String
        byte[] msgValueBytes = msg.getBytes();

        // Genera IvParameterSpec
        SecureRandom scRandom = new SecureRandom();
        scRandom.nextBytes(iv);
        IvParameterSpec ivParam = new IvParameterSpec(iv);

        // Genera hash
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(CLAU.getBytes("UTF-8"));
        byte[] keyBytes = new byte[MIDA_IV];
        System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ALGORISME_XIFRAT);

        // Encrypt.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParam);
        byte[] encrypted = cipher.doFinal(msgValueBytes);

        // Combinar IV i part xifrada.
        byte[] encryptedIVAndText = new byte[MIDA_IV + encrypted.length];
        System.arraycopy(iv, 0, encryptedIVAndText, 0, MIDA_IV);
        System.arraycopy(encrypted, 0, encryptedIVAndText, MIDA_IV, encrypted.length);

        // return iv+msgxifrat
        return encryptedIVAndText;
    }

    public String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {

        // Extreure l'IV.
        String encrypted = Base64.getEncoder().encodeToString(bIvIMsgXifrat);
        byte[] encryptedIvTextBytes = Base64.getDecoder().decode(encrypted);

        // Extreure la part xifrada.        
        System.arraycopy(encryptedIvTextBytes, 0, iv, 0, iv.length);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        int encryptedSize = encryptedIvTextBytes.length - MIDA_IV;
        byte[] encryptedBytes = new byte[encryptedSize];
        System.arraycopy(encryptedIvTextBytes, MIDA_IV, encryptedBytes, 0, encryptedSize);
        
        // Fer hash de la clau
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(CLAU.getBytes("UTF-8"));
        byte[] keyBytes = new byte[MIDA_IV];
        System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ALGORISME_XIFRAT);
    
        // Desxifrar.
        Cipher cipherDecrypt = Cipher.getInstance(FORMAT_AES);
        cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] decrypted = cipherDecrypt.doFinal(encryptedBytes);

        // return String desxifrat    
        return new String(decrypted);

    }

    
}
