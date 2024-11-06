package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class XifradorPolialfabetic {

    private static final char[] ABC = "AÁÀÄBCÇDEÉÈËFGHIÍÌÏJKLMNÑOÓÒÖPQRSTUÚÙÜVWXYZ".toCharArray();
    private static ArrayList<Character> permutedAlphabet;
    private static final int PASSWORD = 7344592;
    private static Random random;

    private void initRandom(int key) {
        random = new Random(key);
    }

    public void permutaAlfabet() {

        permutedAlphabet = new ArrayList<Character>();

        for (char c : ABC) {
            permutedAlphabet.add(c);
        }

        Collections.shuffle(permutedAlphabet,random);

    }

    private char mapLetter(char letter) {

        int position = 0;

        ArrayList<Character> permutedABC = new ArrayList<Character>();

        for (char c : permutedAlphabet.toString().toCharArray()) {
            if(Character.isAlphabetic(c)) {
                permutedABC.add(c);
            }
        }

        for (int i = 0; i < ABC.length; i++) {
            if(ABC[i] == letter) {
                position = i;
                break;
            }
        }

        return permutedABC.get(position);
    }

    private char mapEncriptedLetter(char letter) {

        int position = 0;

        ArrayList<Character> permutedABC = new ArrayList<Character>();

        for (char c : permutedAlphabet.toString().toCharArray()) {
            if(Character.isAlphabetic(c)) {
                permutedABC.add(c);
            }
        }

        for (int i = 0; i < ABC.length; i++) {
            
            if(permutedABC.get(i) == letter) {
                position = i;
                break;
            }

        }

        return ABC[position];
    }

    private String xifraPoliAlfa(String userLine) {

        StringBuilder encriptedLine = new StringBuilder();

        for (char letter : userLine.toCharArray()) {

            permutaAlfabet();
            
            if(!Character.isAlphabetic(letter)) {
                encriptedLine.append(letter);
                continue;
            }

            if(Character.isLowerCase(letter)) {
                letter = mapLetter(Character.toUpperCase(letter));
                encriptedLine.append(Character.toLowerCase(letter));
                continue;
            }

            letter = mapLetter(Character.toUpperCase(letter));
            encriptedLine.append(letter);
            
        }

        return encriptedLine.toString();

    }

    private String desxifraPoliAlfa(String userLine) {
        
        StringBuilder desencriptedLine = new StringBuilder();

        for (char c : ABC) {
            permutedAlphabet.add(c);
        }

        for (char letter : userLine.toCharArray()) {
            
            permutaAlfabet();

            if(!Character.isAlphabetic(letter)) {
                desencriptedLine.append(letter);
                continue;
            }

            if(Character.isLowerCase(letter)) {
                letter = mapEncriptedLetter(Character.toUpperCase(letter));
                desencriptedLine.append(Character.toLowerCase(letter));
                continue;
            }

                letter = mapEncriptedLetter(Character.toUpperCase(letter));
                desencriptedLine.append(letter);
            
        }

        return desencriptedLine.toString();
        
    }

}
