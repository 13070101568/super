package cn.yixiaobai.utils;

public interface Function<T, E> {

	public T callback(E e);
}