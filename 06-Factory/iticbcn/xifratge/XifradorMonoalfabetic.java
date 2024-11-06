package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class XifradorMonoalfabetic implements Xifrador {

    private static final char ABC[] = {'A', 'À', 'Á', 'B', 'C', 'Ç', 'D', 'E', 'È' , 'É' , 'F' , 'G', 'H', 'I', 'Í' , 'J', 'K', 'L', 'M', 'N', 'O', 'Ò', 'Ó' ,'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'V' , 'W', 'X', 'Y', 'Z'};
    private static char PABC[]; 
    private static LinkedHashMap<Character, Character> mapKV;
    private static LinkedHashMap<Character, Character> mapVK;

    public XifradorMonoalfabetic() {
        this.PABC = permutaAlfabet();
        this.mapKV = createMapKV();
        this.mapVK = createMapVK();
    }

    private LinkedHashMap<Character, Character> createMapKV() {

        LinkedHashMap<Character, Character> x = new LinkedHashMap<Character, Character>();

        for (int i = 0; i < ABC.length; i++) {
            x.put(ABC[i], PABC[i]);
        }

        return x;

    }

    private LinkedHashMap<Character, Character> createMapVK() {

        LinkedHashMap<Character, Character> x = new LinkedHashMap<Character, Character>();

        for (int i = 0; i < ABC.length; i++) {
            x.put(PABC[i],ABC[i]);
        }

        return x;

    }

    private char[] permutaAlfabet() {

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

    private StringBuilder xifraMonoAlfa(String inputDesxifrat) {

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

    private StringBuilder desxifraMonoAlfa(String inputXifrat) {

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

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        
        StringBuilder textXifrat = xifraMonoAlfa(msg);
        return new TextXifrat(textXifrat.toString().getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        
        StringBuilder textDesxifrat = desxifraMonoAlfa(new String(xifrat.getBytes()));
        return textDesxifrat.toString();
    }

}
