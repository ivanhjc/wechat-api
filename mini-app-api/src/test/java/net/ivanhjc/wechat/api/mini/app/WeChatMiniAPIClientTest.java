package net.ivanhjc.wechat.api.mini.app;


import org.junit.Test;

public class WeChatMiniAPIClientTest {

    @Test
    public void jsCode2Session() {
        WeChatMiniAPIClient client = new WeChatMiniAPIClient("1234", "5478");
        client.jsCode2Session("abcd");
    }
}
