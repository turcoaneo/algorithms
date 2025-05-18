package org.toptal.test.copilot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.toptal.test.copilot.service.ExampleService;

@SpringBootApplication
@EnableAspectJAutoProxy // Enables AOP proxying
public class CopilotApplication {

    public static void main(String[] args) {
        SpringApplication.run(CopilotApplication.class, args);
    }

}
