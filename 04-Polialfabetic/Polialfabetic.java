
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Polialfabetic {

    private static final char[] ABC = "AÁÀÄBCÇDEÉÈËFGHIÍÌÏJKLMNÑOÓÒÖPQRSTUÚÙÜVWXYZ".toCharArray();
    private static ArrayList<Character> permutedAlphabet;
    private static final int PASSWORD = 7344592;

    private static Random random;

    private static void initRandom(int key) {
        random = new Random(key);
    }

    public static void permutaAlfabet() {

        permutedAlphabet = new ArrayList<Character>();

        for (char c : ABC) {
            permutedAlphabet.add(c);
        }

        Collections.shuffle(permutedAlphabet,random);

    }

    private static char mapLetter(char letter) {

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

    private static char mapEncriptedLetter(char letter) {

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

    private static String xifraPoliAlfa(String userLine) {

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

    private static String desxifraPoliAlfa(String userLine) {
        
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

    public static void main(String[] args) {

        String msgs[] = {"Test 01 àrbritre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"};

        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n--------");

        for (int i = 0; i < msgs.length; i++) {
            initRandom(PASSWORD);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n-----------");

        for (int i = 0; i < msgs.length; i++) {
            initRandom(PASSWORD);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }

    }

}
