package com.txxw.hr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.txxw.hr.dao.mapper.*;
import com.txxw.hr.dao.pojo.Annexurl;
import com.txxw.hr.dao.pojo.Answer;
import com.txxw.hr.dao.pojo.Deliver;
import com.txxw.hr.dao.pojo.Jobseeker;
import com.txxw.hr.service.IAnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.txxw.hr.service.IJobseekerService;
import com.txxw.hr.vo.*;
import com.txxw.hr.vo.params.*;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Enci
 * @since 2023-09-01
 */
@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements IAnswerService {

    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private IJobseekerService jobseekerService;
    @Autowired
    private AnnexurlMapper annexurlMapper;
    @Autowired
    private JobseekerMapper jobseekerMapper;
    @Autowired
    private DeliverMapper deliverMapper;



    @Transactional
    @Override
    public Result submitTxswAnswer(TxswAnswerParam txswAnswerParam) {
        //如果有该用户信息，也就是之前暂存过，则清除所有数据，当点击提交时重新提交信息
        LambdaQueryWrapper<Jobseeker> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Jobseeker::getPhone,txswAnswerParam.getJobseekerParam().getPhone());
        wrapper.eq(Jobseeker::getMail,txswAnswerParam.getJobseekerParam().getMail());
        Jobseeker existjobseeker = jobseekerMapper.selectOne(wrapper);
        //有该用户，提交状态为暂存时，清除用户信息，重新提交/暂存
        if (existjobseeker != null&&!existjobseeker.getSubmit()){//有该用户并且提交状态为暂存
            jobseekerService.removeById(existjobseeker.getId());//删除求职表数据
            Map<String,Object> answerMap  = new HashMap<>();
            answerMap.put("jobseekerId",existjobseeker.getId());
            answerMapper.deleteByMap(answerMap);//删除十问回答
            Map<String,Object> annexMap  = new HashMap<>();
            annexMap.put("jobseekerId",existjobseeker.getId());
            annexurlMapper.deleteByMap(annexMap);//删除该用户的附件信息
            Map<String,Object> deliverMap  = new HashMap<>();
            deliverMap.put("jobseekerId",existjobseeker.getId());
            deliverMapper.deleteByMap(deliverMap);//删除该用户的提交信息
        }else if(existjobseeker != null&&existjobseeker.getSubmit()){//有该用户且提交状态为true
            return Result.fail(60013,"您已提交过信息！");
        }
        //提交求职者基本信息,返回为空说明添加失败，求职者重复提交表单
        Map<String, String> insertResult = jobseekerService.addJobSeeker(txswAnswerParam.getJobseekerParam());
        if(!insertResult.containsKey("jobseekerId")){
            return Result.fail(60010,insertResult.get("error"));
        }

        Long jobSeekerId = Long.parseLong(insertResult.get("jobseekerId"));

        //插入十问作答
        List<AnswerParam> answerParamList = txswAnswerParam.getAnswerParamList();
        for (AnswerParam answerParam : answerParamList) {
            Answer answer = new Answer();
            BeanUtils.copyProperties(answerParam, answer);
            answer.setJobseekerId(jobSeekerId);
            try{
                answerMapper.insert(answer);
            }catch (Exception e){
                return Result.fail(60011,"十问回答传参异常！");
            }
        }

        //插入求职者作品集数据
        AnnexurlParam annexurlParam = txswAnswerParam.getAnnexurlParam();
        Annexurl annexurl = new Annexurl();
        BeanUtils.copyProperties(annexurlParam,annexurl);
        annexurl.setJobseekerId(jobSeekerId);
        try{
            annexurlMapper.insert(annexurl);
        }catch (Exception e){
            return Result.fail(60012,"简历/作品集传参异常！");
        }
        if (txswAnswerParam.getJobseekerParam().getSubmit() != null){
            if(!txswAnswerParam.getJobseekerParam().getSubmit()){
                return Result.success("保存成功，求职数据已更新！");
            }
        }
        return Result.success("天巡求职问卷提交成功！");
    }

    @Override
    public List<AnswerVo> getAnswerByJobSeekerId(Long jobSeekerId) {
        LambdaQueryWrapper<Answer> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Answer::getJobseekerId, jobSeekerId);
        return copyList(answerMapper.selectList(wrapper));
    }

    @Override
    public Annexurl getAnnexByJobSeekerId(Long jobSeekerId) {
        LambdaQueryWrapper<Annexurl> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Annexurl::getJobseekerId, jobSeekerId);
        return annexurlMapper.selectOne(wrapper);
    }

    @Override
    public Result findAnswerFormByJobseekerId(Long jobseekerId) {
        TestpaperBodyVo testpaperBodyVo = new TestpaperBodyVo();
        testpaperBodyVo.setJobseekerVo(jobseekerService.findJobSeekerById(jobseekerId));
        testpaperBodyVo.setAnswerList(getAnswerByJobSeekerId(jobseekerId));
        testpaperBodyVo.setAnnexurl(getAnnexByJobSeekerId(jobseekerId));
        return Result.success(testpaperBodyVo);
    }

    private List<AnswerVo> copyList(List<Answer> answers) {
        ArrayList<AnswerVo> answerVoList = new ArrayList<>();
        for (Answer answer : answers) {
            answerVoList.add(copy(answer));
        }
        return answerVoList;
    }

    private AnswerVo copy(Answer answer){
        AnswerVo answerVo = new AnswerVo();
        BeanUtils.copyProperties(answer,answerVo);
        answerVo.setAnswerId(String.valueOf(answer.getId()));
        answerVo.setQuestionId(String.valueOf(answer.getQuestionId()));
        if(questionMapper.selectById(answer.getQuestionId())==null){
            answerVo.setQuestion("该问题当前已被操作员删除！");
        }else{
            answerVo.setQuestion(questionMapper.selectById(answer.getQuestionId()).getQuestion());
        }
        return answerVo;
    }


}
