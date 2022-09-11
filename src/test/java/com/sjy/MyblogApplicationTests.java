package com.sjy;

import com.sjy.dao.BlogDao;
import com.sjy.dao.MicroBlogDao;
import com.sjy.dao.UserDao;
import com.sjy.entity.MicroBlog;
import com.sjy.service.BlogService;
import com.sjy.util.RedisUtils;
import com.sjy.util.Sendhttp;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.standard.expression.Each;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

@SpringBootTest
class MyblogApplicationTests {

}
