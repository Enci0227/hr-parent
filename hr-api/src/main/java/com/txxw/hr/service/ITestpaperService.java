package com.txxw.hr.service;

import com.txxw.hr.dao.pojo.Testpaper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.params.InfokeyParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Enci
 * @since 2023-09-07
 */
public interface ITestpaperService extends IService<Testpaper> {

    /**
     * 根据求职来源类型获取问卷信息
     * @param id
     * @return
     */
    Result getTestpaper(Long id);

    /**
     * 更新天巡十问基本信息表单
     * @param infokeyParam
     */
    Result updateInfokeyForm(InfokeyParam infokeyParam);
}
