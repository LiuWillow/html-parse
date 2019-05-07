package com.lwl.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * date  2019/5/7
 * author liuwillow
 **/
public class HtmlParser {
    private static Set<String> IMAGE_SUFFIX = new HashSet<>();
    private static Set<String> DOC_SUFFIX = new HashSet<>();

    static {
        String[] images = new String[]{"bmp", "jpeg", "JPG", "png",
                "rtf", "jpg", "PNG", "GIF", "gif"};
        String[] docs = new String[]{"docx", "xlsx", "xls", "rar",
                "rtf", "pdf", "pptx", "doc"};
        IMAGE_SUFFIX.addAll(Arrays.asList(images));
        DOC_SUFFIX.addAll(Arrays.asList(docs));
    }

    public static List<String> getAHrefs(String htmlString) {
        Document document = Jsoup.parse(htmlString);
        Elements elements = document.select("a");
        return elements.stream()
                .map(element -> element.attr("href"))
                .collect(Collectors.toList());
    }

    public static List<String> getAValue(String htmlString) {
        Document document = Jsoup.parse(htmlString);
        Elements elements = document.select("a");
        return elements.stream()
                .map(Element::text)
                .collect(Collectors.toList());
    }

    public static boolean isImage(String str) {
        String[] split = str.split("\\.");
        return IMAGE_SUFFIX.contains(split[split.length - 1]);
    }

    public static boolean isDoc(String str) {
        String[] split = str.split("\\.");
        return DOC_SUFFIX.contains(split[split.length - 1]);
    }

    public static ImgAndDoc parseImagAndDoc(String htmlString){
        List<String> hrefs = getAHrefs(htmlString);
        List<String> images = hrefs.stream().filter(HtmlParser::isImage)
                .collect(Collectors.toList());
        List<String> docs = hrefs.stream().filter(HtmlParser::isDoc)
                .collect(Collectors.toList());

        ImgAndDoc imgAndDoc = new ImgAndDoc();
        imgAndDoc.setImgs(images);
        imgAndDoc.setDocs(docs);
        return imgAndDoc;
    }

    public static void main(String[] args) {
        String s = "sdf.sd.pdf";
        String a = "sdf.sd.jpg";
        System.out.println(isImage(a));
        System.out.println(isImage(s));
    }
}
