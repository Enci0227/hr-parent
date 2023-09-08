package com.txxw.hr.service;

import com.txxw.hr.dao.pojo.Jobseeker;
import com.baomidou.mybatisplus.extension.service.IService;
import com.txxw.hr.vo.JobseekerVo;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.params.JobSeekerPageParam;
import com.txxw.hr.vo.params.JobseekerParam;

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
    Long addJobSeeker(JobseekerParam jobseekerParam);

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
}
