
package criptoproject;
import RSA.rsaAlgorithm;
import java.math.BigInteger;
import java.util.ArrayList;

public class CriptoProject {

    public static void main(String[] args) {
        ArrayList<BigInteger> cipherText = new ArrayList<>();
        rsaAlgorithm rsa = new rsaAlgorithm();
        rsa.Encriptar("Hola", "11 17");
        System.out.println("Imprime:\n");
        System.out.println(cipherText);
    }
    
}
