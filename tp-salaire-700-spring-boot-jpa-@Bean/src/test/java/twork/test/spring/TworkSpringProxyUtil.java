package twork.test.spring;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;

/**
 * @author Gauthier Peel
 */
public class TworkSpringProxyUtil {

	/**
	 * If the given object is a proxy, set the return value as the object * being proxied, otherwise
	 * return the given object.
	 */
	public static final Object unwrapProxy(Object bean) throws Exception {
		if (AopUtils.isAopProxy(bean) && bean instanceof Advised) {
			Advised advised = (Advised) bean;
			bean = advised.getTargetSource().getTarget();
		}
		return bean;
	}

}