package top.mingde.security.properities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;


@Data
@ApiModel("访问拦截")
@ConfigurationProperties(prefix = PermitAllUrlProperties.AUTH_IGNORE_PREFIX)
public class PermitAllUrlProperties {
	
	public static final String AUTH_IGNORE_PREFIX = "spring.security.oauth2.ignore";

	@ApiModelProperty("访问拦截URL")
	public List<String> urls = new ArrayList<>();

}
