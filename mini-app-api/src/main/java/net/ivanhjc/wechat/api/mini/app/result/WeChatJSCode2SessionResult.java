package net.ivanhjc.wechat.api.mini.app.result;

import com.google.gson.annotations.SerializedName;

public class WeChatJSCode2SessionResult extends BaseResult {

    /**
     * 微信用户的唯一标识
     */
    @SerializedName(value = "openId", alternate = {"openid"})
    private String openId;

    /**
     * 会话密钥
     */
    @SerializedName(value = "sessionKey", alternate = {"session_key"})
    private String sessionKey;

    /**
     * 用户在微信开放平台的唯一标识符。本字段在满足一定条件的情况下才返回。
     */
    @SerializedName(value = "unionId", alternate = {"unionid"})
    private String unionId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
}
