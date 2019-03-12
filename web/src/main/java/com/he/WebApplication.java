package com.he;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@ComponentScan("com.he")
@EnableScheduling//开启定时任务注解
//@PropertySource(value = {"classpath:db.yml"})
public class WebApplication {//extends SpringBootServletInitializer

//	/**
//	 * 如需部署到web容器，需extends SpringBootServletInitializer，并复写此方法
//	 * @param application
//	 * @return
//	 */
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(WebApplication.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
