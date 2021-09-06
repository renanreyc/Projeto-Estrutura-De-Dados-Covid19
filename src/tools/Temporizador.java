package tools;

public class Temporizador {

    private long tempoInicial = 0;
    private long tempoFinal = 0;

    public static final int MELHOR = 0;
    public static final int MEDIO = 1;
    public static final int PIOR = 2;

    public final long[] insertionTimeObitos = new long[3];
    public final long[] insertionTimeCasos = new long[3];
    public final long[] insertionTimeCidades = new long[3];

    public final long[] selectionTimeObitos = new long[3];
    public final long[] selectionTimeCasos = new long[3];
    public final long[] selectionTimeCidades = new long[3];

    public final long[] mergeTimeObitos = new long[3];
    public final long[] mergeTimeCasos = new long[3];
    public final long[] mergeTimeCidades = new long[3];

    public final long[] quickTimeObitos = new long[3];
    public final long[] quickTimeCasos = new long[3];
    public final long[] quickTimeCidades = new long[3];

    public final long[] quickMedTimeObitos = new long[3];
    public final long[] quickMedTimeCasos = new long[3];
    public final long[] quickMedTimeCidades = new long[3];

    public final long[] countingTimeObitos = new long[3];
    public final long[] countingTimeCasos = new long[3];

    public final long[] heapTimeObitos = new long[3];
    public final long[] heapTimeCasos = new long[3];
    public final long[] heapTimeCidades = new long[3];

    public void setTemporizadorTempoInicial() {
        tempoInicial = System.currentTimeMillis();
    }

    public void setTemporizadorTempoFinal() {
        tempoFinal = System.currentTimeMillis() - getTemporizadorTempoInicial();
    }

    public void setInsertionTimeObitos(int index, long insertionTime) {
        this.insertionTimeObitos[index] = insertionTime;
    }

    public void setInsertionTimeCasos(int index, long insertionTime) {
        this.insertionTimeCasos[index] = insertionTime;
    }

    public void setInsertionTimeCidades(int index, long insertionTime) {
        this.insertionTimeCidades[index] = insertionTime;
    }

    public void setQUickMedTimeObitos(int index, long quickMedTime) {
        this.quickMedTimeObitos[index] = quickMedTime;
    }

    public void setQUickMedTimeCasos(int index, long quickMedTime) {
        this.quickMedTimeCasos[index] = quickMedTime;
    }

    public void setQUickMedTimeCidades(int index, long quickMedTime) {
        this.quickMedTimeCidades[index] = quickMedTime;
    }


    public void setselectionTimeObitos(int index, long selectionTime) {
        this.selectionTimeObitos[index] = selectionTime;
    }

    public void setselectionTimeCasos(int index, long selectionTime) {
        this.selectionTimeCasos[index] = selectionTime;
    }

    public void setselectionTimeCidades(int index, long selectionTime) {
        this.selectionTimeCidades[index] = selectionTime;
    }

    public void setCountingTimeObitos(int index, long countingTime) {
        this.countingTimeObitos[index] = countingTime;
    }

    public void setCountingTimeCasos(int index, long countingTime) {
        this.countingTimeCasos[index] = countingTime;
    }

    public void setHeapTimeObitos(int index, long heapTime) {
        this.heapTimeObitos[index] = heapTime;
    }

    public void setQuickTimeObitos(int index, long quickTime) {
        this.quickTimeObitos[index] = quickTime;
    }

    public void setQuickTimeCasos(int index, long quickTime) {
        this.quickTimeCasos[index] = quickTime;
    }

    public void setQuickTimeCidades(int index, long quickTime) {
        this.quickTimeCidades[index] = quickTime;
    }

    public void setMergeTimeObitos(int index, long mergeTime) {
        this.mergeTimeObitos[index] = mergeTime;
    }

    public void setMergeTimeCasos(int index, long mergeTime) {
        this.mergeTimeCasos[index] = mergeTime;
    }

    public void setMergeTimeCidades(int index, long mergeTime) {
        this.mergeTimeCidades[index] = mergeTime;
    }

    public void setHeapTimeCasos(int index, long heapTime) {
        this.heapTimeCasos[index] = heapTime;
    }

    public void setHeapTimeCidades(int index, long heapTime) {
        this.heapTimeCidades[index] = heapTime;
    }

    public long getTemporizadorTempoFinal() {
        return tempoFinal;
    }

    public long getTemporizadorTempoInicial() {
        return tempoInicial;
    }

}
