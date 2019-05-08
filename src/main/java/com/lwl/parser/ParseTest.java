package com.lwl.parser;

import java.io.File;
import java.util.*;

/**
 * @author liuweilong
 * @description
 * @date 2019/5/8 15:53
 */
public class ParseTest {
    public static void main(String[] args) throws InterruptedException {
        //TODO 要获取上传后的图片url，key为文件名（id），value为上传后的url

        Map<String, String> realImgMap = new HashMap<>();
        //TODO key为上传之前的url，value为文件名（id）
        File file = new File("D:\\中非\\线上问题\\2019-05-07海关爬数据\\海关总署-需求1\\实际要上传数据\\第一批上传数据.xlsx");
        List<BackgroundNews> newsOrigin = handle(file);
        List<BackgroundNews> newsNeedSort = new ArrayList<>();
        Iterator<BackgroundNews> newsIterator = newsOrigin.iterator();
        while (newsIterator.hasNext()) {
            BackgroundNews backgroundNews = newsIterator.next();
            String content = backgroundNews.getContent();
            // 先解析html，获取img和file的列表
            ImgAndDoc imgAndDoc = HtmlParser.parseImagAndDoc(backgroundNews.getContent());
            // 判断是否有file，将have_file字段修改
            List<FileContent> fileContents = imgAndDoc.getFileContents();
            if (fileContents != null && !fileContents.isEmpty()) {
                backgroundNews.setHaveFile((byte) 1);
                // 取消file链接，保留链接中的文字
                for (FileContent fileContent: fileContents) {
                    content = content.replace(fileContent.getFileAString(), fileContent.getFileText());
                }
            }
            //TODO 替换img
            List<String> imgs = imgAndDoc.getImgs();
            if (imgs != null && !imgs.isEmpty()) {
                for (String img : imgs) {
                    content = content.replaceAll(img, realImgMap.get(img) == null ? "" : realImgMap.get(img));
                }
            }
            backgroundNews.setContent(content);
            //TODO 按日期排序问题，数据库排序规则，按照priority倒叙
            if (backgroundNews.getYear() != null){
                newsIterator.remove();
                newsNeedSort.add(backgroundNews);
            }
        }
        System.out.println();
    }

    private static List<BackgroundNews> handle(File file) {
        List<BackgroundNews> newsList = new ArrayList<>();
        ExcelEventParser excelEventParser = new ExcelEventParser(file.getAbsolutePath())
                .setHandler(new SimpleSheetContentsHandler(newsList));
        excelEventParser.parse();
        return newsList;
    }
}
