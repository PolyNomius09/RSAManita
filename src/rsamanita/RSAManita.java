/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsamanita;

/**
 *
 * @author gaemil
 */

import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class RSAManita {

    public int tamPrimo;
    public BigInteger n, p, q, fi, e, d;
    
    public RSAManita(int tamPrimo) {
        this.tamPrimo = tamPrimo;
    }
    
    
    public void generarPrimos() {
        this.p = new BigInteger(this.tamPrimo, 10, new Random());
        do this.q = new BigInteger(this.tamPrimo, 10, new Random());
        while(this.q.compareTo(this.p) == 0);
    }
    
    public void generarClaves() {
        this.n = this.p.multiply(this.q);
        this.fi = this.p.subtract(BigInteger.valueOf(1));
        this.fi = this.fi.multiply(this.q.subtract(BigInteger.valueOf(1)));
        
        do this.e = new BigInteger(2*this.tamPrimo, new Random());
        while((this.e.compareTo(this.fi) != -1) || (this.e.gcd(this.fi).compareTo(BigInteger.valueOf(1)) != 0));
        
        this.d = this.e.modInverse(this.fi);
    }
    
    public BigInteger[] cifrar(String mensaje) {
        int i;
        byte temp[] = new byte[1];
        byte digitos [] = mensaje.getBytes();
        BigInteger bigDigitos[] = new BigInteger[digitos.length];
        
        for(i = 0; i < bigDigitos.length; i++){
            temp[0] = digitos[i];
            bigDigitos[i] = new BigInteger(temp);
        }
        
        BigInteger cifrado[] = new BigInteger[bigDigitos.length];
        
        for(i=0;i<bigDigitos.length; i++){
            cifrado[i] = bigDigitos[i].modPow(this.e, this.n);
        }
        
        return cifrado;
    }
    
    public String descifrar(BigInteger[] cifrado) {
        BigInteger descifrado[] = new BigInteger[cifrado.length];
        for(int i=0;i<descifrado.length;i++){   
            descifrado[i] = cifrado[i].modPow(this.d, this.n);
        }
        char charArray[] = new char[descifrado.length];
        for(int i=0;i<charArray.length;i++){
            charArray[i] = (char)(descifrado[i].intValue());
        }
        return (new String(charArray));
    }
}
