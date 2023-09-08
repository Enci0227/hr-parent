package com.txxw.hr.dao.mapper;

import com.txxw.hr.dao.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.txxw.hr.dao.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Enci
 * @since 2023-08-15
 */
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 获取所有操作员
     * @param id
     * @param keywords
     * @return
     */
    List<Admin> getAllAdmins(@Param("id") Long id, @Param("keywords") String keywords);

}
