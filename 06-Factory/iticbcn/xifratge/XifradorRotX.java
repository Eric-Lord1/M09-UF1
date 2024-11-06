package iticbcn.xifratge;

public class XifradorRotX {

    private static final char ABC[] = {'A', 'À', 'Á', 'B', 'C', 'Ç', 'D', 'E', 'È' , 'É' , 'F' , 'G', 'H', 'I', 'Í' , 'J', 'K', 'L', 'M', 'N', 'O', 'Ò', 'Ó' ,'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'V' , 'W', 'X', 'Y', 'Z'};
    private static final char abc[] = {'a', 'à', 'á', 'b', 'c', 'ç', 'd', 'e', 'è', 'é', 'f', 'g', 'h', 'i', 'í' ,'j', 'k', 'l', 'm', 'n' , 'o' ,'ò' ,'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú','v', 'w', 'x', 'y', 'z'};
    private static final int MATRIX_LENGTH = ABC.length;

    private String xifraRotX(String cadena, int key) { 

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

    private String desxifraRotX(String cadena, int key) {

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

    public void forcaBruta(String xifratInput) {

        for (int i = 0; i < ABC.length; i++) {
            String x = desxifraRotX(xifratInput, i);
            System.out.println(x);
        }

    }

    
}
