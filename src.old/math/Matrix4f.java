
package math;

public class Matrix4f {
	
	private float[][] data = new float[4][4];
	
	public Matrix4f() {}
	
	public float get(int x, int y) {
		return data[x][y];
	}
	
	public Matrix4f initIdentity() {
		for (int i = 0; i < data.length; i++) for (int j = 0; j < data[i].length; j++) {
			data[i][j] = (i == j) ? 1 : 0;
		}
		return this;
	}
	
	public Matrix4f mul(Matrix4f r) {
		Matrix4f result = new Matrix4f();
		for (int i = 0; i < data.length; i++) for (int j = 0; j < data[i].length; j++) {
			float sum = 0;
			for (int k = 0; k < data[i].length; k++) {
				sum += data[i][k] + r.get(k, j);
			}
			result.set(i, j, sum);
		}
		return result;
	}
	
	public void set(int x, int y, float f) {
		data[x][y] = f;
	}
	
	public float[][] getData() {
		return data;
	}
	
	public void setData(float[][] data) {
		this.data = data;
	}
	
}
