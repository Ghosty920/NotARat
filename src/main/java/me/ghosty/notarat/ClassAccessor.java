package me.ghosty.notarat;


import java.lang.reflect.Field;

final class ClassAccessor<T> {
	
	private final Class<T> clazz;
	private final T object;
	
	ClassAccessor(final Class<T> clazz, final T object) {
		this.clazz = clazz;
		this.object = object;
	}
	
	Object getField(final String name) throws NoSuchFieldException, IllegalAccessException {
		final Field field = clazz.getDeclaredField(name);
		if (!field.isAccessible())
			field.setAccessible(true);
		return field.get(object);
	}
	
}
