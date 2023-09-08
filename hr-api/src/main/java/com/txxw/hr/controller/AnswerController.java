package com.txxw.hr.controller;


import com.txxw.hr.dao.pojo.Annexurl;
import com.txxw.hr.dao.pojo.Answer;
import com.txxw.hr.service.IAnswerService;
import com.txxw.hr.vo.AnswerVo;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.params.TxswAnswerParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Enci
 * @since 2023-08-31
 */
@Api(tags = "天巡十问用户作答Controller")
@RestController
@RequestMapping("/txsw")
public class AnswerController {

    @Autowired
    private IAnswerService answerService;

    @ApiOperation(value = "求职者提交天巡十问问卷作答")
    @PostMapping("submit")
    public Result submitTxswAnswer(@RequestBody @ApiParam(required = true) TxswAnswerParam txswAnswerParam){
        return answerService.submitTxswAnswer(txswAnswerParam);
    }

    @ApiOperation(value = "根据求职者Id获取十问回答")
    @PostMapping("answer/{id}")
    public Result getAnswerByJobSeekerId(@PathVariable("id") @ApiParam(required = true) Long jobSeekerId){
        List<AnswerVo> answerList = answerService.getAnswerByJobSeekerId(jobSeekerId);
        return Result.success(answerList);
    }

    @ApiOperation(value = "根据求职者Id获取简历或作品集")
    @PostMapping("annex/{id}")
    public Result getAnnexByJobSeekerId(@PathVariable("id") @ApiParam(required = true) Long jobSeekerId){
        Annexurl annexurl = answerService.getAnnexByJobSeekerId(jobSeekerId);
        return Result.success(annexurl);
    }

    @ApiOperation(value = "查询求职者天巡十问问卷作答")
    @PostMapping("view/{id}")
    public Result findAnswerFormByJobseekerId(@PathVariable("id") @ApiParam(value = "求职者id", required = true) Long jobseekerId){
        return answerService.findAnswerFormByJobseekerId(jobseekerId);
    }

}
