package AZERTY;

public class MockedRandomWrapper implements IRandomWrapper {
    private float theFloat;

    public MockedRandomWrapper (float theFloat) {
        this.theFloat = theFloat;
    }

    public float getFloat() {
        return theFloat;
    }
}
