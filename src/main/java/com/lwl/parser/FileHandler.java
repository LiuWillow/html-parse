package com.lwl.parser;

import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liuweilong
 * @description
 * @date 2019/5/8 15:06
 */
public class FileHandler implements XSSFSheetXMLHandler.SheetContentsHandler{
    private List<String> row = new LinkedList<>();
    //TODO key为上传之前的url，value为文件名（id）
    //excel里头，第一行没用，其他分别为  id, url  不管
    Map<String, String> mapBeforeUpload;


    public FileHandler(Map<String, String> mapBeforeUpload){
        this.mapBeforeUpload = mapBeforeUpload;
    }

    @Override
    public void startRow(int rowNum) {
        row.clear();
    }

    @Override
    public void endRow(int rowNum) {
        if (rowNum == 0) {
            mapBeforeUpload.clear();
            return;
        }
        // 数据
        mapBeforeUpload.put(row.get(1), row.get(0));
    }

    @Override
    public void cell(String cellReference, String formattedValue, XSSFComment comment) {
        row.add(formattedValue);
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {

    }
}
