package net.ivanhjc.wechat.api.mini.app;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import net.ivanhjc.utility.net.HttpUtils;
import net.ivanhjc.wechat.api.mini.app.request.WeChatSendSubscribedMessageRequest;
import net.ivanhjc.wechat.api.mini.app.result.WeChatJSCode2SessionResult;
import net.ivanhjc.wechat.api.mini.app.result.WeChatTokenResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class WeChatMiniAPIClient {

    private Logger logger = LogManager.getLogger(this.getClass());

    private String appId;
    private String appSecret;

    public WeChatMiniAPIClient(String appId, String appSecret) {
        this.appId = appId;
        this.appSecret = appSecret;
    }

    /**
     * 登录
     *
     * @param code 临时登录凭证 code
     */
    public WeChatJSCode2SessionResult jsCode2Session(String code) {
        String requestURL = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code";
        logger.debug("jsCode2Session request: " + requestURL);
        String result = HttpUtils.getInstance().get(requestURL);
        logger.debug("jsCode2Session result: " + result);
        return new Gson().fromJson(result, WeChatJSCode2SessionResult.class);
    }

    /**
     * 接口调用凭证
     */
    public WeChatTokenResult token() {
        String requestURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
        logger.debug("token request: " + requestURL);
        String result = HttpUtils.getInstance().get(requestURL);
        logger.debug("token result: " + result);
        WeChatTokenResult weChatTokenResult = new Gson().fromJson(result, WeChatTokenResult.class);
        //微信返回过期时间7200秒，实际过期时间要更短，所以采取1小时更新一次
        weChatTokenResult.setExpiresIn(System.currentTimeMillis() + 3600000);
        return weChatTokenResult;
    }

    /**
     * 订阅消息
     *
     * @param accessToken 接口调用凭证
     * @param request     请求参数
     */
    public void sendSubscribedMessage(String accessToken, WeChatSendSubscribedMessageRequest request) {
        String requestURL = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + accessToken;
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("touser", request.getTouser());
        requestBody.addProperty("template_id", request.getTemplateId());
        requestBody.addProperty("page", request.getPage());
        requestBody.addProperty("miniprogram_state", request.getTouser());
        requestBody.addProperty("lang", request.getLang());
        List<WeChatSendSubscribedMessageRequest.Element> list = request.getData();
        if (list != null && list.size() > 0) {
            JsonObject data = new JsonObject();
            list.forEach(e -> {
                JsonObject child = new JsonObject();
                child.addProperty("value", e.getValue());
                data.add(e.getKey(), child);
            });
            requestBody.add("data", data);
        }
        String json = requestBody.toString();
        logger.debug("sendSubscribedMessage request: " + requestURL + ", params: " + json);
        String result = HttpUtils.getInstance().postJSON(requestURL, json);
        logger.debug("sendSubscribedMessage result: " + result);
    }
}
