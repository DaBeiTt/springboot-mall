package org.hsiaomartin.springbootmall.controller;

import org.hsiaomartin.springbootmall.dto.CreateOrderRequest;
import org.hsiaomartin.springbootmall.dto.ShoppingCart;
import org.hsiaomartin.springbootmall.service.ShoppingCartService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingCartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingCartService shoppingCartService;

    @MockBean
    private CreateOrderRequest createOrderRequest;

    @Test
    public void getCart() throws Exception {

        when(shoppingCartService.getCart()).thenReturn(new ShoppingCart());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/cart");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(model().attributeExists("shoppingCart", "createOrderRequest"))
                .andExpect(view().name("order/cart"));
    }

    @Test
    public void addCartItem_Success() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/cart/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("productId", "1")
                .param("quantity", "3");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/cart"));
    }

    @Test
    public void addCartItem_OutOfStock() throws Exception {

        Mockito.doThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST))
                .when(shoppingCartService)
                .addCartItem(Mockito.any());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/cart/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("productId", "1")
                .param("quantity", "10000");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(view().name("message/error"));
    }
}