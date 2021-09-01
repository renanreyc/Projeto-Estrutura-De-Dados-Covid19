package tools;

import java.util.Arrays;

public class Vector<T> {
    private T[] elementos;
    private int tamanho, capacidade;

    public Vector(int capacity) throws IllegalArgumentException {
        if (capacity <= 0) throw new IllegalArgumentException("O tamanho deve ser maior que 0. Tamanho inválido.");
        this.tamanho = 0;
        this.capacidade = capacity;
        this.instanciarElemento(capacidade);
    }

    public void insert(T element, int posicao) throws ArrayIndexOutOfBoundsException {
        if (posicao < 0) throw new ArrayIndexOutOfBoundsException("Index Inválido.");
        if (posicao > this.capacidade) throw new ArrayIndexOutOfBoundsException ("posição do index maior que a capacidade. Index Inválido");
        this.elementos[posicao] = element;
    }

    @SuppressWarnings("unchecked")
    private void instanciarElemento(int capacidade) {
        this.elementos = (T[]) new Object[capacidade];
    }

    public T encontrarIndex(int index) throws ArrayIndexOutOfBoundsException {
        if(index < 0 || index > this.capacidade) throw new ArrayIndexOutOfBoundsException("Index Inválido.");
        return elementos[index];
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "elementos=" + Arrays.toString(elementos) +
                ", tamanho=" + tamanho +
                ", capacidade=" + capacidade +
                '}';
    }
}
