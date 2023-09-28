package com.txxw.hr.controller;


import com.txxw.hr.common.aop.LogAnnotation;
import com.txxw.hr.service.IJobseekerService;
import com.txxw.hr.vo.JobseekerVo;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.params.AddBlackParam;
import com.txxw.hr.vo.params.JobSeekerPageParam;
import com.txxw.hr.vo.params.JobseekerParam;
import com.txxw.hr.vo.params.UserStatusParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * <p>
 *  前端控制器,求职者-基本信息
 * </p>
 *
 * @author Enci
 * @since 2023-08-30
 */
@Api(tags = "求职者基本信息操作Controller")
@RestController
@RequestMapping("/jobseeker")
public class JobseekerController {

    @Autowired
    private IJobseekerService jobseekerService;

    @ApiOperation(value = "条件查询求职者基本信息", notes = "注意：此接口只能查出求职者基本信息；")
    @LogAnnotation(module="求职者操作",operator="条件筛选求职者")
    @PostMapping("list")
    public Result listJobSeeker(@RequestBody JobSeekerPageParam jobSeekerPageParam){
        return jobseekerService.listJobSeeker(jobSeekerPageParam);
    }

    @ApiOperation(value = "条件查询求职者基本信息及面试信息",notes = "注意：此接口只能查出非黑名单且已通过简历筛选的求职者基本信息及其对应面试信息；(无视isBlack及status的传参)")
    @LogAnnotation(module="求职者操作",operator="条件查询求职者基本信息及面试信息")
    @PostMapping("listall")
    public Result listAllJobSeeker(@RequestBody JobSeekerPageParam jobSeekerPageParam){
        return jobseekerService.listAllJobSeeker(jobSeekerPageParam);
    }

    @ApiOperation(value = "根据id查询求职者基本信息")
    @LogAnnotation(module="求职者操作",operator="根据id查询求职者基本信息")
    @GetMapping("select/{id}")
    public Result findJobSeekerById(@PathVariable("id") Long jobseekerId){
        JobseekerVo jobseekerVo = jobseekerService.findJobSeekerById(jobseekerId);
        return Result.success(jobseekerVo);
    }

    @ApiOperation(value = "添加黑名单/拉黑求职者")
    @LogAnnotation(module="求职者操作",operator="添加黑名单拉黑求职者")
    @PostMapping("addblack")
    public Result addBlackByJobseekerId(@RequestBody AddBlackParam addBlackParam){
        return jobseekerService.addBlackByJobseekerId(addBlackParam);
    }

    @ApiOperation(value = "根据id查询求职者的重复投递信息")
    @LogAnnotation(module="求职者操作",operator="根据id查询求职者的重复投递信息")
    @PostMapping("deliverinfo/{id}")
    public Result getDeliverInfoByJobseekerId(@PathVariable("id") Long jobseekerId){
        return jobseekerService.getDeliverInfoByJobseekerId(jobseekerId);
    }

    @ApiOperation(value = "更新求职者状态")
    @LogAnnotation(module="求职者操作",operator="更新求职者状态")
    @PutMapping("status")
    public Result updateStatusByJobseekerId(@RequestBody UserStatusParam userStatusParam){
        return jobseekerService.updateStatusByJobseekerId(userStatusParam);
    }

    @ApiOperation(value = "添加求职者人员/新增人员",
            notes = "(注意：邮箱及电话必传唯一确定求职者；新增人员界面固定则此接口的民族/职位/政治面貌参数需传对应id,不再提供数据映射,也可以不传；应聘来源、应聘类型、学历、工作经验必传，且此字段只能填写固定的几个选择内容(不允许传空字符串)，参见十问问卷的单选内容；)")
    @LogAnnotation(module="求职者操作",operator="添加求职者人员")
    @PostMapping("add")
    public Result addJobseekerByOperator(@RequestBody JobseekerParam jobseekerParam){
        if(jobseekerParam.getPosId()!=null){
            if (jobseekerParam.getPosId()==0){
                return Result.fail(60000,"职位Id请不要传0，数据库没有id为0的职位数据");
            }
        }else {
            return Result.fail(60000,"职位Id不能为空");
        }
        if(jobseekerParam.getNationId()!=null){
            if (jobseekerParam.getNationId()==0){
                return Result.fail(60000,"民族Id请不要传0，可以为空，数据库没有id为0的民族数据");
            }
        }
        if(jobseekerParam.getPoliticId()!=null){
            if (jobseekerParam.getPoliticId()==0){
                return Result.fail(60000,"政治面貌Id请不要传0，可以为空，数据库没有id为0的政治面貌数据");
            }
        }
        return jobseekerService.addJobSeekerByOperator(jobseekerParam);
    }

    @ApiOperation(value = "根据求职者姓名邮箱和手机号即时反馈信息")
    @LogAnnotation(module="求职者操作",operator="根据求职者姓名邮箱和手机号即时反馈信息")
    @PostMapping("response")
    public Result responseByMailAndPhone(@RequestBody JobseekerParam jobseekerParam){
        return jobseekerService.responseByMailAndPhone(jobseekerParam);
    }

    @ApiOperation(value = "查询从某个日期开始收到的简历数量")
    @LogAnnotation(module="求职者操作",operator="查询从某个日期开始收到的简历数量")
    @GetMapping("resumecount")
    public Result getResumeCountByTime(LocalDate date){
        return jobseekerService.getResumeCountByTime(date);
    }

    @ApiOperation(value = "编辑/修改求职者基本信息")
    @LogAnnotation(module="求职者操作",operator="编辑求职者基本信息")
    @PostMapping("update/info")
    public Result updateJobseekerBaseinfo(@RequestBody JobseekerParam jobseekerParam){
        return jobseekerService.updateJobseekerBaseinfo(jobseekerParam);
    }


}
