package com.txxw.hr.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.txxw.hr.dao.mapper.NationMapper;
import com.txxw.hr.dao.mapper.PositionMapper;
import com.txxw.hr.dao.pojo.Jobseeker;
import com.txxw.hr.dao.mapper.JobseekerMapper;
import com.txxw.hr.service.IJobseekerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.txxw.hr.service.IPositionService;
import com.txxw.hr.vo.JobseekerVo;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.ResultPage;
import com.txxw.hr.vo.params.JobSeekerPageParam;
import com.txxw.hr.vo.params.JobseekerParam;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Enci
 * @since 2023-08-30
 */
@Service
public class JobseekerServiceImpl extends ServiceImpl<JobseekerMapper, Jobseeker> implements IJobseekerService {

    @Autowired
    private JobseekerMapper jobseekerMapper;
    @Autowired
    private NationMapper nationMapper;
    @Autowired
    private PositionMapper positionMapper;



    @Override
    public Result listJobSeeker(JobSeekerPageParam jobSeekerPageParam) {
        Page<Jobseeker> page = new Page<>(jobSeekerPageParam.getPage(),jobSeekerPageParam.getPageSize());
        IPage<Jobseeker> jobseekerIPage = jobseekerMapper.listJobseeker(
                page,
//                jobSeekerPageParam.getAge(),
                jobSeekerPageParam.getGender(),
                jobSeekerPageParam.getPosId(),
                //jobSeekerPageParam.getExpectSalary(),薪资范围
                jobSeekerPageParam.getTiptopDegree(),
                jobSeekerPageParam.getSource(),
                jobSeekerPageParam.getType()
                );
        List<Jobseeker> records = jobseekerIPage.getRecords();
        long total = jobseekerIPage.getTotal();
        ResultPage resultPage = new ResultPage();
        resultPage.setData(copyList(records));
        resultPage.setTotal(total);
        return Result.success(resultPage);
    }

    @Override
    public JobseekerVo findJobSeekerById(Long jobseekerId) {
        Jobseeker jobseeker = jobseekerMapper.selectById(jobseekerId);
        return copy(jobseeker);
    }

    private List<JobseekerVo> copyList(List<Jobseeker> records) {
        List<JobseekerVo> jobseekerVoList = new ArrayList<>();
        for (Jobseeker record : records) {
            jobseekerVoList.add(copy(record));
        }
        return jobseekerVoList;
    }

    private JobseekerVo copy(Jobseeker jobseeker){
        JobseekerVo jobseekerVo = new JobseekerVo();
        jobseekerVo.setId(String.valueOf(jobseeker.getId()));
        BeanUtils.copyProperties(jobseeker,jobseekerVo);

        jobseekerVo.setSendTime(new DateTime(jobseeker.getSendTime()).toString("yyyy-MM-dd HH:mm"));
        //并不是所有的接口 都需要标签 ，作者信息

        //映射职位信息
        Long posId = jobseeker.getPosId();
        jobseekerVo.setPos(positionMapper.selectById(posId).getName());

        //映射民族信息
        Long nationId = jobseeker.getNationId();
        jobseekerVo.setNation(nationMapper.selectById(nationId).getName());

        return jobseekerVo;
    }

    /**
     * 添加求职者基本信息
     * @param jobseekerParam
     * @return
     */
    @Override
    public Long addJobSeeker(JobseekerParam jobseekerParam) {

        Jobseeker jobseeker = new Jobseeker();
        BeanUtils.copyProperties(jobseekerParam,jobseeker);


        jobseeker.setSendTime(System.currentTimeMillis());
        //设置求职状态为待定
        jobseeker.setStatus(2);
        //如果点击提交按钮则这里是1
        jobseeker.setSubmit(true);

        //禁止重复提交
        try {
            jobseekerMapper.insert(jobseeker);
        }catch (Exception e){
            if(e instanceof DuplicateKeyException){
                //主键异常，禁止重复提交
                return null;
            }else{
                log.error("传参异常，插入失败");
                //其他异常
                return null;
            }
        }
        //返回求职者id
        return jobseeker.getId();
    }


}
