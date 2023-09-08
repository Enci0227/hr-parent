package com.txxw.hr.controller;

import com.txxw.hr.common.aop.LogAnnotation;
import com.txxw.hr.service.TestService;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.params.TestParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/8/10
 * @description:测试控制类
 **/
//json数据进行交互

@Api(description = "测试模块") //修饰整个类，描述Controller的作用
@RestController
//@RequestMapping("test")
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @ApiOperation("测试1")
    @PostMapping("test1")
    public Result test(){
        return Result.success("SUCCESS");
    }

    @ApiOperation("测试2")
    @PostMapping ("test2/{id}")
    public Result test2(@PathVariable("id") Long id){
        return testService.findOneById(id);
    }

    @ApiOperation("测试3")
    @ApiImplicitParams(
        {
            @ApiImplicitParam(
                    name = "id",
                    value = "用户id",
                    required = true,
                    dataType = "long",
                    paramType = "query"
            )
        }
    )
    @PostMapping ("test3")
    public Result test3(@RequestParam(value = "id",required = false) Long id){

        return testService.findById(id);
    }

    @ApiOperation("测试4")
    @PostMapping ("test4")
    //加上此注解 代表要对此接口记录日志
    @LogAnnotation(module="测试",operator="测试项目4")
    public Result test4(@RequestBody TestParam testParam){
        return Result.success(testParam);
    }

    @GetMapping("/employee/basic/hello")
    public String hello2(){
        return "/employee/basic/hello";
    }

    @GetMapping("/employee/advanced/hello")
    public String hello3(){
        return "/employee/advanced/hello";
    }

    @ApiOperation("测试remote")
    @GetMapping(value = "/remote")
    public Result remote(){
        return Result.success("8083远程请求发送成功");
    }

}
