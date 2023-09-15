package com.txxw.hr.controller;


import com.txxw.hr.common.aop.LogAnnotation;
import com.txxw.hr.service.IJobseekerService;
import com.txxw.hr.vo.JobseekerVo;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.params.AddBlackParam;
import com.txxw.hr.vo.params.JobSeekerPageParam;
import com.txxw.hr.vo.params.UserStatusParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器,求职者-基本信息
 * </p>
 *
 * @author Enci
 * @since 2023-08-30
 */
@Api(tags = "求职者基本信息Controller")
@RestController
@RequestMapping("/jobseeker")
public class JobseekerController {

    @Autowired
    private IJobseekerService jobseekerService;

    @ApiOperation(value = "条件查询所有求职者基本信息")
    @LogAnnotation(module="求职者操作",operator="条件筛选求职者")
    @PostMapping("list")
    public Result listJobSeeker(@RequestBody JobSeekerPageParam jobSeekerPageParam){
        return jobseekerService.listJobSeeker(jobSeekerPageParam);
    }

    @ApiOperation(value = "根据id查询求职者基本信息")
    @LogAnnotation(module="求职者操作",operator="根据id查询求职者基本信息")
    @PostMapping("select/{id}")
    public Result findJobSeekerById(@PathVariable("id") Long jobseekerId){
        JobseekerVo jobseekerVo = jobseekerService.findJobSeekerById(jobseekerId);
        return Result.success(jobseekerVo);
    }

    @ApiOperation(value = "添加黑名单拉黑求职者")
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
    @PostMapping("status")
    public Result updateStatusByJobseekerId(@RequestBody UserStatusParam userStatusParam){
        return jobseekerService.updateStatusByJobseekerId(userStatusParam);
    }


}
