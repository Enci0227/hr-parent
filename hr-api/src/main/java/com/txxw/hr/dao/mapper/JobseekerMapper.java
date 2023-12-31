package com.txxw.hr.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.txxw.hr.dao.pojo.Jobseeker;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Enci
 * @since 2023-08-30
 */
public interface JobseekerMapper extends BaseMapper<Jobseeker> {

    /**
     * 条件筛选
//     * @param page
     * @param gender
     * @param posId
     * @param tiptopDegree
     * @param source
     * @param type
     * @return
     */
    IPage<Jobseeker> listJobseeker(Page<Jobseeker> page, String gender, Long posId, String tiptopDegree, String source, String type);
}
