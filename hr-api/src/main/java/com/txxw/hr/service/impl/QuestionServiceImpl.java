package com.txxw.hr.service.impl;

import com.txxw.hr.dao.pojo.Admin;
import com.txxw.hr.dao.pojo.Question;
import com.txxw.hr.dao.mapper.QuestionMapper;
import com.txxw.hr.service.IAdminService;
import com.txxw.hr.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.txxw.hr.utils.AdminUtils;
import com.txxw.hr.vo.QuestionVo;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.params.QuestionParam;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public Result updateQuestion(QuestionParam questionParam) {
        /**
         * 如果有id,则在原有基础上修改
         * 如果没有id，则添加新数据
         */
        Admin currentAdmin = AdminUtils.getCurrentAdmin();
        Question question = new Question();

        question.setId(questionParam.getId());
        question.setType(questionParam.getType());
        question.setQuestion(questionParam.getQuestion());
        question.setCreateDate(System.currentTimeMillis());
        question.setUser(currentAdmin.getUsername());

        if (question.getId() == null) {
            questionMapper.insert(question);
        }else{
            questionMapper.updateById(question);
        }
        return Result.success("天巡十问问题更新成功");
    }

}
