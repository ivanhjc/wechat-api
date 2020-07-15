package net.ivanhjc.wechat.api.mini.app.result;

import com.google.gson.annotations.SerializedName;

public class WeChatTokenResult extends BaseResult {
    /**
     * 获取到的凭证
     */
    @SerializedName(value = "accessToken", alternate = {"access_token"})
    private String accessToken;
    /**
     * 凭证有效时间，单位：毫秒。微信返回的是7200秒之内的值，单位为秒，我们内部使用转换为绝对时间，单位为毫秒。
     */
    @SerializedName(value = "expiresIn", alternate = {"expires_in"})
    private Long expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
