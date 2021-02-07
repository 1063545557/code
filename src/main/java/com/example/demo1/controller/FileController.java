package com.example.demo1.controller;

import com.alibaba.excel.EasyExcel;
import com.example.demo1.entity.DemoData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Value("${filepath}")
    private String filePath;

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam(value = "file") MultipartFile file){
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        File targetFile=new File(filePath);
        if (!targetFile.exists()){
            targetFile.mkdirs();
        }
        try(FileOutputStream out=new FileOutputStream(filePath+file.getOriginalFilename());)
        {
            out.write(file.getBytes());
            log.info("upload success");

        }catch (Exception e){
            e.printStackTrace();
            log.error("文件上传失败！");
            return "uploading failure";
        }
        return "uploading success";
    }
    //多文件上传
    @PostMapping("/upload1")
    @ResponseBody
    public String upload1(HttpServletRequest request){
        List<MultipartFile> files=((MultipartHttpServletRequest)request).getFiles("file");
        MultipartFile file=null;

        for (int i=0;i<files.size();i++){
            file=files.get(i);
            if (!file.isEmpty()){
                try(BufferedOutputStream stream=new BufferedOutputStream(new FileOutputStream(new File(filePath+file.getOriginalFilename())));){
                    byte[] bytes=file.getBytes();
                    stream.write(bytes);
                }catch (Exception e){
                    e.printStackTrace();
                    return "uploading failure";
                }
            }
        }
        return "uploading success";
    }

    @GetMapping("/download")
    public String download(HttpServletResponse response){

        String filepath="a.txt";
        String fileName = "TestsimpleWrite" + System.currentTimeMillis() + ".xlsx";
        File file=new File(fileName);
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());

            response.setContentType("application/octet-stream");
            response.setHeader("content-type","application/octet-stream");
            try {
                response.setHeader("Content-Disposition","attachment;fileName="+URLEncoder.encode(fileName,"UTF-8"));
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }

        //加上设置大小 下载下来的excel文件才不会在打开前提示修复
        response.addHeader("Content-Length",String.valueOf(file.length()));

        byte[] buffer=new byte[1024];
        OutputStream os=null;
        try(FileInputStream fis=new FileInputStream(file);
            BufferedInputStream bis=new BufferedInputStream(fis);
            ){
                os=response.getOutputStream();
                int i=bis.read(buffer);
                while(i !=-1){
                    os.write(buffer);
                    i=bis.read(buffer);
                }

            }catch (Exception e){
            e.printStackTrace();
        }finally {
            new File(fileName).delete();
        }
        log.info("download success");
        return "download success";
    }
    private  List<DemoData> data() {
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }
}
