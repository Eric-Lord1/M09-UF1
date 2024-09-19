/*
 * Enunciat

Crea una classe Java anomenada Rot13.java que contingui les següents funcions:

xifraRot13( cadena ): Ha de substituir cada lletra (no els espais ni els signes de puntuació) per la lletra
que està 13 posicions més a la dreta en l’abecedari (si sobrepassa ha de tornar a començar).

desxifraRot13( cadena ): Ha de fer el procés invers de la funció anterior.

Crea també un main per fer algunes proves.

Recomanacions

Crea un 2 array a nivell de classe
    Un amb les lletres de l’abecedari minúscules en ordre i
    Un altre amb les lletres en majúscula en ordre alfabètic
Feu-ho per simplificar el procés de desplaçament d’una lletra per un altra.
 */

import java.util.Scanner;

public class Rot13 {

    private static final char ABC[] = {'A', 'À', 'Á', 'B', 'C', 'Ç', 'D', 'E', 'È' , 'É' , 'F' , 'G', 'H', 'I', 'Í' , 'J', 'K', 'L', 'M', 'N', 'O', 'Ò', 'Ó' ,'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'V' , 'W', 'X', 'Y', 'Z'};
    private static final char abc[] = {'a', 'à', 'á', 'b', 'c', 'ç', 'd', 'e', 'è', 'é', 'f', 'g', 'h', 'i', 'í' ,'j', 'k', 'l', 'm', 'n' , 'o' ,'ò' ,'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú','v', 'w', 'x', 'y', 'z'};
    private static final byte KEY = 13;
    private static final int MATRIX_LENGTH = ABC.length;

    private static String xifraRot13(String cadena) {

        String encodedString = "";  

        for (int i = 0; i < cadena.length(); i++) {
            for (int j = 0; j < ABC.length; j++) {
                
                if (!Character.isLetter(cadena.charAt(i))) {
                    encodedString += cadena.charAt(i);
                    break;   
                }

                if (ABC[j] == cadena.charAt(i)) {
                    encodedString += ABC[((j + KEY) % MATRIX_LENGTH)];
                    break;
                }
                if (abc[j] == cadena.charAt(i)) {
                    encodedString += abc[(j + KEY) % MATRIX_LENGTH];
                    break;
                }
            }
        }

        return encodedString;

    }

    private static String desxifraRot13(String cadena) {

        String decodedString = "";  

        for (int i = 0; i < cadena.length(); i++) {

            for (int j = 0; j < ABC.length; j++) {
                
                if (!Character.isLetter(cadena.charAt(i))) {
                    decodedString += cadena.charAt(i);
                    break;   
                }

                if (ABC[j] == cadena.charAt(i)) {
                    int position = (j - KEY);
                    if (position < 0) position =  position + MATRIX_LENGTH;
                    decodedString += ABC[position];
                    break;
                }

                if (abc[j] == cadena.charAt(i)) {
                    int position = (j - KEY);
                    if (position < 0) position =  position + MATRIX_LENGTH;
                    decodedString += abc[position];
                    break;
                }
            }
        }

        return decodedString;
    }

    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        var x = sc.nextLine();

        var y = xifraRot13(x);

        System.out.println(y);
        System.out.println(desxifraRot13(y));


    }

}