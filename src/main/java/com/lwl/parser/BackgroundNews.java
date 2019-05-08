package com.lwl.parser;

import lombok.Data;

import java.util.*;

@Data
public class BackgroundNews{
    public static final String DEFAULT_SOURCE = "海关总署";
    public static final String DEFAULT_TYPE = "政策法规-关务";
    public static final Integer DEFAULT_STATE = 1;
    public static final Byte DEFAULT_STATUS = 1;
    /**
     * 自增
     */
    private Integer id;

    /**
     * 新闻标题
     */
    private String title;

    /**
     * 来源   全部采用   "海关总署"
     */
    private String source = "海关总署";

    /**
     * 类型  全部采用  "政策法规-关务"
     */
    private String type = "政策法规-关务";

    /**
     * 1代表待审核，2代表审核不通过，0代表审核通过',默认待审核
     * 全部用 1 审核通过
     */
    private Integer state = 1;

    /**
     * 不管
     */
    private String path = "";

    /**
     * 封面图URL   产品没给
     */
    private String cover;

    /**
     * 简介，引用    文章标题
     */
    private String introduction;

    /**
     * 不管  默认 ''
     */
    private String img = "";

    /**
     * 关键词
     */
    private String keyWordOne;

    /**
     * 不管
     */
    private String keyWordTwo = "";
    /**
     * 不管
     */
    private String keyWordThree = "";
    /**
     * 不管
     */
    private String keyWordFour = "";
    /**
     * 不管
     */
    private String keyWordFive = "";

    private Date addTime;

    /**
     * 是否可用    全部用1   可用
     */
    private Byte status = 1;
    /**
     * 不管
     */
    private Integer lastPage = 0;

    /**
     * 不管
     */
    private Integer nextPage = 0;

    /**
     * 不管
     */
    private Integer listOne = 0;

    /**
     * 不管
     */
    private Integer listTwo = 0;

    /**
     * 不管
     */
    private Integer listThree = 0;

    /**
     * 不管
     */
    private Integer listFour = 0;

    /**
     * 不管
     */
    private Integer listFive = 0;

    /**
     * 不管
     */
    private Integer priority = 0;


    private Date updateTime;

    private Date publishTime;

    /**
     * 操作人名称  全部用 “”
     */
    private String operatorName = "";

    /**
     * 新闻类型   全部采用  2 新闻
     */
    private Byte newsType = 2;

    /**
     * 根据文章内容判断是否有附件  1 有  0 没有，默认0
     */
    private Byte haveFile = 0;

    /**
     * 全部用  0
     */
    private Integer operatorId = 0;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 公告  第几年
     */
    private Integer year;

    /**
     * 第几号公告
     */
    private Integer sortNum;




}