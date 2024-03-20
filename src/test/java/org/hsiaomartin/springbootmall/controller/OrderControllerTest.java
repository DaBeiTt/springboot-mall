package org.hsiaomartin.springbootmall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hsiaomartin.springbootmall.dao.UserDao;
import org.hsiaomartin.springbootmall.model.Order;
import org.hsiaomartin.springbootmall.util.Page;
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
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserDao userDao;

    private ObjectMapper objectMapper = new ObjectMapper();

    // 創建訂單
    @Transactional
    @Test
    public void createOrder_success() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/{userId}/orders", 1)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("buyItemList[0].productId", "1")
                .param("buyItemList[0].quantity", "5")
                .param("buyItemList[1].productId", "2")
                .param("buyItemList[1].quantity", "2");

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(model().attributeExists("success"))
                .andExpect(view().name("message/success"))
                .andReturn();

        SuccessObject successObject = (SuccessObject) mvcResult.getModelAndView().getModel().get("success");
        assertEquals("createOrder", successObject.getEvent());
        assertEquals("訂單新增成功!", successObject.getMessage());
    }

    @Transactional
    @Test
    public void createOrder_illegalArgument_emptyBuyItemList() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/{userId}/orders", 1)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Transactional
    @Test
    public void createOrder_userNotExist() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/{userId}/orders", 100)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("buyItemList[0].productId", "1")
                .param("buyItemList[0].quantity", "5");

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(view().name("message/error"))
                .andReturn();

        String errorMessage = (String) mvcResult.getModelAndView().getModel().get("errorMessage");
        assertEquals("該使用者不存在!", errorMessage);
    }

    @Transactional
    @Test
    public void createOrder_productNotExist() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/{userId}/orders", 1)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("buyItemList[0].productId", "100")
                .param("buyItemList[0].quantity", "1");

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(view().name("message/error"))
                .andReturn();

        String errorMessage = (String) mvcResult.getModelAndView().getModel().get("errorMessage");
        assertEquals("該商品不存在!", errorMessage);
    }

    @Transactional
    @Test
    public void createOrder_stockNotEnough() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/{userId}/orders", 1)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("buyItemList[0].productId", "1")
                .param("buyItemList[0].quantity", "100");

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(view().name("message/error"))
                .andReturn();

        String errorMessage = (String) mvcResult.getModelAndView().getModel().get("errorMessage");
        assertEquals("該商品庫存不足!", errorMessage);
    }

    // 查詢訂單列表
    @Test
    public void getOrders() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/users/{userId}/orders", 1)
                .with(request -> {
                    request.setAttribute("userLogin", userDao.getUserById(1));
                    return request;
                });

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(model().attributeExists("orderPage"))
                .andExpect(view().name("order/userOrder"))
                .andReturn();

        Page<Order> page = (Page) mvcResult.getModelAndView().getModel().get("orderPage");
        assertEquals(5, page.getLimit());
        assertEquals(0, page.getOffset());
        assertNotNull(page.getResults());
    }

    @Test
    public void getOrders_pagination() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/users/{userId}/orders", 1)
                .param("limit", "2")
                .param("offset", "2")
                .with(request -> {
                    request.setAttribute("userLogin", userDao.getUserById(1));
                    return request;
                });

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(model().attributeExists("orderPage"))
                .andExpect(view().name("order/userOrder"))
                .andReturn();

        Page<Order> page = (Page) mvcResult.getModelAndView().getModel().get("orderPage");
        assertEquals(2, page.getLimit());
        assertEquals(2, page.getOffset());
        assertNotNull(page.getResults());
    }

    @Test
    public void getOrders_userHasNoOrder() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/users/{userId}/orders", 3)
                .with(request -> {
                    request.setAttribute("userLogin", userDao.getUserById(3));
                    return request;
                });

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(model().attributeExists("orderPage"))
                .andExpect(view().name("order/userOrder"))
                .andReturn();

        Page<Order> page = (Page) mvcResult.getModelAndView().getModel().get("orderPage");
        assertEquals(5, page.getLimit());
        assertEquals(0, page.getOffset());
        assertEquals(0, page.getResults().size());
    }

    @Test
    public void getOrders_userNotExist() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/users/{userId}/orders", 100)
                .with(request -> {
                    request.setAttribute("userLogin", userDao.getUserById(100));
                    return request;
                });

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(view().name("message/error"))
                .andReturn();

        String errorMessage = (String) mvcResult.getModelAndView().getModel().get("errorMessage");
        assertEquals("該使用者不存在!", errorMessage);
    }
}