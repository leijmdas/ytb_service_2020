package ytb.common.redis;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Component
public class UtilInitialize implements ApplicationContextAware {

	@Bean("restTemplate")
	public RestTemplate registRestTemplate() {
		RestTemplate restTpl = new RestTemplate();
		StringHttpMessageConverter stringMsgConvert = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		restTpl.setMessageConverters(Arrays.asList(new BufferedImageHttpMessageConverter(),
				new ByteArrayHttpMessageConverter(), new ResourceHttpMessageConverter(),
				stringMsgConvert, createJsonConverter(),
				new AllEncompassingFormHttpMessageConverter()));
		return restTpl;
	}


	public static MappingJackson2HttpMessageConverter createJsonConverter() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(objectMapper);
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN));
		return converter;
	}

	@Bean
	public LocalValidatorFactoryBean validatorFactory(){
		LocalValidatorFactoryBean factory = new org.springframework.validation.beanvalidation.LocalValidatorFactoryBean();
//		BeanValidator.setValidatorFactory(factory);
		return factory;
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		setApplicationContextInner(ctx);

//		BeanValidator.setValidatorFactory(validatorFactory());
		// redis
		RedisUtil.Singleton.instanceRedisTemplate((RedisTemplate) ctx.getBean("stringRedisTemplate"));

//		// rest
//		RestUtil.Singleton.instanceRestTemplate((RestTemplate) ctx.getBean("restTemplate"));
//
//		UserSessionHolder.config("users:session:", 7200);	//设置地端MQ
//		ClientRequestConfigurator.config(clientRequestMq);
		
	}




	private static ApplicationContext applicationContext;

	/**
	 * 实现了ApplicationContextAware 接口，必须实现该方法；
	 * 通过传递applicationContext参数初始化成员变量applicationContext
	 * @param applicationContext
	 * @throws BeansException
	 */
	void setApplicationContextInner(ApplicationContext applicationContext) throws BeansException {
		UtilInitialize.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
