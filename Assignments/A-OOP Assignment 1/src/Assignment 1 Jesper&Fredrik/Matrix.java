import java.util.Arrays;

public class Matrix {
	private double[][] elements;

	public Matrix(int x, int y) {
		elements = new double[x][y];
	}

	public Matrix(double[][] matrix) {
		elements = matrix.clone();
	}

	public double[][] getElements() {
		return elements;
	}

	public double getElements(int x, int y) {
		return elements[x][y];
	}

	public Matrix setElements(int x, int y, double data) {
		double[][] matrix = this.getElements();
		matrix[x][y] = data;
		return new Matrix(matrix);
	}

	public int[] size() {
		return new int[] { this.elements.length, this.elements[0].length };
	}

	public Matrix multiply(Matrix m2) {
		if (this.size()[0] != m2.size()[1])
			throw new IllegalArgumentException();
		double[][] matrix = new double[this.size()[0]][m2.size()[1]];
		for (int i = 0; i < this.size()[0]; i++) {
			for (int j = 0; j < m2.size()[1]; j++) {
				for (int k = 0; k < this.size()[0]; k++) {
					matrix[i][j] += this.getElements(i, k) * m2.getElements(k, j);
				}
			}
		}
		return new Matrix(matrix);
	}

	public Matrix add(Matrix m2) {
		if (this.size()[0] != m2.size()[1])
			throw new IllegalArgumentException();
		double[][] matrix = new double[this.size()[0]][m2.size()[1]];
		for (int i = 0; i < this.size()[0]; i++) {
			for (int j = 0; j < m2.size()[1]; j++) {
				matrix[i][j] = this.getElements(i, j) + m2.getElements(i, j);
			}
		}
		return new Matrix(matrix);
	}

	@Override
	public String toString() {
		String sum = "";

		for (int i = 0; i < this.size()[0]; i++) {
			sum += Arrays.toString(this.getElements()[i]);
			sum += "\n";
		}
		return sum;
	}
}