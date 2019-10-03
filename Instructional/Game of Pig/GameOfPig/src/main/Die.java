package main;

import java.util.*;

public class Die
{
    private Random random;
    
    public Die() {
        this.random = new Random();
    }
    
    public int roll() {
        return this.random.nextInt(6) + 1;
    }
}
