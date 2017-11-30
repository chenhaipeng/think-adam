package com.thinkme.base.aspect;

import com.thinkme.common.request.Request;
import com.thinkme.common.utils.ValidatorUtil;
import com.thinkme.utils.base.BeanUtils;
import com.thinkme.utils.json.FastJsonUtil;
import com.thinkme.utils.net.IPUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenhaipeng
 * @version 1.0
 * @date 2017/07/07 18:29
 */
@Aspect
@Component
@Order(5)
public class WebLogAspect {
	private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
	ThreadLocal<Long> startTime = new ThreadLocal<>();


	// 定义切点Pointcut
	@Pointcut("@annotation(com.thinkme.base.aspect.WebLog)")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Exception {
		startTime.set(System.currentTimeMillis());
//         TRACE_ID
		String token = java.util.UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		MDC.put("TRACE_ID", token);
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		Object[] arguments = joinPoint.getArgs();

		String url = request.getRequestURL().toString();
		String ip = IPUtil.getIp(request);
		//校验
		if (BeanUtils.isNotEmpty(arguments)) {
			Object object = arguments[0];
			if (object instanceof Request) {
//                String userId = request.getHeader("userId"); //((AppRequest) object).getUserId();
//                AppRequest appRequest = (AppRequest) object;
//                appRequest.setUserId(userId);
//                appRequest.setIp(ip);
				Map<String, Object> anno = getControllerMethodDescription(joinPoint);
//                if (anno.get("needLogin") != null && anno.get("needLogin") == Boolean.TRUE) {
//                    if (StringUtils.isBlank(userId)) {
//                        throw new BusinessException(Response.ERROR(UserCodeEnum.NEEDLOGIN));
//                    }
//                }
				if (anno.get("logger") != null && anno.get("logger") == Boolean.TRUE) {
					// 记录下请求内容
					logger.info("URL : {} ,IP :{},PARAM:{}", url, ip, FastJsonUtil.object2String(object));
				}

				if (anno.get("validate") != null && anno.get("validate") == Boolean.TRUE)
					ValidatorUtil.validator(object);
			} else {
				logger.info("URL : {} ,IP :{}", url, ip);
			}

		}
	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		try {

			// 处理完请求，返回内容
			logger.info("RESPONSE : {},SPEND TIME:{} MS ", FastJsonUtil.object2String(ret), (System.currentTimeMillis() - startTime.get()));
			MDC.remove("TRACE_ID");
		} catch (Exception e) {
			//防止因为json转换的问题，导致返回失败
		}
	}


	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 *
	 * @param joinPoint 切点
	 * @return 方法描述
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, Object> getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					map.put("description", method.getAnnotation(WebLog.class).description());
					map.put("needLogin", method.getAnnotation(WebLog.class).needLogin());
					map.put("validate", method.getAnnotation(WebLog.class).validate());
					map.put("logger", method.getAnnotation(WebLog.class).logger());
					break;
				}
			}
		}
		return map;
	}


}
