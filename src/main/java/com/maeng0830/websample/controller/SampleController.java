package com.maeng0830.websample.controller;

import com.maeng0830.websample.exception.ErrorCode;
import com.maeng0830.websample.exception.WebSampleException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@RestController
public class SampleController {
    // GET, POST 등 요청 방식을 직접 지정
    // RequestMapping이 아닌, 요즘에는 축약형 매핑 어노테이션도 많이 사용한다.
//    @RequestMapping(value = "/order/1", method = RequestMethod.GET)
//    public String getOrder() {
//        log.info("Get some order");
//        return "orderId:1, orderAmount:1000";
//    }

    // 축약형 매핑
    // with PathVariable
    @GetMapping("/order/{orderId}")
    public String getOrder(@PathVariable("orderId") String id) throws IllegalAccessException, SQLIntegrityConstraintViolationException {
        log.info("Get some order : " + id);

        if ("500".equals(id)) {
            throw new WebSampleException(
                    ErrorCode.TOO_BIG_ID_ERROR,
                    "500 is too big orderId.");
        }

        if ("3".equals(id)) {
            throw new WebSampleException(
                    ErrorCode.TOO_SMALL_ID_ERROR,
                    "3 is too small orderId.");
        }

        if ("4".equals(id)) {
            throw new SQLIntegrityConstraintViolationException(
                    "Duplicated insertion was tried.");
        }

        return "orderId:" + id + "," + "orderAmount:" + 1000;
    }

    // 축약형 매핑
    // with PathVariable
    @DeleteMapping("/order/{orderId}")
    public String deleteOrder(@PathVariable("orderId") String id) {
        log.info("Delete some order : " + id);
        return "Delete orderId:" + id;
    }

    // 축약형 매핑
    // with RequestParam
    @GetMapping("/order")
    public String getOrderWithRequestParam(
            @RequestParam(value = "orderId", required = false, defaultValue = "defaultId") String id,
            @RequestParam("orderAmount") Integer amount) {
        log.info("Get some order : " + id + ", amount : " + amount);
        return "orderId:" + id + "," + "orderAmount:" + amount;
    }

    // 축약형 매핑
    @PutMapping("/order")
    public String createOrder() {
        log.info("Create order");
        return "order created -> orderId:1, orderAmount:1000";
    }

    // 축약형 매핑
    @PostMapping("/order")
    public String createOrder(
            @RequestBody CreateOrderRequest createOrderRequest,
            @RequestHeader String userAccountId) {
        log.info("Create some order : " + createOrderRequest + ", userAccountId : " + userAccountId);
        return "orderId:" + createOrderRequest.getOrderId() + "," + "orderAmount:" + createOrderRequest.getOrderAmount();
    }

    @Data // 자바 빈 객체로 생성하는 어노테이션
    public static class CreateOrderRequest {
        private String orderId;
        private Integer orderAmount;
    }
}
