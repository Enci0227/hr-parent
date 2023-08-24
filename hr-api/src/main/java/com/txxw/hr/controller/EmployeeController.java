package com.txxw.hr.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.txxw.hr.dao.pojo.*;
import com.txxw.hr.service.*;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.params.EmployeeParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Enci
 * @since 2023-08-23
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IPoliticsStatusService politicsStatusService;
    @Autowired
    private IJoblevelService joblevelService;
    @Autowired
    private INationService nationService;
    @Autowired
    private IPositionService positionService;
    @Autowired
    private IDepartmentService departmentService;

    @ApiOperation(value = "获取所有员工(分页)")
    @GetMapping("/")
    public Result getEmployee(@RequestBody EmployeeParams employeeParams){

        return employeeService.getEmployeeByPage(employeeParams);
    }

    /**
     * 以下是添加员工所需要的选择性信息查询
     * @return
     */

    @ApiOperation(value = "获取所有政治面貌")
    @GetMapping("/politicsstatus")
    public List<PoliticsStatus> getAllPoliticsStatus(){
        return politicsStatusService.list();
    }

    @ApiOperation(value = "获取所有职称")
    @GetMapping("/joblevels")
    public List<Joblevel> getAllJoblevels(){
        return joblevelService.list();
    }

    @ApiOperation(value = "获取所有民族")
    @GetMapping("/nations")
    public List<Nation> getAllNations(){
        return nationService.list();
    }

    @ApiOperation(value = "获取所有职位")
    @GetMapping("/positions")
    public List<Position> getAllPositions(){
        return positionService.list();
    }

    @ApiOperation(value = "获取所有部门")
    @GetMapping("/deps")
    public Result getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @ApiOperation(value = "获取工号")
    @GetMapping("/maxWorkID")
    public Result maxWorkID(){
        return employeeService.maxWorkID();
    }

    @ApiOperation(value = "添加员工")
    @PostMapping("/")
    public Result addEmp(@RequestBody Employee employee){
        return employeeService.addEmp(employee);
    }

    @ApiOperation(value = "更新员工")
    @PutMapping("/")
    public Result updateEmp(@RequestBody Employee employee){
        if (employeeService.updateById(employee)){
            return Result.success("更新成功！");
        }
        return Result.fail(60002,"更新失败！");
    }

    @ApiOperation(value = "删除员工")
    @DeleteMapping("/{id}")
    public Result deleteEmp(@PathVariable Integer id){
        if (employeeService.removeById(id)){
            return Result.success("删除成功！");
        }
        return Result.fail(60003,"删除失败！");
    }

    @ApiOperation(value = "导出员工数据")
    //乱码的话要加produces = "application/octet-stream"
    @GetMapping(value = "/export",produces = "application/octet-stream")
    public void exportEmployee(HttpServletResponse response){
        List<Employee> list = employeeService.getEmployee(null);
        //三个参数分别是：导出的文件名，导出的表格名字，Excel的类型(HSSF表示03版Excel)
        ExportParams params = new ExportParams("员工表", "员工表", ExcelType.HSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(params, Employee.class, list);
        ServletOutputStream out = null;
        try {
            //流形式
            response.setHeader("content-type","application/octet-stream");
            //防止中文乱码
            response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode("员工表.xls","UTF-8"));
            out = response.getOutputStream();
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null!=out){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @ApiOperation(value = "导入员工数据")
    @PostMapping("/import")
    public Result importEmployee(MultipartFile file){
        ImportParams params = new ImportParams();
        //去掉标题行
        params.setTitleRows(1);
        List<Nation> nationList = nationService.list();//拿所有民族，一共就56个名族嘛。拿一次就好，好过循环n遍拿id
        List<PoliticsStatus> politicsStatusList = politicsStatusService.list();
        List<Department> departmentList = departmentService.list();
        List<Joblevel> joblevelList = joblevelService.list();
        List<Position> positionList = positionService.list();

        try {
            List<Employee> list = ExcelImportUtil.importExcel(file.getInputStream(), Employee.class, params);
            list.forEach(employee -> {
                //民族id
                //讲解：employee.getNation().getName()  name被设置了非空，肯定拿得到，拿到我们当前的民族name
                // new Nation(employee.getNation().getName())  新建民族对象，把name放进去。 只有民族name，没有其他
                // nationList.indexOf 因为重写equals和hashCode方法了，此方法可以通过民族name去比较拿到民族的索引下标
                // nationList.get 这下可以通过下标获取整个对象，里面有id 有name，有实体类的变量
                // .getId() 这个对象获取id
                // employee.setNationId 设置当前的民族Id
                employee.setNationId(nationList.get(nationList.indexOf(new Nation(employee.getNation().getName()))).getId());
                //政治面貌id
                employee.setPoliticId(politicsStatusList.get(politicsStatusList.indexOf(new PoliticsStatus(employee.getPoliticsStatus().getName()))).getId());
                //部门id
                employee.setDepartmentId(departmentList.get(departmentList.indexOf(new Department(employee.getDepartment().getName()))).getId());
                //职称id
                employee.setJobLevelId(joblevelList.get(joblevelList.indexOf(new Joblevel(employee.getJoblevel().getName()))).getId());
                //职位id
                employee.setPosId(positionList.get(positionList.indexOf(new Position(employee.getPosition().getName()))).getId());
            });
            if (employeeService.saveBatch(list)){
                return Result.success("导入成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail(80001,"导入失败！");
    }

}
