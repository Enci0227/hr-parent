package com.txxw.hr.controller;


import com.txxw.hr.dao.pojo.Annex;
import com.txxw.hr.dao.pojo.Infokey;
import com.txxw.hr.dao.pojo.Question;
import com.txxw.hr.service.IAnnexService;
import com.txxw.hr.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Enci
 * @since 2023-09-04
 */
@Api(tags = "天巡十问附件管理Controller")
@RestController
@RequestMapping("/annex")
public class AnnexController {

    @Autowired
    private IAnnexService annexService;


    @ApiOperation(value = "查询所有附件选项")
    @GetMapping("list")
    public Result listAnnex(){
        List<Annex> list = annexService.list();
        return Result.success(list);
    }

    @ApiOperation(value = "更新天巡十问是否需要提交附件")
    @PostMapping("update")
    public Result isRequired(@RequestBody List<Annex> annexes){
        if (annexService.updateBatchById(annexes)){
            return Result.success("更新成功！");
        }
        return Result.fail(60002,"更新失败！");
    }


}
