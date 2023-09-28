package com.txxw.hr.service;

import com.txxw.hr.dao.pojo.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.params.QuestionParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Enci
 * @since 2023-08-30
 */
public interface IQuestionService extends IService<Question> {

    /**
     * 更新或添加天巡十问问题
     * 点击每条问题下方的保存按钮的功能
     * @param questionParam
     */
    Result updateQuestion(QuestionParam questionParam);

}
