package com.tlms.core.t;
/**
 * 类泛型
 * @author 160068
 * 2018年12月6日 下午3:12:33
 * @param <T>
 */
public class Point<T> {
	private T x;
	private T y;
	public T getX() {
		return x;
	}
	public void setX(T x) {
		this.x = x;
	}
	public T getY() {
		return y;
	}
	public void setY(T y) {
		this.y = y;
	}
	
}
