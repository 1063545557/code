package com.example.demo1.demo;

import com.alibaba.excel.EasyExcel;
import com.example.demo1.entity.DemoData;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EasyExcelDemo {
    public static void main(String[] args) {
//        String fileName = "TestsimpleWrite" + System.currentTimeMillis() + ".xlsx";
//        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());

    }
    private static List<DemoData> data() {
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
