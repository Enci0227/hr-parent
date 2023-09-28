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

    @ApiOperation(value = "求职者提交天巡十问问卷作答",notes = "注：不填的基本信息之后无法按此条件筛选，另外：为保证数据库完整性以及后续的条件筛选：除姓名、手机号、邮箱必传外，职位、应聘来源、应聘类型、学历、工作经验也必传，且此字段只能填写固定的几个选择内容(不允许传空字符串)，参见十问问卷的单选内容；" +
            "请做好输入框的规则校验（校验不通过则不允许调用此接口），如因莫名其妙的数据插入（按道理不可能插入成功）可能导致数据库完整性被破坏形成难以排除的系统异常")
    @PostMapping("submit")
    public Result submitTxswAnswer(@RequestBody @ApiParam(required = true) TxswAnswerParam txswAnswerParam){
        if(txswAnswerParam.getJobseekerParam().getPosId()!=null){
            if (txswAnswerParam.getJobseekerParam().getPosId()==0){
                return Result.fail(60000,"职位Id请不要传0，可以不传，数据库没有id为0的职位数据");
            }
        }
        if(txswAnswerParam.getJobseekerParam().getNationId()!=null){
            if (txswAnswerParam.getJobseekerParam().getNationId()==0){
                return Result.fail(60000,"民族Id请不要传0，可以不传，数据库没有id为0的民族数据");
            }
        }
        if(txswAnswerParam.getJobseekerParam().getPoliticId()!=null){
            if (txswAnswerParam.getJobseekerParam().getPoliticId()==0){
                return Result.fail(60000,"政治面貌Id请不要传0，可以不传，数据库没有id为0的政治面貌数据");
            }
        }
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
