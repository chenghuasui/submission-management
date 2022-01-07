package top.mingde;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import io.undertow.server.DefaultByteBufferPool;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EnableAsync
@EnableCaching
@SpringBootApplication(scanBasePackages="top.mingde")
@EnableTransactionManagement(proxyTargetClass = true)
@EnableAspectJAutoProxy(exposeProxy = true)
public class OperationApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext application = SpringApplication.run(OperationApplication.class, args);
		ConfigurableEnvironment environment = application.getEnvironment();
		String[] activeProfiles = environment.getActiveProfiles();
		log.info("Application Startup Successful. The Count Of Parameter Is " + activeProfiles.length);
	}

	@Component
	public class CustomizationBean implements WebServerFactoryCustomizer<UndertowServletWebServerFactory> {

		@Override
		public void customize(UndertowServletWebServerFactory factory) {
			factory.addDeploymentInfoCustomizers(deploymentInfo -> {
				WebSocketDeploymentInfo webSocketDeploymentInfo = new WebSocketDeploymentInfo();
				webSocketDeploymentInfo.setBuffers(new DefaultByteBufferPool(false, 1024));
				deploymentInfo.addServletContextAttribute("io.undertow.websockets.jsr.WebSocketDeploymentInfo",
						webSocketDeploymentInfo);
			});
		}
	}

	@Bean
	public ConfigurationCustomizer configurationCustomizer() {
		return new ConfigurationCustomizer() {
			public void customize(MybatisConfiguration configuration) {
				configuration.setUseGeneratedShortKey(false);
			}
		};
	}

}
