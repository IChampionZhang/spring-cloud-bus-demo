package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.BusProperties;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.study.event.MyEvent;

@SpringBootApplication
public class SpringCloudBusDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context= SpringApplication.run(SpringCloudBusDemoApplication.class, args);
		/*AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();
		
		applicationContext.register(EventConfiguration.class);
		applicationContext.register(MyListener.class);
		
		applicationContext.refresh();
		
		ApplicationEventPublisher publisher=applicationContext;
		publisher.publishEvent(new MyApplicationEvent("开始学习Spring Cloud Bus"));*/
		
		BusProperties properties=context.getBean(BusProperties.class);
		context.publishEvent(new MyEvent("这是 springcloud-bus事件", properties.getId()));
	}

	public static class MyApplicationEvent extends ApplicationEvent{

		public MyApplicationEvent(String  msg) {
			super(msg);
			// TODO Auto-generated constructor stub
		}
		
	}
	
	@Configuration
	public static class EventConfiguration{
		
		@EventListener
		public void onEvent(MyApplicationEvent event){
			System.out.println("监听到事件："+event);
		}
	}
	
	@Component
	public static class MyListener implements ApplicationListener<MyEvent>{

		@Override
		public void onApplicationEvent(MyEvent event) {
			// TODO Auto-generated method stub
			System.out.println("收到事件："+event.getSource());
		}
		
	}
}
