package com.txxw.hr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.txxw.hr.dao.mapper.*;
import com.txxw.hr.dao.pojo.*;
import com.txxw.hr.service.IAnswerService;
import com.txxw.hr.service.IJobseekerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.txxw.hr.service.INationService;
import com.txxw.hr.service.IPositionService;
import com.txxw.hr.utils.AdminUtils;
import com.txxw.hr.vo.*;
import com.txxw.hr.vo.params.AddBlackParam;
import com.txxw.hr.vo.params.JobSeekerPageParam;
import com.txxw.hr.vo.params.JobseekerParam;
import com.txxw.hr.vo.params.UserStatusParam;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Enci
 * @since 2023-08-30
 */
@Service
public class JobseekerServiceImpl extends ServiceImpl<JobseekerMapper, Jobseeker> implements IJobseekerService {


    @Autowired
    private IAnswerService answerService;
    @Autowired
    private JobseekerMapper jobseekerMapper;
    @Autowired
    private NationMapper nationMapper;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private DeliverMapper deliverMapper;
    @Autowired
    private PoliticsStatusMapper politicsStatusMapper;
    @Autowired
    private InterviewMapper interviewMapper;

    @Override
    public Result listJobSeeker(JobSeekerPageParam jobSeekerPageParam) {
        Page<Jobseeker> page = new Page<>(jobSeekerPageParam.getPage(), jobSeekerPageParam.getPageSize());
        IPage<Jobseeker> jobseekerIPage = jobseekerMapper.listJobseeker(
                page,
                jobSeekerPageParam.getName(),
                jobSeekerPageParam.getAge(),
                jobSeekerPageParam.getGender(),
                jobSeekerPageParam.getPosId(),
                jobSeekerPageParam.getExpectSalary(),//薪资范围
                jobSeekerPageParam.getTiptopDegree(),
                jobSeekerPageParam.getSource(),
                jobSeekerPageParam.getType(),
                jobSeekerPageParam.getIsBlack(),
                jobSeekerPageParam.getSpecificAge(),
                jobSeekerPageParam.getPhone(),
                jobSeekerPageParam.getStatus()
        );
        List<Jobseeker> records = jobseekerIPage.getRecords();
        long total = jobseekerIPage.getTotal();
        ResultPage resultPage = new ResultPage();
        resultPage.setData(copyList(records, false));
        resultPage.setTotal(total);
        return Result.success(resultPage);
    }

    @Override
    public JobseekerVo findJobSeekerById(Long jobseekerId) {
        Jobseeker jobseeker = jobseekerMapper.selectById(jobseekerId);
        return copy(jobseeker, false);
    }

    @Override
    public Result addBlackByJobseekerId(AddBlackParam addBlackParam) {
        Jobseeker jobseeker = new Jobseeker();
        jobseeker.setIsBlack(true);
        jobseeker.setId(addBlackParam.getJobseekerId());
        jobseeker.setBlackreason(addBlackParam.getBlackreason());
        jobseekerMapper.updateById(jobseeker);
        return Result.success("已拉黑该求职者！");
    }

    //这个函数还映射了面试信息
    private List<JobseekerVo> copyList(List<Jobseeker> records, Boolean isInterview) {
        List<JobseekerVo> jobseekerVoList = new ArrayList<>();
        for (Jobseeker record : records) {
            jobseekerVoList.add(copy(record, isInterview));
        }
        return jobseekerVoList;
    }

    //这个函数还映射了面试信息
    private JobseekerVo copy(Jobseeker jobseeker, Boolean isInterview) {
        JobseekerVo jobseekerVo = new JobseekerVo();
        //首先映射基本信息
        jobseekerVo.setId(String.valueOf(jobseeker.getId()));
        BeanUtils.copyProperties(jobseeker, jobseekerVo);
        jobseekerVo.setSendTime(new DateTime(jobseeker.getSendTime()).toString("yyyy-MM-dd HH:mm"));

        //映射职位信息
        Long posId = jobseeker.getPosId();
        if (posId != null && posId != 0) {
            jobseekerVo.setPos(positionMapper.selectById(posId).getName());
        } else {
            jobseekerVo.setPos("空");
        }
        //映射民族信息
        Long nationId = jobseeker.getNationId();
        if (nationId != null && nationId != 0) {
            jobseekerVo.setNation(nationMapper.selectById(nationId).getName());
        } else {
            jobseekerVo.setNation("空");
        }
        //映射政治面貌信息
        Long politicId = jobseeker.getPoliticId();
        if (politicId != null && nationId != 0) {
            jobseekerVo.setPolitic(politicsStatusMapper.selectById(politicId).getName());
        } else {
            jobseekerVo.setPolitic("空");
        }
        //如果isInterview为true，则说明需要映射面试信息
        if (isInterview) {
            LambdaQueryWrapper<Interview> interviewWrapper = new LambdaQueryWrapper<>();
            interviewWrapper.eq(Interview::getJobseekerId, jobseeker.getId());
            Interview interview = interviewMapper.selectOne(interviewWrapper);
            jobseekerVo.setInterview(interview);
        }

        return jobseekerVo;
    }

    /**
     * 添加求职者基本信息
     *
     * @param jobseekerParam
     * @return
     */
    @Override
    public Map<String, String> addJobSeeker(JobseekerParam jobseekerParam) {

        //定义一个map，用来返回结果
        Map<String, String> result = new HashMap<>();
        result.put("error", null);

        Jobseeker jobseeker = new Jobseeker();
        BeanUtils.copyProperties(jobseekerParam, jobseeker);
        if (jobseekerParam.getName() == null || jobseekerParam.getMail() == null || jobseekerParam.getPhone() == null || jobseekerParam.getPosition() == null ||
                jobseekerParam.getExperience() == null || jobseekerParam.getSource() == null || jobseekerParam.getTiptopDegree() == null ||
                jobseekerParam.getType() == null || jobseekerParam.getPosition().equals("") || jobseekerParam.getExperience().equals("") ||
                jobseekerParam.getSource().equals("") || jobseekerParam.getTiptopDegree().equals("") || jobseekerParam.getType().equals("") ||
                jobseekerParam.getName().equals("") || jobseekerParam.equals("") || jobseekerParam.getPhone().equals("")) {
            result.put("error", "姓名/邮箱/手机号/职位/应聘来源/应聘类型/学历/工作经验必传,不允许为空/空字符串");
        }

        try {
            if (jobseekerParam.getNation() != null && !jobseekerParam.getNation().equals("")) {//不为空且不为空字符串才执行
                // 民族字符串映射为id
                LambdaQueryWrapper<Nation> nationWrapper = new LambdaQueryWrapper<>();
                nationWrapper.eq(Nation::getName, jobseekerParam.getNation());
                jobseeker.setNationId(nationMapper.selectOne(nationWrapper).getId());
            }
            if (jobseekerParam.getPosition() != null && !jobseekerParam.getPosition().equals("")) {
                // 职位字符串映射id
                LambdaQueryWrapper<Position> positionWrapper = new LambdaQueryWrapper<>();
                positionWrapper.eq(Position::getName, jobseekerParam.getPosition());
                jobseeker.setPosId(positionMapper.selectOne(positionWrapper).getId());
            }
            if (jobseekerParam.getPolitic() != null && !jobseekerParam.getPolitic().equals("")) {
                //籍贯字符串映射为id
                LambdaQueryWrapper<PoliticsStatus> politicsStatusWrapper = new LambdaQueryWrapper<>();
                politicsStatusWrapper.eq(PoliticsStatus::getName, jobseekerParam.getPolitic());
                jobseeker.setPoliticId(politicsStatusMapper.selectOne(politicsStatusWrapper).getId());
            }
        } catch (Exception e) {
            result.put("error", "民族/职位/政治面貌/传参异常");
            log.error("民族/职位/政治面貌/传参异常");
            return result;
        }

        jobseeker.setSendTime(System.currentTimeMillis());
        //设置求职状态为待定
        jobseeker.setStatus(2);
        //如果点击提交按钮则这里是1
        if (jobseekerParam.getSubmit() != null) {
            jobseeker.setSubmit(jobseekerParam.getSubmit());
        } else {//未填提交与否默认提交
            jobseeker.setSubmit(true);
        }

        //禁止重复提交
        try {
            jobseekerMapper.insert(jobseeker);
            result.put("jobseekerId", jobseeker.getId().toString());

            //基本信息提交之后要生成一条提交记录
            Deliver deliver = new Deliver();
            deliver.setJobseekerId(jobseeker.getId());
            deliver.setCreateDate(jobseeker.getSendTime());
            deliverMapper.insert(deliver);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                //主键异常，禁止重复提交
                result.put("error", "您已提交过数据，请勿重复提交,等待审核！");
            } else {
                //其他异常
                result.put("error", "其他异常，提交失败");
                log.error("其他异常，插入失败");
            }
        }
        //返回插入结果
        return result;
    }

    @Override
    public Result getDeliverInfoByJobseekerId(Long jobseekerId) {
        LambdaQueryWrapper<Deliver> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Deliver::getJobseekerId, jobseekerId);
        List<Deliver> delivers = deliverMapper.selectList(wrapper);
        //定义每次提交的数据列表，进行时间格式的转换
        List<String> datas = new ArrayList<>();
        for (Deliver deliver : delivers) {
            datas.add(new DateTime(deliver.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        }

        DeliverDataVo deliverDataVo = new DeliverDataVo();
        deliverDataVo.setCount(delivers.size());
        deliverDataVo.setCreateDate(datas);

        return Result.success(deliverDataVo);
    }

    @Override
    public Result updateStatusByJobseekerId(UserStatusParam userStatusParam) {
        Jobseeker jobseeker = new Jobseeker();
        jobseeker.setId(userStatusParam.getId());
        jobseeker.setStatus(userStatusParam.getStatus());
        //获取当前用户作为操作员信息
        Admin currentAdmin = AdminUtils.getCurrentAdmin();
        jobseeker.setOperator(currentAdmin.getName());

        int result = jobseekerMapper.updateById(jobseeker);
        if (1 == result && userStatusParam.getStatus() == 1) {
            //给用户发邮件（等待实现）

            //先看面试表有没有该用户的面试数据
            LambdaQueryWrapper<Interview> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Interview::getJobseekerId, userStatusParam.getId());
            Interview exist = interviewMapper.selectOne(wrapper);
            // 设置状态为通过则在面试安排表中新建一条数据(当设置状态为1，且数据库中不存在该用户面试信息时新建一条数据)
            if (userStatusParam.getStatus() == 1 && exist == null) {
                Interview interview = new Interview();
                interview.setJobseekerId(userStatusParam.getId());
                interview.setCreateTime(System.currentTimeMillis());
                interviewMapper.insert(interview);
            }
            return Result.success("求职者状态修改成功，等待其他操作...");
        }
        return Result.fail(60002, "求职者状态修改失败！");
    }

    @Override
    public Result listAllJobSeeker(JobSeekerPageParam jobSeekerPageParam) {
        Page<Jobseeker> page = new Page<>(jobSeekerPageParam.getPage(), jobSeekerPageParam.getPageSize());
        IPage<Jobseeker> jobseekerIPage = jobseekerMapper.listAllJobseeker(
                page,
                jobSeekerPageParam.getName(),
                jobSeekerPageParam.getAge(),
                jobSeekerPageParam.getGender(),
                jobSeekerPageParam.getPosId(),
                jobSeekerPageParam.getExpectSalary(),//薪资范围
                jobSeekerPageParam.getTiptopDegree(),
                jobSeekerPageParam.getSource(),
                jobSeekerPageParam.getType(),
                false,
                jobSeekerPageParam.getSpecificAge(),
                jobSeekerPageParam.getPhone(),
                1,//只有筛选通过的才有面试信息，因此只查询简历筛选通过者的面试信息
                jobSeekerPageParam.getInterviewSelect()
        );
        List<Jobseeker> records = jobseekerIPage.getRecords();
        long total = jobseekerIPage.getTotal();
        ResultPage resultPage = new ResultPage();
        resultPage.setData(copyList(records, true));
        resultPage.setTotal(total);
        return Result.success(resultPage);
    }

    @Override
    public Result addJobSeekerByOperator(JobseekerParam jobseekerParam) {
        Jobseeker jobseeker = new Jobseeker();
        BeanUtils.copyProperties(jobseekerParam, jobseeker);
        jobseeker.setSendTime(System.currentTimeMillis());
        // 设置求职状态为待定
        jobseeker.setStatus(2);
        // 如果点击提交按钮则这里是1
        jobseeker.setSubmit(true);
        jobseeker.setIsBlack(false);
        // 设置操作员
        jobseeker.setOperator(AdminUtils.getCurrentAdmin().getName());

        //禁止重复提交
        try {
            int result = jobseekerMapper.insert(jobseeker);
            System.out.println("影响结果行数" + result);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                //主键异常，禁止重复提交
                return Result.fail(60001, "已存在该求职者，请勿重复添加！");
            } else {
                //其他异常
                log.error("传参异常/其他异常，插入失败" + e.getMessage());
                return Result.fail(60001, "其他异常，提交失败");
            }
        }
        return Result.success("新增人员成功！");
    }

    @Override
    public Result responseByMailAndPhone(JobseekerParam jobseekerParam) {
        LambdaQueryWrapper<Jobseeker> wrapper = new LambdaQueryWrapper<>();
        if (jobseekerParam.getPhone() != null) {
            wrapper.eq(Jobseeker::getPhone, jobseekerParam.getPhone());
        }
        if (jobseekerParam.getMail() != null) {
            wrapper.eq(Jobseeker::getMail, jobseekerParam.getMail());
        }
        Jobseeker jobseeker = jobseekerMapper.selectOne(wrapper);
        if (jobseeker != null) {
            if (jobseeker.getSubmit()) {
//                查询最近一次投递的时间
                LambdaQueryWrapper<Deliver> timeWrapper = new LambdaQueryWrapper<>();
                timeWrapper.eq(Deliver::getJobseekerId, jobseeker.getId());
                timeWrapper.select(Deliver::getCreateDate).orderByDesc(Deliver::getCreateDate).last("limit 1");
                Deliver deliver = deliverMapper.selectOne(timeWrapper);
                Long createDate = deliver.getCreateDate();
                Long time = System.currentTimeMillis() - createDate;
                if (time / (1000 * 60 * 60 * 24) <= 30) {
                    String timeFormat = new DateTime(createDate).toString("yyyy-MM-dd HH:mm");
                    int days = 30 - ((int) (time / (1000 * 60 * 60 * 24)));
                    return Result.fail(60000, "您在一个月内已经投过简历，上次投递时间" + timeFormat + "，请于" + days + "天后再考虑");
                }
                if (jobseeker.getIsBlack()) {
                    return Result.fail(60000, "您已进入公司黑名单，不可投递");
                }
            } else {//未提交，提交状态为暂存
                //抓取该用户已存在的数据(基本信息，十问作答，附件)
                Result answerData = answerService.findAnswerFormByJobseekerId(jobseeker.getId());
                return Result.success(answerData.getData());
            }
        }
        return Result.success(null);//未查找到该用户任何数据，该用户可提交表单，返回空
    }

    @Override
    public Result getResumeCountByTime(LocalDate date) {
        long milliseconds = date.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
        LambdaQueryWrapper<Jobseeker> wrapper = new LambdaQueryWrapper<>();
        wrapper.gt(Jobseeker::getSendTime, milliseconds);
        int resumeCount = jobseekerMapper.selectList(wrapper).size();
        wrapper.eq(Jobseeker::getStatus, true);
        int resumePassCount = jobseekerMapper.selectList(wrapper).size();
        Map<String, Integer> result = new HashMap<>();
        result.put("resumeCount", resumeCount);//收到简历数
        result.put("resumePassCount", resumePassCount);//初次简历筛选数量

        return Result.success(result);
    }

    @Override
    public Result updateJobseekerBaseinfo(JobseekerParam jobseekerParam) {

        if (jobseekerParam.getName() == null || jobseekerParam.getMail() == null || jobseekerParam.getPhone() == null || jobseekerParam.getPosId() == null ||
                jobseekerParam.getExperience() == null || jobseekerParam.getSource() == null || jobseekerParam.getTiptopDegree() == null ||
                jobseekerParam.getType() == null || jobseekerParam.getExperience().equals("") ||
                jobseekerParam.getSource().equals("") || jobseekerParam.getTiptopDegree().equals("") || jobseekerParam.getType().equals("") ||
                jobseekerParam.getName().equals("") || jobseekerParam.equals("") || jobseekerParam.getPhone().equals("")) {
            return Result.fail(60003, "姓名/邮箱/手机号/职位Id/应聘来源/应聘类型/学历/工作经验必传,不允许为空/空字符串");
        }
        Jobseeker jobseeker = new Jobseeker();
        jobseeker.setId(jobseekerParam.getId());
        jobseeker.setExperience(jobseekerParam.getExperience());
        jobseeker.setSource(jobseekerParam.getSource());
        jobseeker.setTiptopDegree(jobseekerParam.getTiptopDegree());
        jobseeker.setType(jobseekerParam.getType());
        jobseeker.setPhone(jobseekerParam.getPhone());
        jobseeker.setMail(jobseekerParam.getMail());
        jobseeker.setName(jobseekerParam.getName());
        jobseeker.setPosId(jobseekerParam.getPosId());
        if (jobseekerParam.getNotes() != null) {//备注不为空的话也修改备注
            jobseeker.setNotes(jobseekerParam.getNotes());
        }
        if (1 == jobseekerMapper.updateById(jobseeker)) {
            return Result.success("修改求职者信息成功");
        }
        return Result.fail(60003,"修改求职者信息失败！");
    }
}
