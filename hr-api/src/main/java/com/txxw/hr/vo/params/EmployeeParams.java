package com.txxw.hr.vo.params;

import com.txxw.hr.dao.pojo.Employee;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeParams {

    private int page = 1;

    private int pageSize = 10;

    private Employee employee;

    private LocalDate[] beginDateScope;

//    private Long categoryId;
//
//    private Long tagId;
//
//    private String year;
//
//    private String month;
//
//    public String getMonth(){
//        if (this.month != null && this.month.length() == 1){
//            return "0"+this.month;
//        }
//        return this.month;
//    }
}
