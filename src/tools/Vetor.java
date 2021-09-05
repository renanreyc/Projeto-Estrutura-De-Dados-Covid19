package tools;

import java.util.Arrays;

public class Vetor<T> {

    private int tamanho;
    private int capacidade;
    private T[] elementos;

    public Vetor(int capacidade) throws IllegalArgumentException {
        if (capacidade <= 0) throw new IllegalArgumentException("Capacidade Inválida. Capacidade mínimo > 0");
        this.capacidade = capacidade;
        this.instanciarElemento(capacidade);
        this.tamanho = 0;
    }

    public void adicionarElemento(T element) {
        aumentarTamanho();
        if (this.tamanho < this.capacidade) {
            this.elementos[this.tamanho++] = element;
        }
    }

    public T encontrarElemento(int index) throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index > this.capacidade) throw new ArrayIndexOutOfBoundsException("Index Inválido.");
        return elementos[index];
    }

    public void inserirElemento(T element, int position) throws ArrayIndexOutOfBoundsException {
        if (position < 0 || position > this.capacidade) throw new ArrayIndexOutOfBoundsException("Index Inválido.");
        this.elementos[position] = element;
    }

    private void aumentarTamanho() {
        if (this.tamanho == this.capacidade) {
            T[] newElements = (T[]) new Object[this.capacidade * 2];
            for (int i = 0; i < this.tamanho; i++) {
                newElements[i] = this.elementos[i];
            }
            this.elementos = newElements;
        }
        setCapacidade();
    }

    private void instanciarElemento(int capacity) {
        this.elementos = (T[]) new Object[capacity];
    }

    private void setCapacidade() {
        this.capacidade = this.elementos.length;
    }

    public void setTamanho() {
        this.tamanho = this.capacidade;
    }

    public int getTamanho() {
        return this.tamanho;
    }

    @Override
    public String toString() {
        return "Vetor{" +
                "tamanho=" + tamanho +
                ", capacidade=" + capacidade +
                ", elementos=" + Arrays.toString(elementos) +
                '}';
    }
}
