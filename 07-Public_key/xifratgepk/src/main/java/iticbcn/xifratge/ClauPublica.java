package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAKeyGenParameterSpec;

import javax.crypto.Cipher;

public class ClauPublica {

    public KeyPair generaParellClausRSA() throws Exception {

        // Objecte per genera claus RSA
        KeyPairGenerator generadorClaus = KeyPairGenerator.getInstance("RSA");
        
        // Inicialitzem el generador de claus amb una mida de 2048 bits i un exponent f4
        generadorClaus.initialize(new RSAKeyGenParameterSpec(2048, RSAKeyGenParameterSpec.F4));

        // Retornem el parell de claus la publica i la privada
        return generadorClaus.generateKeyPair();

    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica)throws Exception {

        // Instanciem el xifrador en RSA
        Cipher xifrador = Cipher.getInstance("RSA");

        // El inicialitzem en mode encriptacio amb la clau publica donada
        xifrador.init(Cipher.ENCRYPT_MODE, clauPublica);

        // Retornem el missatge encriptat en un vector de bytes
        return xifrador.doFinal(msg.getBytes("UTF-8"));
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception{

        // Instanciem el xifrador en RSA
        Cipher xifrador = Cipher.getInstance("RSA");

        // El inicialitzem en mode desencriptacio amb la clau privada generada
        xifrador.init(Cipher.DECRYPT_MODE, clauPrivada);

        // Desencriptem el missatge y el guardem en format de bytes
        byte[] bytesDesxifrats = xifrador.doFinal(msgXifrat);

        // retornem el valor en un string
        return new String(bytesDesxifrats, "UTF-8");
    }
    
}
