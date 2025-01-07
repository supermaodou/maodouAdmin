package org.maodou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.maodou.mapper")
public class MaodouAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaodouAdminApplication.class, args);
	}

}
