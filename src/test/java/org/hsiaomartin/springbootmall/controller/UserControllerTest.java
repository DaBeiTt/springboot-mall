package org.hsiaomartin.springbootmall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hsiaomartin.springbootmall.dao.UserDao;
import org.hsiaomartin.springbootmall.dto.UserLoginRequest;
import org.hsiaomartin.springbootmall.dto.UserRegisterRequest;
import org.hsiaomartin.springbootmall.model.User;
import org.hsiaomartin.springbootmall.util.SuccessObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserDao userDao;

    private ObjectMapper objectMapper = new ObjectMapper();

    // 註冊新帳號
    @Test
    public void register_success() throws Exception {

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("test1@gmail.com");
        userRegisterRequest.setPassword("test1");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", userRegisterRequest.getEmail())
                .param("password", userRegisterRequest.getPassword());

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(model().attributeExists("success"))
                .andExpect(status().is(200))
                .andExpect(view().name("message/success"))
                .andReturn();

        SuccessObject successObject = (SuccessObject) mvcResult.getModelAndView().getModel().get("success");
        assertEquals("register", successObject.getEvent());
        assertEquals("註冊成功！", successObject.getMessage());

        // 檢查資料庫中的密碼不為明碼
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
        assertNotEquals(userRegisterRequest.getPassword(), user.getPassword());
    }

    @Test
    public void register_invalidEmailFormat() throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("3gd8e7q34l9");
        userRegisterRequest.setPassword("123");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", userRegisterRequest.getEmail())
                .param("password", userRegisterRequest.getPassword());

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Test
    public void register_emailAlreadyExist() throws Exception {
        // 先註冊一個帳號
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("test2@gmail.com");
        userRegisterRequest.setPassword("123");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", userRegisterRequest.getEmail())
                .param("password", userRegisterRequest.getPassword());

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andReturn();

        SuccessObject successObject = (SuccessObject) mvcResult.getModelAndView().getModel().get("success");
        assertEquals("register", successObject.getEvent());
        assertEquals("註冊成功！", successObject.getMessage());

        // 再次使用同個 email 註冊
        mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(view().name("message/error"))
                .andReturn();

        String errorMessage = (String) mvcResult.getModelAndView().getModel().get("errorMessage");
        assertEquals("此郵件已被註冊 !", errorMessage);
    }

    // 登入
    @Test
    public void login_success() throws Exception {
        // 先註冊新帳號
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("test3@gmail.com");
        userRegisterRequest.setPassword("123");

        register(userRegisterRequest);

        // 再測試登入功能
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setEmail(userRegisterRequest.getEmail());
        userLoginRequest.setPassword(userRegisterRequest.getPassword());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", userLoginRequest.getEmail())
                .param("password", userLoginRequest.getPassword());

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/products"));

    }

    @Test
    public void login_wrongPassword() throws Exception {
        // 先註冊新帳號
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("test4@gmail.com");
        userRegisterRequest.setPassword("123");

        register(userRegisterRequest);

        // 測試密碼輸入錯誤的情況
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setEmail(userRegisterRequest.getEmail());
        userLoginRequest.setPassword("unknown");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", userLoginRequest.getEmail())
                .param("password", userLoginRequest.getPassword());

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(view().name("message/error"))
                .andReturn();

        String errorMessage = (String) mvcResult.getModelAndView().getModel().get("errorMessage");
        assertEquals("郵件地址或密碼錯誤 !", errorMessage);
    }

    @Test
    public void login_invalidEmailFormat() throws Exception {
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setEmail("hkbudsr324");
        userLoginRequest.setPassword("123");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", userLoginRequest.getEmail())
                .param("password", userLoginRequest.getPassword());

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Test
    public void login_emailNotExist() throws Exception {
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setEmail("unknown@gmail.com");
        userLoginRequest.setPassword("123");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", userLoginRequest.getEmail())
                .param("password", userLoginRequest.getPassword());

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(view().name("message/error"))
                .andReturn();

        String errorMessage = (String) mvcResult.getModelAndView().getModel().get("errorMessage");
        assertEquals("郵件地址或密碼錯誤 !", errorMessage);
    }

    private void register(UserRegisterRequest userRegisterRequest) throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", userRegisterRequest.getEmail())
                .param("password", userRegisterRequest.getPassword());

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }
}