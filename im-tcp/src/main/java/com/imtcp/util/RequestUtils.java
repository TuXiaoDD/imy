package com.imtcp.util;

import com.imtcp.pack.MessageHeader;
import com.imtcp.pack.MessagePack;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.MemoryAttribute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestUtils {

    //解析方法，将字符串解析为一个Map结构
    public static Map<String, String> getRequestParams(FullHttpRequest request) {
        HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(false), request);
        List<InterfaceHttpData> httpPostData = decoder.getBodyHttpDatas();
        Map<String, String> params = new HashMap<>();

        for (InterfaceHttpData data : httpPostData) {
            if (data.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
                MemoryAttribute attribute = (MemoryAttribute) data;
                params.put(attribute.getName(), attribute.getValue());
            }
        }
        return params;
    }

    /**
     * 约定请求头中带上MessageHeader属性值
     * body或parameter中带入具体请求参数
     *
     * @param request
     * @return
     */
    public static MessagePack toMessagePack(FullHttpRequest request) {
        HttpHeaders headers = request.headers();
        if (headers == null) throw new RuntimeException("请求头为空");
        try {
            // todo 先全部拦截，有些可能会为空
            Integer appId = Integer.parseInt(headers.get("appId"));
            Integer version = Integer.parseInt(headers.get("version"));
            Integer command = Integer.parseInt(headers.get("command"));
            Integer clientType = Integer.parseInt(headers.get("clientType"));
            Integer codeType = Integer.parseInt(headers.get("codeType"));
            int length = Integer.parseInt(headers.get("length"));
            MessageHeader header = new MessageHeader();
            header.setVersion(version);
            header.setAppId(appId);
            header.setCommand(command);
            header.setClientType(clientType);
            header.setCodeType(codeType);
            header.setLength(length);
            MessagePack pack = new MessagePack();
            pack.setHeader(header);

            // todo 暂时先塞回去
            pack.setRequest(request);
            return pack;
        } catch (Exception e) {
            throw new RuntimeException("请求参数不合法");
        }

    }

}
