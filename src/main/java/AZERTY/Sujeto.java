package AZERTY;

public interface Sujeto {
    public void registrarObservador(Observador o);
    public void deregistrarObservador(Observador o);
    public void notificar();
}
