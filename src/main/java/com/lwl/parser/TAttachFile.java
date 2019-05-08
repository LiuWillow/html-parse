package com.lwl.parser;

import lombok.Data;

import java.util.Date;

@Data
public class TAttachFile {
    private Integer id;

    private Integer newsId;

    private String fileName;

    private String fileUrl;

    private Date uploadTime;

    private Boolean status;
}