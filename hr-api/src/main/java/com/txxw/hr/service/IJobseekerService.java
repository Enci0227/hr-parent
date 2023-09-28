package com.txxw.hr.service;

import com.txxw.hr.dao.pojo.Jobseeker;
import com.baomidou.mybatisplus.extension.service.IService;
import com.txxw.hr.vo.JobseekerVo;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.params.AddBlackParam;
import com.txxw.hr.vo.params.JobSeekerPageParam;
import com.txxw.hr.vo.params.JobseekerParam;
import com.txxw.hr.vo.params.UserStatusParam;

import java.time.LocalDate;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Enci
 * @since 2023-08-30
 */
public interface IJobseekerService extends IService<Jobseeker> {

    /**
     * 添加求职者基本信息
     * @param jobseekerParam
     * @return
     */
    Map<String, String> addJobSeeker(JobseekerParam jobseekerParam);

    /**
     * 条件筛选求职者
     * @param jobSeekerPageParam
     * @return
     */
    Result listJobSeeker(JobSeekerPageParam jobSeekerPageParam);

    /**
     * 根据id查找求职者基本信息
     * @param jobseekerId
     * @return
     */
    JobseekerVo findJobSeekerById(Long jobseekerId);

    /**
     * 添加黑名单
     * @param addBlackParam
     * @return
     */
    Result addBlackByJobseekerId(AddBlackParam addBlackParam);

    /**
     * 获取某个用户提交次数及时间信息
     * @param jobseekerId
     * @return
     */
    Result getDeliverInfoByJobseekerId(Long jobseekerId);

    /**
     * 设置求职者状态
     * @param userStatusParam
     * @return
     */
    Result updateStatusByJobseekerId(UserStatusParam userStatusParam);

    /**
     * 条件查询求职者基本信息及面试信息
     * @param jobSeekerPageParam
     * @return
     */
    Result listAllJobSeeker(JobSeekerPageParam jobSeekerPageParam);

    /**
     * 操作员添加求职者信息
     * @param jobseekerParam
     * @return
     */
    Result addJobSeekerByOperator(JobseekerParam jobseekerParam);

    /**
     * 根据邮箱或电话即时响应求职者信息
     * @param jobseekerParam
     * @return
     */
    Result responseByMailAndPhone(JobseekerParam jobseekerParam);

    /**
     * 查询从某个日期开始收到的有效简历数量及通过数
     * @param date
     * @return
     */
    Result getResumeCountByTime(LocalDate date);

    /**
     * 更新求职者基本信息
     * @param jobseekerParam
     * @return
     */
    Result updateJobseekerBaseinfo(JobseekerParam jobseekerParam);
}
