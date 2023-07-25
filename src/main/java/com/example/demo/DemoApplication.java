package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// Auto-Detecting
// 현재 등록된 패키지내의 컴포넌트를 탐색해서 생성해라
@ComponentScan({
	"dao",
	"service",
	"controller",
	"com.example.demo"
})

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
