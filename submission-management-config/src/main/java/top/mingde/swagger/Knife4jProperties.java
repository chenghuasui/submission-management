package top.mingde.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;

@Data
@ConfigurationProperties(prefix=Knife4jProperties.SWAGGER_PREFIX)
@ApiModel("Swagger-Api接口实体类")
public class Knife4jProperties {

    public static final String SWAGGER_PREFIX = "knife4j";

    @ApiModelProperty("标题")
    private String title="SWAGGER API";

    @ApiModelProperty("描述")
    private String description="";

    @ApiModelProperty("Api服务链接")
    private String termsOfServiceUrl="http://siufung.net/";

    @ApiModelProperty("作者")
    private String contact="SIUFUNG";

    @ApiModelProperty("版本")
    private String version="2.0.0";

    @ApiModelProperty("扫描包名")
    private String basePackage;

    @ApiModelProperty("权限Token令牌名称")
    private String tokenHeader= HttpHeaders.AUTHORIZATION;

    @ApiModelProperty("Api组名")
    private String groupName = "1.x";

}
