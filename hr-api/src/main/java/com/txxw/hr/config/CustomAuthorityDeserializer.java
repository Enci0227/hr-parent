package com.txxw.hr.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 自定义Authority解析器
 * 反序列化
 * 用于更新用户信息时Authority属性的反序列化
 */
public class CustomAuthorityDeserializer extends JsonDeserializer {
    @Override
    public Object deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode jsonNode = mapper.readTree(p);//读取解析对象，拿到jsonNode
        List<GrantedAuthority> grantedAuthorities = new LinkedList<>();
        Iterator<JsonNode> elements = jsonNode.elements();//从jsonNode获取对应元素
        while (elements.hasNext()){
            JsonNode next = elements.next();
            JsonNode authority = next.get("authority");
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.asText()));//拿到之后放入SimpleGrantedAuthority对象里面
        }

        return grantedAuthorities;
    }
}
