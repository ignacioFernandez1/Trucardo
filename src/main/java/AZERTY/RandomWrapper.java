package AZERTY;
import java.util.*;

public class RandomWrapper implements IRandomWrapper{
    private Random rand;

    public RandomWrapper() {
        rand = new Random();
    }

    public float getFloat() {
        return rand.nextFloat();
    }
}