/*

Crea una classe Java anomenada Rot13.java que contingui les següents funcions:

xifraRot13( cadena ): Ha de substituir cada lletra (no els espais ni els signes de puntuació) per la lletra
que està 13 posicions més a la dreta en l’abecedari (si sobrepassa ha de tornar a començar).

desxifraRot13( cadena ): Ha de fer el procés invers de la funció anterior.

*/

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
        
        String[] userInput = {"Hola k tal :)","xd","L'altrè dià vàig veuré ún GOSET !!!!"};

        for (int i = 0; i < userInput.length; i++) {
            
            var inputEncoded = xifraRot13(userInput[i]);
            var inputDecoded = desxifraRot13(inputEncoded);

            System.out.println("Input Encoded: " + inputEncoded);
            System.out.println("Input Decoded: " + inputDecoded);
            System.out.println();

        }

        
    }

}
