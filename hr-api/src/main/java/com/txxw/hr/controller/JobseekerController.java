package com.txxw.hr.controller;


import com.txxw.hr.common.aop.LogAnnotation;
import com.txxw.hr.service.IJobseekerService;
import com.txxw.hr.vo.JobseekerVo;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.params.JobSeekerPageParam;
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
    @LogAnnotation(module="求职者操作",operator="条件筛选求职者")
    @PostMapping("select/{id}")
    public Result findJobSeekerById(@PathVariable("id") Long jobseekerId){
        JobseekerVo jobseekerVo = jobseekerService.findJobSeekerById(jobseekerId);
        return Result.success(jobseekerVo);
    }

}
