
package math;

public class Vector3f {
	
	private float x, y, z;
	
	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float length() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}
	
	public float dot(Vector3f r) {
		return x * r.getX() + y * r.getY() + z * r.getZ();
	}
	
	public Vector3f cross(Vector3f r) {
		float x_ = y * r.getZ() - z * r.getY();
		float y_ = z * r.getX() - x * r.getZ();
		float z_ = x * r.getY() - y * r.getX();
		return new Vector3f(x_, y_, z_);
	}
	
	public Vector3f normalise() {
		float length = length();
		x /= length;
		y /= length;
		z /= length;
		return this;
	}
	
	public Vector3f rotate(float angle) {
		return null; // TODO Vector3f::rotate(float angle)
	}
	
	public Vector3f add(float f) {
		return new Vector3f(x + f, y + f, z + f);
	}
	
	public Vector3f add(Vector3f r) {
		return new Vector3f(x + r.getX(), y + r.getY(), z + r.getZ());
	}
	
	public Vector3f sub(float f) {
		return new Vector3f(x - f, y - f, z - f);
	}
	
	public Vector3f sub(Vector3f r) {
		return new Vector3f(x - r.getX(), y - r.getY(), z - r.getZ());
	}
	
	public Vector3f mul(float f) {
		return new Vector3f(x * f, y * f, z * f);
	}
	
	public Vector3f mul(Vector3f r) {
		return new Vector3f(x * r.getX(), y * r.getY(), z * r.getZ());
	}
	
	public Vector3f div(float f) {
		return new Vector3f(x / f, y / f, z / f);
	}
	
	public Vector3f div(Vector3f r) {
		return new Vector3f(x / r.getX(), y / r.getY(), z / r.getZ());
	}
	
	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getZ() {
		return z;
	}
	
	public void setZ(float z) {
		this.z = z;
	}
	
}
