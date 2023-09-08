package com.txxw.hr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.txxw.hr.dao.mapper.AnnexurlMapper;
import com.txxw.hr.dao.mapper.JobseekerMapper;
import com.txxw.hr.dao.mapper.QuestionMapper;
import com.txxw.hr.dao.pojo.Annexurl;
import com.txxw.hr.dao.pojo.Answer;
import com.txxw.hr.dao.mapper.AnswerMapper;
import com.txxw.hr.dao.pojo.Jobseeker;
import com.txxw.hr.service.IAnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.txxw.hr.service.IJobseekerService;
import com.txxw.hr.vo.AnswerVo;
import com.txxw.hr.vo.JobseekerVo;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.TestpaperBodyVo;
import com.txxw.hr.vo.params.AnnexurlParam;
import com.txxw.hr.vo.params.AnswerParam;
import com.txxw.hr.vo.params.JobSeekerPageParam;
import com.txxw.hr.vo.params.TxswAnswerParam;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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

    @Transactional
    @Override
    public Result submitTxswAnswer(TxswAnswerParam txswAnswerParam) {
        //提交求职者基本信息,返回为空说明添加失败，求职者重复提交表单
        Long jobSeekerId = jobseekerService.addJobSeeker(txswAnswerParam.getJobseekerParam());
        if(jobSeekerId == null){
            return Result.fail(60010,"传参异常/请勿重复提交");
        }

        //插入十问作答
        List<AnswerParam> answerParamList = txswAnswerParam.getAnswerParamList();
        for (AnswerParam answerParam : answerParamList) {
            Answer answer = new Answer();
            BeanUtils.copyProperties(answerParam, answer);
            answer.setJobseekerId(jobSeekerId);
            answerMapper.insert(answer);
        }

        //插入求职者作品集数据
        AnnexurlParam annexurlParam = txswAnswerParam.getAnnexurlParam();
        Annexurl annexurl = new Annexurl();
        BeanUtils.copyProperties(annexurlParam,annexurl);
        annexurl.setJobseekerId(jobSeekerId);

        return Result.success("天巡十问提交成功！");
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
        answerVo.setQuestionId(String.valueOf(answer.getQuestionId()));
        answerVo.setQuestion(questionMapper.selectById(answer.getQuestionId()).getQuestion());
        return answerVo;
    }

    //    此段代码为实验练习
//    @Override
//    public Result selectAll() {
//        List<Answer> answers=answerMapper.selectAllGroupByJobseekerId();
//        Map<Long, List<Answer>> personMap = answers.stream().collect(Collectors.groupingBy(Answer::getJobseekerId));
//
//        return Result.success(personMap);
//    }

}
