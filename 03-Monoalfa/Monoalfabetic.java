/*
 * 
 * 
 
Crea un programa en Java anomenat Monoalfabetic.java que tingui un mètode
permutaAlfabet(alfabet), que generi una permutació de l’alfabet complet amb accents greus, aguts,
dièresi, «ç» i «ñ» i la retorni en un array de char.

Després crea els mètodes:

xifraMonoAlfa(cadena) que xifre la cadena passada com a paràmetre amb xifratge monoalfabètic
utilitzant la permutació generada inicialment

desxifraMonoAlfa(cadena) que desxifre la cadena del paràmetre i torni una cadena dexifrada amb
monoalfabètic.

Nota: S’han de respectar les majúscules i les minúscules originals.

* 
 * 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class Monoalfabetic {

    private static final char ABC[] = {'A', 'À', 'Á', 'B', 'C', 'Ç', 'D', 'E', 'È' , 'É' , 'F' , 'G', 'H', 'I', 'Í' , 'J', 'K', 'L', 'M', 'N', 'O', 'Ò', 'Ó' ,'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'V' , 'W', 'X', 'Y', 'Z'};
    private static char PABC[]; 
    private static LinkedHashMap<Character, Character> mapKV;
    private static LinkedHashMap<Character, Character> mapVK;


    private static LinkedHashMap<Character, Character> createMapKV() {

        LinkedHashMap<Character, Character> x = new LinkedHashMap<Character, Character>();

        for (int i = 0; i < ABC.length; i++) {
            x.put(ABC[i], PABC[i]);
        }

        return x;

    }

    private static LinkedHashMap<Character, Character> createMapVK() {

        LinkedHashMap<Character, Character> x = new LinkedHashMap<Character, Character>();

        for (int i = 0; i < ABC.length; i++) {
            x.put(PABC[i],ABC[i]);
        }

        return x;

    }

    private static char[] permutaAlfabet() {

        ArrayList<Character> alphabet = new ArrayList<>();
        for (char c : ABC) {
            alphabet.add(c);
        }

        Collections.shuffle(alphabet);
        
        StringBuilder x = new StringBuilder();

        for (Character c : alphabet) {
            if(Character.isAlphabetic(c)) x.append(c);
        }

        return x.toString().toCharArray();

    }

    private static StringBuilder xifraMonoAlfa(String inputDesxifrat) {

        StringBuilder inputXifrat = new StringBuilder();

        for (int i = 0; i < inputDesxifrat.length(); i++) {
            
            if(!Character.isAlphabetic(inputDesxifrat.charAt(i))) {
                inputXifrat.append(inputDesxifrat.charAt(i));
                continue;
            }    
            
            if(Character.isLowerCase(inputDesxifrat.charAt(i))){
                inputXifrat.append(Character.toLowerCase(mapKV.get(Character.toUpperCase(inputDesxifrat.charAt(i)))));
                continue;
            }

            inputXifrat.append(mapKV.get(inputDesxifrat.charAt(i)));
        }

        return inputXifrat;

    }

    private static StringBuilder desxifraMonoAlfa(String inputXifrat) {

        StringBuilder inputDesxifrat = new StringBuilder();

        for (int i = 0; i < inputXifrat.length(); i++) {

            if(!Character.isAlphabetic(inputXifrat.charAt(i))) {
                inputDesxifrat.append(inputXifrat.charAt(i));
                continue;
            }    
            
            if(Character.isLowerCase(inputXifrat.charAt(i))){
                inputDesxifrat.append(Character.toLowerCase(mapVK.get(Character.toUpperCase(inputXifrat.charAt(i)))));
                continue;
            }

            inputDesxifrat.append(mapVK.get(inputXifrat.charAt(i)));
        }

        return inputDesxifrat;

    }

    public static void main(String[] args) {
        
        PABC = permutaAlfabet(); 
        mapKV = createMapKV();
        mapVK = createMapVK();

        String input = "MANOLO? cóm estàs AvUi !! :) ;(";
        StringBuilder inputXifrat = xifraMonoAlfa(input);
        StringBuilder inputDesXifrat = desxifraMonoAlfa(inputXifrat.toString());

        System.out.println("El teu input és: " + input);
        System.out.println("El teu input xifrat és: " + inputXifrat.toString());
        System.out.println("El teu input desxifrat és: " + inputDesXifrat.toString());

    }
    
}
