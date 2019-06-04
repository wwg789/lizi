package cn.lizi.lizi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class LiziApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiziApplication.class, args);
	}

}
