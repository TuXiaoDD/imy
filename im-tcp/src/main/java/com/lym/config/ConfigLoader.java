package com.lym.config;

import com.example.common.utils.PropertiesUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

/**
 * 加载配置类
 * 优先级：配置文件 < 环境变量 < jvm < 启动参数
 */
@Slf4j
public class ConfigLoader {

    private static final String FILE_NAME = "baseConfig.properties";

    private BaseConfig baseConfig = new BaseConfig();

    private static final ConfigLoader INSTANTS = new ConfigLoader();

    private ConfigLoader() {
    }

    public static ConfigLoader getInstants() {
        return INSTANTS;
    }

    public BaseConfig getBaseConfig() {
        return INSTANTS.baseConfig;
    }


    public BaseConfig load(String[] args) {
        {
            // 配置文件
            InputStream stream = ConfigLoader.class.getClassLoader().getResourceAsStream(FILE_NAME);
            Properties properties = new Properties();
            try {
                properties.load(stream);
                PropertiesUtils.properties2Object(properties, baseConfig);
            } catch (IOException e) {

            }
        }

        {
            // 环境变量
            Map<String, String> envMap = System.getenv();
            if (envMap != null && envMap.keySet().size() > 0) {
                Properties properties = new Properties();
                properties.putAll(envMap);
                PropertiesUtils.properties2Object(properties, baseConfig);
            }
        }

        {
            // jvm
            Properties properties = System.getProperties();
            if (properties.size() > 0) {
                PropertiesUtils.properties2Object(properties, baseConfig);
            }
        }

        {
            // 启动参数： --aa=bb
            if (args != null && args.length > 0) {
                Properties properties = new Properties();
                Arrays.stream(args).forEach(arg -> {
                    String[] split = arg.split("=");
                    String key = split[0].replace("--", "");
                    properties.put(key, split[1]);
                });
                PropertiesUtils.properties2Object(properties, baseConfig);
            }
        }

        return baseConfig;
    }


}
