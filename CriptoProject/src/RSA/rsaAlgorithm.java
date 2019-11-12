/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RSA;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class rsaAlgorithm {

    /**
     * @return the publicKey
     */
   
    private String publicKey;
    private String privateKey;
    private int p = 0, q = 0, n= 0, phi = 0, e=0, d=0, k=2;
    
   /* public static void main(String[] args){
        rsaAlgorithm sa = new rsaAlgorithm();
        String ci = sa.Encriptar("a", "11 17");
        System.out.println(ci);
        //sa.Desencriptar(ci);
    }*/
    
    public rsaAlgorithm(){
        createValues();
    }
    
    static int mcd(int a, int b){
          if(b==0) return a;
          else return mcd(b, a%b);
      }

    static boolean isPrime(int a){
          for(int i=2; i < a; i++){
              if(a%i==0) return false;
          }
          return true;
      }

    int getD(int e, int phi){
          //d = (1 + k * fi)/e
          //e d mod phi = 1

          int result=0;
          int k = 1;
          while(true){
              result = (1+k*phi)/e;
              if((e*result)%phi ==1){
                  return result;
              }else{
                  k++;
              } 
          }
      }  
    
    public void createValues(){
        ArrayList<Integer> primes = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Valor de p:");
            p = sc.nextInt();
            System.out.println("\nValor de q:");
            q = sc.nextInt();
        }while(p == q || !isPrime(p) || !isPrime(q));
               
        n = p*q;
        phi = (p-1)*(q-1);//find the Totient
        
 
        for(int i=0; i < phi; i++){//sacar valor de e
            e = i;
            if(isPrime(i) && mcd(i,phi)==1 && (phi > e && e > 1) && (phi%i!=0)){
                primes.add(i);//lista de número primos
            }
        }
        
         
        e = primes.get(0);  //8207  
        d = getD(e,phi);
        
        privateKey = n + " " + d;
        publicKey = n + " " + e;
        
        System.out.println("p: " + p + " phi: " + phi + " e: " + e);
        System.out.println(" q: " + q + " n: " + n + " d:" + d + "\n");
    }
    
    /*public ArrayList<BigInteger> Encriptar(String texto){
        System.out.println("p: " + p + " phi: " + phi + " e: " + e);
        System.out.println(" q: " + q + " n: " + n + " d:" + d + "\n");
        
        byte[] b = texto.getBytes(StandardCharsets.US_ASCII);    
        
        BigInteger result, var, phii = BigInteger.valueOf(n);
        ArrayList<BigInteger> cipherText = new ArrayList<>();
   
        for(int i =0; i < b.length ;i++){
            var = BigInteger.valueOf(b[i]);
            result = var.pow(e);
            var = result.mod(phii);
            cipherText.add(var);
            //System.out.println("Original: " + b[i] + "Encriptado: " +  cipherText.get(i));
        }
        return cipherText;
    }*/
    
    public String Encriptar(String texto, String llaveUser){
        
        String[] llave = llaveUser.split(" ");
        
        //System.out.println("p: " + p + " phi: " + phi + "phi2: " + Integer.parseInt(llave[0]) + " e: " + e);
        //System.out.println("e3: " + Integer.parseInt(llave[1]) + " q: " + q + " n: " + n + " d:" + d + "\n");       
        
        byte[] b = texto.getBytes(StandardCharsets.US_ASCII);    
        
        BigInteger result, var, phii = BigInteger.valueOf(Integer.parseInt(llave[0]));
        String cipherText = "";
   
        for(int i =0; i < b.length ;i++){
            var = BigInteger.valueOf(b[i]);
            result = var.pow(Integer.parseInt(llave[1]));
            var = result.mod(phii);
            cipherText += var + " ";
            //System.out.println("Original: " + b[i] + "Encriptado: " +  cipherText.get(i));
        }
        return cipherText;
    }
    
    public String Desencriptar(String cipherText){
        String[] cipher = cipherText.split(" ");
        ArrayList<BigInteger> desText = new ArrayList<>();
        ArrayList<BigInteger> cipherArray = new ArrayList<>();
        BigInteger result, var, phii = BigInteger.valueOf(n);
        char a = '\0';
        String texto = "";
        
        for(int i=0; i < cipher.length; i++){
            cipherArray.add(BigInteger.valueOf(Integer.parseInt(cipher[i])));
        }
        
        for(int i =0; i < cipherArray.size(); i++){
            result = cipherArray.get(i).pow(d);
            var = result.mod(phii);
            desText.add(var);
            //System.out.println("Original: " + cipherText.get(i) + "Desencriptado: " +  desText.get(i));
        }
       
        for(int i=0; i < desText.size(); i++){
            a = (char)desText.get(i).intValueExact();
            texto+= a;
        }//final del for
        //System.out.println("Texto= " + texto);
        return texto;
    }

    /**
     * @return the publicKey
     */
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * @return the privateKey
     */
    public String getPrivateKey() {
        return privateKey;
    }
}

