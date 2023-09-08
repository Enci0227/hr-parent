package com.txxw.hr.dao.mapper;

import com.txxw.hr.dao.pojo.Answer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Enci
 * @since 2023-09-01
 */
public interface AnswerMapper extends BaseMapper<Answer> {

    /**
     * 查询所有回答并按求职者id分组
     * @return
     */
    List<Answer> selectAllGroupByJobseekerId();
}
