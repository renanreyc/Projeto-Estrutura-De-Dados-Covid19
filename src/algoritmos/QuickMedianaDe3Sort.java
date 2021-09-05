package algoritmos;

import tools.DataBase;
import tools.Vetor;

import java.text.Collator;

public class QuickMedianaDe3Sort {

    public static final int CASOS = 0;
    public static final int OBITOS = 1;
    public static final int CIDADES = 2;

    public static void quickMedianaDe3Sort(Vetor<DataBase> vetor, int inicio, int fim, int indicador) {
        if (inicio < fim) {
            int pivotIndex = 0;
            switch (indicador) {
                case CASOS:
                    int pivot3Index = buscarPivotCasosIndex(vetor, inicio, fim);
                    trocaNumeros(vetor, pivot3Index, fim);
                    pivotIndex = particionaCasos(vetor, inicio, fim);
                    break;
                case OBITOS:
                    pivot3Index = buscarPivotObitosIndex(vetor, inicio, fim);
                    trocaNumeros(vetor, pivot3Index, fim);
                    pivotIndex = particionaObitos(vetor, inicio, fim);
                    break;
                case CIDADES:
                    pivot3Index = buscarPivotCidadesIndex(vetor, inicio, fim);
                    trocaNumeros(vetor, pivot3Index, fim);
                    pivotIndex = particionaCidades(vetor, inicio, fim);
                    break;
            }
            quickMedianaDe3Sort(vetor, inicio, pivotIndex - 1, indicador);
            quickMedianaDe3Sort(vetor, pivotIndex + 1, fim, indicador);
        }
    }

    public static int particionaCasos(Vetor<DataBase> vetor, int inicio, int fim) {
        DataBase pivot = vetor.encontrarElemento(fim);
        int indexNumerosAbaixoDoPivot = inicio - 1;
        for (int indexNumerosMaioresDoPivot = inicio; indexNumerosMaioresDoPivot < fim; indexNumerosMaioresDoPivot++) {
            if (vetor.encontrarElemento(indexNumerosMaioresDoPivot).getAvailableConfirmed() <= pivot.getAvailableConfirmed()) {
                indexNumerosAbaixoDoPivot++;
                trocaNumeros(vetor, indexNumerosAbaixoDoPivot, indexNumerosMaioresDoPivot);
            }
        }
        trocaNumeros(vetor, indexNumerosAbaixoDoPivot + 1, fim);
        return indexNumerosAbaixoDoPivot + 1;
    }

    public static int particionaObitos(Vetor<DataBase> vetor, int inicio, int fim) {
        DataBase pivot = vetor.encontrarElemento(fim);
        int indexNumerosAbaixoDoPivot = inicio - 1;
        for (int indexNumerosMaioresDoPivot = inicio; indexNumerosMaioresDoPivot < fim; indexNumerosMaioresDoPivot++) {
            if (vetor.encontrarElemento(indexNumerosMaioresDoPivot).getAvailableConfirmed() <= pivot.getAvailableConfirmed()) {
                indexNumerosAbaixoDoPivot++;
                trocaNumeros(vetor, indexNumerosAbaixoDoPivot, indexNumerosMaioresDoPivot);
            }
        }
        trocaNumeros(vetor, indexNumerosAbaixoDoPivot + 1, fim);
        return indexNumerosAbaixoDoPivot + 1;
    }

    public static int particionaCidades(Vetor<DataBase> vetor, int inicio, int fim) {

        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.NO_DECOMPOSITION);

        DataBase pivot = vetor.encontrarElemento(fim);
        int indexNumerosAbaixoDoPivot = inicio - 1;
        for (int indexNumerosMaioresDoPivot = inicio; indexNumerosMaioresDoPivot < fim; indexNumerosMaioresDoPivot++) {
            if (collator.compare(vetor.encontrarElemento(indexNumerosMaioresDoPivot).getCity(), pivot.getCity()) <= 0) {
                indexNumerosAbaixoDoPivot++;
                trocaNumeros(vetor, indexNumerosAbaixoDoPivot, indexNumerosMaioresDoPivot);
            }
        }
        trocaNumeros(vetor, indexNumerosAbaixoDoPivot + 1, fim);
        return indexNumerosAbaixoDoPivot + 1;
    }

    public static int buscarPivotCasosIndex(Vetor<DataBase> vetor, int inicioIndex, int fimIndex) {
        int meioIndex;
        if (fimIndex % 2 == 0) {
            meioIndex = (fimIndex / 2) - 1;
        } else {
            meioIndex = fimIndex / 2;
        }

        int inicio = vetor.encontrarElemento(inicioIndex).getAvailableConfirmed();
        int fim = vetor.encontrarElemento(fimIndex).getAvailableConfirmed();
        int meio = vetor.encontrarElemento(meioIndex).getAvailableConfirmed();
        int pivotIndex;

        if (inicio > fim) {
            if (inicio > meio) {
                if (fim > meio) {
                    pivotIndex = fimIndex;
                } else {
                    pivotIndex = meioIndex;
                }
            } else {
                pivotIndex = inicioIndex;
            }
        } else {
            if (fim > meio) {
                if (inicio > meio) {
                    pivotIndex = inicioIndex;
                } else {
                    pivotIndex = meioIndex;
                }
            } else {
                pivotIndex = fimIndex;
            }
        }
        return pivotIndex;
    }

    public static int buscarPivotObitosIndex(Vetor<DataBase> vetor, int inicioIndex, int fimIndex) {

        int meioIndex;
        if (fimIndex % 2 == 0) {
            meioIndex = (fimIndex / 2) - 1;
        } else {
            meioIndex = fimIndex / 2;
        }

        int inicio = vetor.encontrarElemento(inicioIndex).getAvailableConfirmed();
        int fim = vetor.encontrarElemento(fimIndex).getAvailableConfirmed();
        int meio = vetor.encontrarElemento(meioIndex).getAvailableConfirmed();

        int pivotIndex;

        if (inicio > fim) {
            if (inicio > meio) {
                if (fim > meio) {
                    pivotIndex = fimIndex;
                } else {
                    pivotIndex = meioIndex;
                }
            } else {
                pivotIndex = inicioIndex;
            }
        } else {
            if (fim > meio) {
                if (inicio > meio) {
                    pivotIndex = inicioIndex;
                } else {
                    pivotIndex = meioIndex;
                }
            } else {
                pivotIndex = fimIndex;
            }
        }
        return pivotIndex;
    }

    public static int buscarPivotCidadesIndex(Vetor<DataBase> vetor, int inicioIndex, int fimIndex) {
        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.NO_DECOMPOSITION);

        int meioIndex;
        if (fimIndex % 2 == 0) {
            meioIndex = (fimIndex / 2) - 1;
        } else {
            meioIndex = fimIndex / 2;
        }

        String inicio = vetor.encontrarElemento(inicioIndex).getCity();
        String fim = vetor.encontrarElemento(fimIndex).getCity();
        String meio = vetor.encontrarElemento(meioIndex).getCity();
        int pivotIndex;

        if (collator.compare(inicio, fim) > 0) {
            if (collator.compare(inicio, meio) > 0) {
                if (collator.compare(fim, meio) > 0) {
                    pivotIndex = fimIndex;
                } else {
                    pivotIndex = meioIndex;
                }
            } else {
                pivotIndex = inicioIndex;
            }
        } else {
            if (collator.compare(fim, meio) > 0) {
                if (collator.compare(inicio, meio) > 0) {
                    pivotIndex = inicioIndex;
                } else {
                    pivotIndex = meioIndex;
                }
            } else {
                pivotIndex = fimIndex;
            }
        }
        return pivotIndex;
    }

    public static void trocaNumeros(Vetor<DataBase> vetor, int origem, int destino) {
        DataBase aux = vetor.encontrarElemento(origem);
        vetor.inserirElemento(vetor.encontrarElemento(destino), origem);
        vetor.inserirElemento(aux, destino);
    }
}
