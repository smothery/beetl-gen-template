package panzer.vor.generate;

import com.gitee.easyopen.ApiConfig;
import com.gitee.easyopen.support.ApiController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class IndexController extends ApiController {

    @Value("${app.appId}")
    private String appId;
    @Value("${app.appSecret}")
    private String appSecret;
    @Value("${app.showDoc}")
    private Boolean showDoc;
    @Value("${app.docPassword}")
    private String docPassword;

    // @Autowired
    // private AuthInterceptor authInterceptor;
    // @Autowired
    // private LogInterceptor logInterceptor;

    // @CrossOrigin(origins = "*", maxAge = 3000)
    // @RequestMapping("mono")
    // @ResponseBody
    // public Mono<Object> mono(HttpServletRequest request, HttpServletResponse response) {
    //     Object obj = this.getWebfluxInvokeTemplate().processInvoke(request, response);
    //     return Mono.just(obj);
    // }

    @Override
    protected void initApiConfig(ApiConfig apiConfig) {
        // 显示文档页面
        apiConfig.setShowDoc(showDoc);
        // 设置文档页面密码
        apiConfig.setJwtExpireIn(86400);
        apiConfig.setDocPassword(docPassword);
        apiConfig.setTimeoutSeconds(6000);

        // 配置国际化消息
        apiConfig.getIsvModules().add("i18n/isv/goods_error");

        // 配置秘钥键值对
        Map<String, String> appSecretStore = new HashMap<String, String>();
        appSecretStore.put(appId, appSecret);

        apiConfig.addAppSecret(appSecretStore);

        // apiConfig.setInterceptors(new ApiInterceptor[]{authInterceptor, logInterceptor});
    }

}