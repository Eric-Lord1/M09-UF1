import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    private int npass;

    public String getSHA512AmbSalt(String pw, String salt) {

        pw = pw.trim();

        // Inicialitzem un string null que recullira la pasword encriptada
        String passwordEncripted = null;

        try {

            // MessageDigest genera valors HASH a partir de strings
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // Converteix la sal a bytes i actualitza el estat del MessageDigest amb aquests
            // bytes
            md.update(salt.getBytes(StandardCharsets.UTF_8));

            // Converteix la password a bytes i actualitza el estat del MessageDigest amb
            // aquests bytes més els anteriors
            md.update(pw.getBytes(StandardCharsets.UTF_8));

            // Digest genera el valor hash a partir dels bytes donats
            byte[] bytes = md.digest();

            // Transformem el vector de bytes en un String en hexadecimal
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            passwordEncripted = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        npass++;
        return passwordEncripted;

    }

    public String getPBKDF2AmbSalt(String pw, String salt) {
        try {

            // Convertir el paswd i el salt a byte[]
            char[] password = pw.toCharArray();
            byte[] saltBytes = salt.getBytes("UTF-8");

            // Nombre de iteracions (més iteracions => més segur)
            int iterations = 65536;

            // Longitud del hash resultant
            int keyLength = 512; // En bits

            // Utilitzem PBKDF2 amb el salt i la contraseña
            PBEKeySpec spec = new PBEKeySpec(password, saltBytes, iterations, keyLength);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] hash = skf.generateSecret(spec).getEncoded();

            // Convertir el hash a una representación hexadecimal para devolverlo como
            // String
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }

            // Retornar el hash como un String hexadecimal
            npass++;
            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException("Error al generar el hash PBKDF2", e);
        }
    }

    public String getInterval(long t1, long t2) {

        return String.valueOf(t2 - t1);

    }

    public String forcaBruta(String alg, String hash, String salt) {

        npass = 0;

        char[] charSet = "abcdefABCDEF1234567890!".toCharArray();

        char[] hackPasw = new char[6];

        if (alg.equals("SHA-512")) {

            for (int i = 0; i < 23; i++) {
                hackPasw[0] = charSet[i];
                if (getSHA512AmbSalt(String.copyValueOf(hackPasw), salt).equals(hash))
                    return String.copyValueOf(hackPasw);
                for (int j = 0; j < 23; j++) {
                    hackPasw[1] = charSet[j];
                    if (getSHA512AmbSalt(String.copyValueOf(hackPasw), salt).equals(hash))
                        return String.copyValueOf(hackPasw);
                    for (int k = 0; k < 23; k++) {
                        hackPasw[2] = charSet[k];
                        if (getSHA512AmbSalt(String.copyValueOf(hackPasw), salt).equals(hash))
                            return String.copyValueOf(hackPasw);
                        for (int m = 0; m < 23; m++) {
                            hackPasw[3] = charSet[m];
                            if (getSHA512AmbSalt(String.copyValueOf(hackPasw), salt).equals(hash))
                                return String.copyValueOf(hackPasw);
                            for (int n = 0; n < 23; n++) {
                                hackPasw[4] = charSet[n];
                                if (getSHA512AmbSalt(String.copyValueOf(hackPasw), salt).equals(hash))
                                    return String.copyValueOf(hackPasw);
                                for (int o = 0; o < 23; o++) {
                                    hackPasw[5] = charSet[o];
                                    if (getSHA512AmbSalt(String.copyValueOf(hackPasw), salt).equals(hash))
                                        return String.copyValueOf(hackPasw);
                                    hackPasw[5] = ' ';
                                }
                                hackPasw[4] = ' ';
                            }
                            hackPasw[3] = ' ';
                        }
                        hackPasw[2] = ' ';
                    }
                    hackPasw[1] = ' ';
                }
            }

        } else if (alg.equals("PBKDF2")) {

            for (int i = 0; i < 23; i++) {
                hackPasw[0] = charSet[i];
                if (getPBKDF2AmbSalt(String.copyValueOf(hackPasw), salt).equals(hash))
                    return String.copyValueOf(hackPasw);
                for (int j = 0; j < 23; j++) {
                    hackPasw[1] = charSet[j];
                    if (getPBKDF2AmbSalt(String.copyValueOf(hackPasw), salt).equals(hash))
                        return String.copyValueOf(hackPasw);
                    for (int k = 0; k < 23; k++) {
                        hackPasw[2] = charSet[k];
                        if (getPBKDF2AmbSalt(String.copyValueOf(hackPasw), salt).equals(hash))
                            return String.copyValueOf(hackPasw);
                        for (int m = 0; m < 23; m++) {
                            hackPasw[3] = charSet[m];
                            if (getPBKDF2AmbSalt(String.copyValueOf(hackPasw), salt).equals(hash))
                                return String.copyValueOf(hackPasw);
                            for (int n = 0; n < 23; n++) {
                                hackPasw[4] = charSet[n];
                                if (getPBKDF2AmbSalt(String.copyValueOf(hackPasw), salt).equals(hash))
                                    return String.copyValueOf(hackPasw);
                                for (int o = 0; o < 23; o++) {
                                    hackPasw[5] = charSet[o];
                                    if (getPBKDF2AmbSalt(String.copyValueOf(hackPasw), salt).equals(hash))
                                        return String.copyValueOf(hackPasw);
                                    hackPasw[5] = ' ';
                                }
                                hackPasw[4] = ' ';
                            }
                            hackPasw[3] = ' ';
                        }
                        hackPasw[2] = ' ';
                    }
                    hackPasw[1] = ' ';
                }
            }

        } else {
            return "Algoritme no implementat";
        }

        

        return "Password no trobat";

    }

    public static void main(String[] args) throws Exception {

        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();

        String[] aHashes = { h.getSHA512AmbSalt(pw, salt), h.getPBKDF2AmbSalt(pw, salt) };

        String pwTrobat = null;
        String[] algorismes = { "SHA-512", "PBKDF2" };

        for (int i = 0; i < aHashes.length; i++) {
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }
}
