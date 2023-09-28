package com.txxw.hr.service;

import com.txxw.hr.dao.pojo.Annexurl;
import com.txxw.hr.dao.pojo.Answer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.txxw.hr.vo.AnswerVo;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.params.AnswerParam;
import com.txxw.hr.vo.params.JobSeekerPageParam;
import com.txxw.hr.vo.params.TxswAnswerParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Enci
 * @since 2023-09-01
 */
public interface IAnswerService extends IService<Answer> {

    /**
     * 求职者保存天巡十问表单
     * @param txswAnswerParam
     * @return
     */
    Result submitTxswAnswer(TxswAnswerParam txswAnswerParam);

    /**
     * 根据求职者id查询十问作答
     * @param jobSeekerId
     * @return
     */
    List<AnswerVo> getAnswerByJobSeekerId(Long jobSeekerId);

    /**
     * 根据求职者id求职者简历及作品集
     * @param jobSeekerId
     * @return
     */
    Annexurl getAnnexByJobSeekerId(Long jobSeekerId);

    /**
     * 根据求职者id获取问卷作答内容
     * @param jobseekerId
     * @return
     */
    Result findAnswerFormByJobseekerId(Long jobseekerId);
}
