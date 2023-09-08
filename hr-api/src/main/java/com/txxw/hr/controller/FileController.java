package com.txxw.hr.controller;

import cn.hutool.core.io.FileUtil;
import com.txxw.hr.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import static com.sun.deploy.cache.Cache.exists;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/9/6
 * @description:
 **/
@Api(tags = "文件上传下载Controller")
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${ip:localhost}")
    private String ip;

    @Value("${server.port}")
    private String port;

    private static final String ROOT_PATH = System.getProperty("user.dir") + File.separator + "files";


    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();//文件原始名称
        String mainName = FileUtil.mainName(originalFilename);//不包含后缀的名称
        String extName = FileUtil.extName(originalFilename);//文件后缀


        if (!FileUtil.exist(ROOT_PATH)) {
            FileUtil.mkdir(ROOT_PATH);//如果当前文件的父级目录不存在，就创建
        }
        if (FileUtil.exist(ROOT_PATH + File.separator + originalFilename)) {//如果当前上传的文件已经存在了，那么这个时候就要重新命名一个文件名称
            originalFilename = System.currentTimeMillis() + "_" + mainName + "." + extName;
        }

        File saveFile = new File(ROOT_PATH + File.separator + originalFilename);
        file.transferTo(saveFile);//存储文件到本地磁盘
        String url = "http://" + ip + ":" + port + "/file/download/" + originalFilename;
        //返回文件的链接，这个链接就是文件的下载地址，这个地址就是我的后台提供出来的
        return Result.success(url);
    }


    @ApiOperation(value = "文件下载")
    @GetMapping("/download/{fileName}")
    public void download(@PathVariable @ApiParam(value = "文件地址", required = true) String fileName, HttpServletResponse response) throws IOException {
        //根据文件名称获取文件的字节流数组

        String filePath = ROOT_PATH + File.separator + fileName;
        if (!FileUtil.exist(filePath)) {
            return;
        }
        byte[] bytes = FileUtil.readBytes(filePath);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);//数组是一个字节数组，也就是文件的字节流数组
        outputStream.flush();
        outputStream.close();//一定要用完后关闭
        //返回文件的链接，这个链接就是文件的下载地址，这个地址就是我的后台提供出来的

    }
}
