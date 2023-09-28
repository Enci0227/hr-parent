package com.txxw.hr.controller;


import com.txxw.hr.dao.pojo.Infokey;
import com.txxw.hr.dao.pojo.Question;
import com.txxw.hr.service.IQuestionService;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.params.QuestionParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  天巡十问问题控制器
 * </p>
 *
 * @author Enci
 * @since 2023-08-30
 */
@Api(tags = "天巡十问问题Controller")
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private IQuestionService questionService;

    @ApiOperation(value = "更新/添加问题内容（不填id就是新建问题）")
    @PostMapping("update")
    public Result updateQuestionForm(@RequestBody @ApiParam(value = "用户更新或添加的问题参数", required = true) QuestionParam questionParam){
        return questionService.updateQuestion(questionParam);
    }

    @ApiOperation(value = "删除问题")
    @DeleteMapping("delete/{id}")
    public Result deleteQuestion(@PathVariable @ApiParam(value = "删除问题的id", required = true) Long id) {
        if (questionService.removeById(id)) {
            return Result.success("删除成功!");
        }
        return Result.fail(60003,"删除失败!");
    }

}
