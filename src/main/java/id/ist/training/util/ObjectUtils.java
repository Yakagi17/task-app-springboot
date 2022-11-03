package id.ist.training.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectUtils {
	
	private ObjectUtils() {
	}

	public static void copyProperties(final Object dest, final Object orig)	 {
		try {
			BeanUtils.copyProperties(dest, orig);
		} catch (IllegalAccessException | InvocationTargetException e) {
			log.warn(e.toString(), e);
		}
	}
}
