package lab1;

import java.util.Arrays;

public class QUBOProblem {
    
    private double[][] couplingMatrix;
    private int[] optimalState;
    private double optimalStateValue;

    public QUBOProblem() {
        this.couplingMatrix = new double[1][1];
        this.optimalState = new int[]{0};
        this.optimalStateValue = Double.POSITIVE_INFINITY;
    }

    public QUBOProblem(double[][] matrix) {
        validateCouplingMatrix(matrix);
        this.couplingMatrix = deepCopy(matrix);
        this.optimalState = new int[matrix.length];
        Arrays.fill(this.optimalState, 0);
        this.optimalStateValue = computeEnergy(this.optimalState);
    }

    public QUBOProblem(double[][] matrix, int[] state) {
        validateCouplingMatrix(matrix);
        if (state.length != matrix.length) {
            throw new IllegalArgumentException("Długość wektora stanu musi odpowiadać rozmiarowi macierzy Q");
        }
        this.couplingMatrix = deepCopy(matrix);
        this.optimalState = Arrays.copyOf(state, state.length);
        this.optimalStateValue = computeEnergy(state);
    }

    public double computeEnergy(int[] state) {
        if (state.length != couplingMatrix.length) {
            throw new IllegalArgumentException("Długość wektora stanu nie pasuje do rozmiaru macierzy Q");
        }

        double energy = 0.0;
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                energy += state[i] * couplingMatrix[i][j] * state[j];
            }
        }

        if (energy < optimalStateValue) {
            optimalStateValue = energy;
            optimalState = Arrays.copyOf(state, state.length);
        }

        return energy;
    }

    @Override 
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QUBOProblem)) return false;

        QUBOProblem other = (QUBOProblem) obj;

        if (!Arrays.deepEquals(this.couplingMatrix, other.couplingMatrix)) {
            return false;
        }
        
        if (Arrays.equals(this.optimalState, other.optimalState) && 
            this.optimalStateValue == other.optimalStateValue) {
            return true;
        }

        return false;
    }

    public double[][] getCouplingMatrix() {
        return deepCopy(couplingMatrix);
    }

    public int[] getOptimalState() {
        return Arrays.copyOf(optimalState, optimalState.length);
    }

    public double getOptimalStateValue() {
        return optimalStateValue;
    }

    // Metody pomocnicze
    private void validateCouplingMatrix(double[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Macierz współczynników nie może być null");
        }
        int n = matrix.length;
        for (double[] row : matrix) {
            if (row.length != n) {
                throw new IllegalArgumentException("Macierz współczynników musi być kwadratowa");
            }
        }
    }

    private double[][] deepCopy(double[][] matrix) {
        double[][] copy = new double[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            copy[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        }
        return copy;
    }

    public static void main(String[] args) {
        double[][] qMatrix = {
            {1.0, -1.0},
            {-1.0, 2.0}
        };

        int[] initialState = {0, 0};

        QUBOProblem qubo1 = new QUBOProblem(qMatrix);
        QUBOProblem qubo2 = new QUBOProblem(qMatrix, initialState);

        System.out.println("Macierz Q:");
        System.out.println(Arrays.deepToString(qubo1.getCouplingMatrix()));

        System.out.println("Wartość dla początkowego stanu:");
        System.out.println(qubo1.computeEnergy(initialState));

        System.out.println("Optymalny stan:");
        System.out.println(Arrays.toString(qubo1.getOptimalState()));

        System.out.println("Czy qubo1 i qubo2 to ten sam problem? " + qubo1.equals(qubo2));
    }
}
