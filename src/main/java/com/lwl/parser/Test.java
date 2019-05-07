package com.lwl.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.Objects;

/**
 * date  2019/5/7
 * author liuwillow
 **/
public class Test {
    public static void main(String[] args) throws IOException {
        //TODO 先把图片都上传了，放到一个map里，key为图片名，value为路径

        //TODO 每条数据都构造一个实体
        File file = new File("D:\\ideaProjects\\test-html-parser\\src\\main\\resources\\test.html");
        StringBuilder stringBuilder = new StringBuilder();
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        while (fileInputStream.read(bytes) != -1) {
            stringBuilder.append(new String(bytes, "utf-8"));
        }
        String htmlString = stringBuilder.toString();
        ImgAndDoc imgAndDoc = HtmlParser.parseImagAndDoc(htmlString);
        if (Objects.nonNull(imgAndDoc.getDocs()) && !imgAndDoc.getDocs().isEmpty()) {
            //TODO 要把文档存起来，理论上要先插入文档，再插入其他数据
        }
        if (Objects.nonNull(imgAndDoc.getImgs()) && !imgAndDoc.getImgs().isEmpty()) {
            //TODO 替换Img
            for (String imageString : imgAndDoc.getImgs()) {
                htmlString = htmlString.replaceAll(imageString, "从map里根据imageString拿到的url");
            }
        }
        System.out.println(htmlString);


        //TODO 然后把数据存到数据库
    }
}
