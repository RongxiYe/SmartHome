package com.group.smarthome;

import com.group.smarthome.utils.MD5Crypto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;


@SpringBootTest
class SmartHomeApplicationTests {

    @Test
    void contextLoads() {
        String str = "111";
        System.out.println(MD5Crypto.encrypt(str));
    }

}
