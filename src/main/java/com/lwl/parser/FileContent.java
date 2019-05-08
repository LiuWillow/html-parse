package com.lwl.parser;

import lombok.Data;

/**
 * @author liuweilong
 * @description
 * @date 2019/5/8 17:49
 */
@Data
public class FileContent {
    /**
     * a标签中的字符串
     */
    private String fileText;
    /**
     * 包含<a>标签的文件字符串，需要全部被替换为""
     */
    private String fileAString;

    /**
     * 文件url
     */
    private String url;
}
