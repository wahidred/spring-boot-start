package twork;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class DebugBpp implements BeanPostProcessor {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Object postProcessBeforeInitialization(Object _bean, String _beanName) throws BeansException {
		logger.debug("¤¤¤¤¤¤¤¤¤¤  Bean :" + _beanName + ",  CREATED : " + _bean.toString());
		return _bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object _bean, String _beanName) throws BeansException {
		return _bean;
	}

}
