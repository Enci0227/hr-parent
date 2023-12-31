package com.txxw.hr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.txxw.hr.dao.mapper.*;
import com.txxw.hr.dao.pojo.Annex;
import com.txxw.hr.dao.pojo.Infokey;
import com.txxw.hr.dao.pojo.Question;
import com.txxw.hr.dao.pojo.Testpaper;
import com.txxw.hr.service.ITestpaperService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.txxw.hr.vo.QuestionVo;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.TestpaperVo;
import com.txxw.hr.vo.params.InfokeyParam;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Enci
 * @since 2023-09-07
 */
@Service
public class TestpaperServiceImpl extends ServiceImpl<TestpaperMapper, Testpaper> implements ITestpaperService {

    @Autowired
    private TestpaperMapper testpaperMapper;
    @Autowired
    private InfokeyMapper infokeyMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private AnnexMapper annexMapper;

    @Override
    public Result getTestpaper(Long id) {
        /**
         * 1.首先根据id查询问卷表得到各类题型编号集合
         * 2.将基本信息字段映射成数据集合
         * 3.将问题映射成数据及集合
         */
        Testpaper testpaper = testpaperMapper.selectById(id);

        //获取基本信息字段
        String[] infokeyIds = testpaper.getInfolist().split(",");
        List<Long> infokeyIdList = new ArrayList<>();
        for (String infokeyId : infokeyIds) {
            infokeyIdList.add(Long.parseLong(infokeyId));
        }
        LambdaQueryWrapper<Infokey> infokeyWrapper = new LambdaQueryWrapper<>();
        infokeyWrapper.in(Infokey::getId,infokeyIdList);
        List<Infokey> infokeyList = infokeyMapper.selectList(infokeyWrapper);

        //获取问题列表
        LambdaQueryWrapper<Question> questionWrapper = new LambdaQueryWrapper<>();
        questionWrapper.eq(Question::getType,id);
        List<Question> questionList = questionMapper.selectList(questionWrapper);
        List<QuestionVo> questionVoList = copyList(questionList);


        //获取附件选项
        List<Annex> annexList = annexMapper.selectList(null);

        //生成问卷表单
        TestpaperVo testpaperVo = new TestpaperVo();
        testpaperVo.setInfokeyList(infokeyList);
        testpaperVo.setQuestionList(questionVoList);
        testpaperVo.setAnnexList(annexList);

        return Result.success(testpaperVo);
    }

    /**
     * 更新天巡十问问卷基本信息表单
     *
     * @param infokeyParam
     */
    @Transactional
    @Override
    public Result updateInfokeyForm(InfokeyParam infokeyParam) {
        /**
         * 1.修改数据库中已经存在的基本信息字段集合
         */
        LambdaQueryWrapper<Infokey> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Infokey::getId,infokeyParam.getTestpaperId());
        Testpaper testpaper = new Testpaper();
        testpaper.setId(infokeyParam.getTestpaperId());

        // 将基本信息字段id集合转换为用英文,分隔的字符串
        List<Long> infokeyIds = infokeyParam.getInfokeyIds();
        String infokeyList = infokeyIds.stream().map(Object::toString).collect(Collectors.joining(","));
        testpaper.setInfolist(infokeyList);

        testpaperMapper.updateById(testpaper);
        return Result.success("基本信息表单更新成功");
    }

    private List<QuestionVo> copyList(List<Question> records) {
        List<QuestionVo> questionVoList = new ArrayList<>();
        for (Question record : records) {
            questionVoList.add(copy(record));
        }
        return questionVoList;
    }

    private QuestionVo copy(Question question){
        QuestionVo questionVo = new QuestionVo();
        questionVo.setId(String.valueOf(question.getId()));
        BeanUtils.copyProperties(question,questionVo);
        questionVo.setCreateDate(new DateTime(question.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        return questionVo;
    }
}
