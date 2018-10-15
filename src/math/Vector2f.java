
package math;

public class Vector2f {
	
	private float x, y;
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float length() {
		return (float) Math.sqrt(x * x + y * y);
	}
	
	public float dot(Vector2f r) {
		return x * r.getX() + y * r.getY();
	}
	
	public Vector2f normalise() {
		float length = length();
		x /= length;
		y /= length;
		return this;
	}
	
	public Vector2f rotate(float angle) {
		float cos = (float) Math.cos(angle);
		float sin = (float) Math.sin(angle);
		return new Vector2f(x * cos - y * sin, x * sin + y * cos);
	}
	
	public Vector2f add(float f) {
		return new Vector2f(x + f, y + f);
	}
	
	public Vector2f add(Vector2f r) {
		return new Vector2f(x + r.getX(), y + r.getY());
	}
	
	public Vector2f sub(float f) {
		return new Vector2f(x - f, y - f);
	}
	
	public Vector2f sub(Vector2f r) {
		return new Vector2f(x - r.getX(), y - r.getY());
	}
	
	public Vector2f mul(float f) {
		return new Vector2f(x * f, y * f);
	}
	
	public Vector2f mul(Vector2f r) {
		return new Vector2f(x * r.getX(), y * r.getY());
	}
	
	public Vector2f div(float f) {
		return new Vector2f(x / f, y / f);
	}
	
	public Vector2f div(Vector2f r) {
		return new Vector2f(x / r.getX(), y / r.getY());
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
	
}
