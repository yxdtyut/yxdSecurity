package com.yxdtyut.security.wireMock;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * @Author : yangxudong
 * @Description :   wireMock伪造服务启动类
 * @Date : 下午4:59 2018/6/23
 */

public class MockServer {
    public static void main(String[] args) throws IOException {
        configureFor(9999);
        removeAllMappings();
        mock("/order/1","01");
    }

    private static void mock(String url, String file) throws IOException {
        ClassPathResource resource = new ClassPathResource("mock/response/" + file + ".txt");
        String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8").toArray(),"\n");
        System.out.println(content);
        stubFor(get(urlPathEqualTo(url)).willReturn(aResponse().withBody(content).withStatus(200)));
    }
}
