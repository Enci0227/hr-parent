package com.txxw.hr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.txxw.hr.dao.pojo.Employee;
import com.txxw.hr.dao.mapper.EmployeeMapper;
import com.txxw.hr.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.ResultPage;
import com.txxw.hr.vo.params.EmployeeParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Enci
 * @since 2023-08-23
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 获取所有员工（分页）
     *
     * @param employeeParams
     * @return
     */
    @Override
    public Result getEmployeeByPage(EmployeeParams employeeParams) {

        Page<Employee> page = new Page<>(employeeParams.getPage(), employeeParams.getPageSize());
        IPage<Employee> employeeByPage = employeeMapper.getEmployeeByPage(page, employeeParams.getEmployee(), employeeParams.getBeginDateScope());
        ResultPage resultPage = new ResultPage(employeeByPage.getTotal(), employeeByPage.getRecords());
        return Result.success(resultPage);
    }

    /**
     * 获取工号
     *
     * @return
     */
    @Override
    public Result maxWorkID() {
        List<Map<String, Object>> maps = employeeMapper.selectMaps(new QueryWrapper<Employee>().select("max(workID)"));

        return Result.success(
                String.format("%08d", Integer.parseInt(maps.get(0).get("max(workID)").toString()) + 1));
    }

    /**
     * 添加员工
     * @param employee
     * @return
     */
    @Override
    public Result addEmp(Employee employee) {
        //处理合同期限，保留2位小数
        LocalDate beginContract = employee.getBeginContract();
        LocalDate endContract = employee.getEndContract();
        //获取到两个时间的间隔
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        //保留两位小数
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        //转换成年保留两位小数
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days / 365.00)));
        if (1 == employeeMapper.insert(employee)) {
//            Employee emp = employeeMapper.getEmployee(employee.getId()).get(0);
//            //数据库记录发送的消息
//            String msgId = UUID.randomUUID().toString();
////            String msgId = "123456";
//            MailLog mailLog = new MailLog();
//            mailLog.setMsgId(msgId);
//            mailLog.setEid(employee.getId());
//            mailLog.setStatus(0);
//            mailLog.setRouteKey(MailConstants.MAIL_ROUTING_KEY_NAME);
//            mailLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
//            mailLog.setCount(0);
//            mailLog.setTryTime(LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT));
//            mailLog.setCreateTime(LocalDateTime.now());
//            mailLog.setUpdateTime(LocalDateTime.now());
//            mailLogMapper.insert(mailLog);
//
//            //发送信息
//            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME,
//                    MailConstants.MAIL_ROUTING_KEY_NAME,emp, new CorrelationData(msgId));
            return Result.success("添加成功！");
        }
        return Result.fail(60001,"添加失败！");
    }

    /**
     * 查询员工
     * @param id
     * @return
     */
    @Override
    public List<Employee> getEmployee(Long id) {
        return employeeMapper.getEmployee(id);
    }
}
