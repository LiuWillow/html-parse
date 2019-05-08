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
public class SimpleSheetContentsHandler implements XSSFSheetXMLHandler.SheetContentsHandler{
    private static Pattern pattern = Pattern.compile("公告\\d+年第\\d+号");
    private Date now = new Date();
    /**
     * 1 文章标题  2 板块  3 关键词  4 文章内容  5 文章时间   6 系统时间
      */
    private static final int IDX_TITLE = 1;
    private static final int IDX_KEYWORD = 3;
    private static final int IDX_CONTENT = 4;
    private static final byte NO_FILE = 0;
    private static final byte HAVE_FILE = 1;


    private List<String> row = new LinkedList<>();
    private List<BackgroundNews> newsList;

    public SimpleSheetContentsHandler(List<BackgroundNews> newsList){
        this.newsList = newsList;
    }


    @Override
    public void startRow(int rowNum) {
        row.clear();
    }

    @Override
    public void endRow(int rowNum) {
        if (rowNum == 0) {
            newsList.clear();
            return;
        }
        // 数据
        BackgroundNews backgroundNews = generateNews(row);
        newsList.add(backgroundNews);
    }

    @Override
    public void cell(String cellReference, String formattedValue, XSSFComment comment) {
        row.add(formattedValue);
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {

    }


    private BackgroundNews generateNews(List<String> row) {
        // 1 文章标题  2 板块  3 关键词  4 文章内容  5 文章时间   6 系统时间
        if (Objects.isNull(row) || row.isEmpty()){
            return null;
        }
        BackgroundNews backgroundNews = new BackgroundNews();
        backgroundNews.setTitle(row.get(IDX_TITLE));
        backgroundNews.setPath("");
        //TODO 封面图片待定
        backgroundNews.setCover("");
        backgroundNews.setIntroduction(row.get(IDX_TITLE));
        backgroundNews.setImg("");
        backgroundNews.setKeyWordOne(row.get(IDX_KEYWORD));
        backgroundNews.setKeyWordTwo("");
        backgroundNews.setKeyWordThree("");
        backgroundNews.setKeyWordFour("");
        backgroundNews.setKeyWordFive("");
        backgroundNews.setAddTime(now);
        backgroundNews.setUpdateTime(now);
        backgroundNews.setPublishTime(now);
        backgroundNews.setContent(row.get(IDX_CONTENT));
        //TODO 要解析是否有    公告xxxx年第yy号
        //TODO  年第号
        Matcher matcher = pattern.matcher(backgroundNews.getTitle());
        if (matcher.find()){
            String group = matcher.group();
            int nianIndex = group.lastIndexOf("年");
            int diIndex = group.indexOf("第");
            String yearString = group.substring(nianIndex - 4, nianIndex);
            String haoString = group.substring(diIndex + 1, group.length() - 1);
            backgroundNews.setYear(Integer.valueOf(yearString));
            backgroundNews.setSortNum(Integer.valueOf(haoString));
        }

        return backgroundNews;
    }


    public static void main(String[] args) {
        Matcher matcher = pattern.matcher("这是公告2014年第222号公告水电费公告2019s第21号公告");
        while (matcher.find()){

        }
        System.out.println();
    }
}
