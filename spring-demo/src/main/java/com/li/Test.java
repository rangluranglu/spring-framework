package com.li;

import com.li.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

	public static void main(String[] args) {

 		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		// UserService.class ---> 无参构造方法 ---> 对象 ----> 依赖注入 ----> Bean对象 ： bean的创建生命周期
		UserService userService = (UserService) applicationContext.getBean("userService");

		userService.test();
	}
}
