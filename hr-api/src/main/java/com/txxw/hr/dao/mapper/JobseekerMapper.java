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
     * @param page
     * @param name
     * @param age
     * @param gender
     * @param posId
     * @param expectSalary
     * @param tiptopDegree
     * @param source
     * @param type
     * @param isBlack
     * @param specificAge
     * @param phone
     * @param status
     * @return
     */
    IPage<Jobseeker> listJobseeker(Page<Jobseeker> page, String name, Integer[] age, String gender, Long posId, Integer[] expectSalary, String tiptopDegree, String source, String type, Boolean isBlack, Integer specificAge, String phone, Integer status);

    /**
     *
     * @param page
     * @param name
     * @param age
     * @param gender
     * @param posId
     * @param expectSalary
     * @param tiptopDegree
     * @param source
     * @param type
     * @param isBlack
     * @param specificAge
     * @param phone
     * @param status
     * @param interviewSelect
     * @return
     */
    IPage<Jobseeker> listAllJobseeker(Page<Jobseeker> page, String name, Integer[] age, String gender, Long posId, Integer[] expectSalary, String tiptopDegree, String source, String type, Boolean isBlack, Integer specificAge, String phone, Integer status, Integer interviewSelect);
}
