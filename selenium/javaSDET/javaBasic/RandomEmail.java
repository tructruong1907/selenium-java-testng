package javaSDET.javaBasic;

import java.util.Random;

public class RandomEmail {
    static void main(){
        Random rand = new Random();
        System.out.println(rand.nextBoolean());
        System.out.println(rand.nextDouble());
        System.out.println(rand.nextFloat());
        System.out.println(rand.nextInt());
        System.out.println(rand.nextLong());
    }
}
