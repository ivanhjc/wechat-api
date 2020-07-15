package net.ivanhjc.wechat.api.mini.app.crypt.result;

import com.google.gson.annotations.SerializedName;

public class BaseDecryptedResult {

    private Watermark watermark;

    public Watermark getWatermark() {
        return watermark;
    }

    public void setWatermark(Watermark watermark) {
        this.watermark = watermark;
    }

    public static class Watermark {
        @SerializedName(value = "appId", alternate = {"appid"})
        private String appId;
        private String timestamp;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}
