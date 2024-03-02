package me.ghosty.notarat;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

final class ClassAccessor<T> {
	
	private final Class<T> clazz;
	private T object;
	
	ClassAccessor(Class<T> clazz, T object) {
		this.clazz = clazz;
		this.object = object;
	}
	
	Object getField(String name) throws Exception {
		Field field = clazz.getDeclaredField(name);
		if (!field.isAccessible())
			field.setAccessible(true);
		return field.get(object);
	}
	
	Object invoke(String name, Object... args) throws Exception {
		Class[] clazzs = new Class[args.length];
		for (int i = 0; i < args.length; i++)
			clazzs[i] = args[i].getClass();
		
		return invokeT(name, args, clazzs);
	}
	
	/**
	 * I had too many shit to manage with this method having the same name as the one above and the parameters being arrays... so I just added a T
	 */
	Object invokeT(String name, Object[] args, Class[] clazzs) throws Exception {
		Method method = clazz.getMethod(name, clazzs);
		if (!method.isAccessible())
			method.setAccessible(true);
		return method.invoke(object, args);
	}
	
	ClassAccessor<T> getObject(Object... args) throws Exception {
		Class[] clazzs = new Class[args.length];
		for (int i = 0; i < args.length; i++)
			clazzs[i] = args[i].getClass();
		
		Constructor<T> constructor = clazz.getDeclaredConstructor(clazzs);
		if (!constructor.isAccessible())
			constructor.setAccessible(true);
		object = constructor.newInstance(args);
		return this;
	}
	
	T get() {
		return object;
	}
	
}
