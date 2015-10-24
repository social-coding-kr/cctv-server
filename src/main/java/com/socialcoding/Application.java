package com.socialcoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class Application {

	private static final String PID_FILE_NAME = "cctv-server.pid";

    public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Application.class);
		application.addListeners(new ApplicationPidFileWriter(PID_FILE_NAME));
		application.run(args);
    }

}
