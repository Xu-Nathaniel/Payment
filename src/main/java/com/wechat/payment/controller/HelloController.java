package com.wechat.payment.controller;


import com.wechat.payment.controller.resp.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hello")
@Api(tags = "hello")
@Validated
public class HelloController {


    @ApiOperation("hello")
    @GetMapping
    public ApiResponse<String> hello() {
        return ApiResponse.ok("hello");
    }



//    BaiDuService service = ProxyBuilder.newBuilder()
//            .proxyInterface(BaiDuService.class)
//            .build();
//    String main = service.main();
//    System.out.println(main);
//
//    @Rest("https://www.baidu.com")
//    public interface BaiDuService {
//        @Get
//         String main();
//    }

}


