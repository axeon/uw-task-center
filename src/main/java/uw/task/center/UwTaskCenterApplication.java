package uw.task.center;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import uw.common.app.AppBootStrap;

@SpringBootApplication
@EnableDiscoveryClient
public class UwTaskCenterApplication {
    public static void main(String[] args) {
        AppBootStrap.run(UwTaskCenterApplication.class, args);
    }
}
