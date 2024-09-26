
/*
 * 
 
xifraRotX( cadena, desplaçament ): Ha de substituir cada lletra (no els espais ni els signes de
puntuació) per la lletra que està "desplaçament" posicions més a la dreta en l’abecedari (si sobrepassa ha
de tornar a començar).

desxifraRotX( cadena, desplaçament ): Ha de fer el procés invers de la funció anterior.

forcaBrutaRotX( cadenaXifrada ): Ha de provar tots els desplaçaments possibles i mostrar el missatge
resultant de desxifrar amb desplaçaments de 1,2,3,... fins la longitud de l’abecedari.

 * 
 */

public class RotX {

    private static final char ABC[] = {'A', 'À', 'Á', 'B', 'C', 'Ç', 'D', 'E', 'È' , 'É' , 'F' , 'G', 'H', 'I', 'Í' , 'J', 'K', 'L', 'M', 'N', 'O', 'Ò', 'Ó' ,'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'V' , 'W', 'X', 'Y', 'Z'};
    private static final char abc[] = {'a', 'à', 'á', 'b', 'c', 'ç', 'd', 'e', 'è', 'é', 'f', 'g', 'h', 'i', 'í' ,'j', 'k', 'l', 'm', 'n' , 'o' ,'ò' ,'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú','v', 'w', 'x', 'y', 'z'};
    private static final int MATRIX_LENGTH = ABC.length;

    private static String xifraRotX(String cadena, int key) { 

        StringBuffer encodedInput = new StringBuffer();

        for (int i = 0; i < cadena.length(); i++) {
            for (int j = 0; j < ABC.length; j++) {
                
                if (!Character.isLetter(cadena.charAt(i))) {
                    encodedInput.append(cadena.charAt(i));
                    break;   
                }

                if (ABC[j] == cadena.charAt(i)) {
                    encodedInput.append(ABC[((j + key) % MATRIX_LENGTH)]);
                    break;
                }

                if (abc[j] == cadena.charAt(i)) {
                    encodedInput.append(abc[(j + key) % MATRIX_LENGTH]);
                    break;
                }
            }
        }

        return encodedInput.toString();

    }

    private static String desxifraRotX(String cadena, int key) {

        StringBuffer decodedString = new StringBuffer();  

        for (int i = 0; i < cadena.length(); i++) {
            for (int j = 0; j < ABC.length; j++) {
                
                if (!Character.isLetter(cadena.charAt(i))) {
                    decodedString.append(cadena.charAt(i));
                    break;   
                }

                if (ABC[j] == cadena.charAt(i)) {
                    int position = (j - (key%MATRIX_LENGTH));
                    if (position < 0) position =  position + MATRIX_LENGTH;
                    decodedString.append(ABC[position]);
                    break;
                }

                if (abc[j] == cadena.charAt(i)) {
                    int position = (j - (key%MATRIX_LENGTH));
                    if (position < 0) position =  position + MATRIX_LENGTH;
                    decodedString.append(abc[position]);
                    break;
                }
            }
        }

        return decodedString.toString();
    }

    
    public static void main(String[] args) {
        
        String[] userInput = {"Hola k tal :)","xd","L'altrè dià vàig veuré ún GOSET !!!!"};
        int[] keys = {13,100,55};

        for (int i = 0; i < userInput.length; i++) {
            
            var inputEncoded = xifraRotX(userInput[i],keys[i]);
            var inputDecoded = desxifraRotX(inputEncoded, keys[i]);

            System.out.println("Input Encoded: " + inputEncoded);
            System.out.println("Input Decoded: " + inputDecoded);
            System.out.println();

        }

        
    }

    
}
