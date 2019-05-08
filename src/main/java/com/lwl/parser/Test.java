package com.lwl.parser;

import java.util.*;

/**
 * @author liuweilong
 * @description
 * @date 2019/5/8 18:32
 */
public class Test {
    public static void main(String[] args) {
        List<BackgroundNews> list = new ArrayList<>();
        BackgroundNews backgroundNews1 = new BackgroundNews();
        backgroundNews1.setYear(2018);
        backgroundNews1.setSortNum(87);

        BackgroundNews backgroundNews2 = new BackgroundNews();
        backgroundNews2.setYear(2019);
        backgroundNews2.setSortNum(56);


        BackgroundNews backgroundNews3 = new BackgroundNews();
        backgroundNews3.setYear(2019);
        backgroundNews3.setSortNum(42);

        BackgroundNews backgroundNews4 = new BackgroundNews();
        backgroundNews4.setYear(2019);
        backgroundNews4.setSortNum(422);


        list.add(backgroundNews1);
        list.add(backgroundNews2);
        list.add(backgroundNews3);
        list.add(backgroundNews4);

        Comparator<BackgroundNews> comparator = (new1, new2) -> {
            //倒叙排列
            if (new1.getYear() < new2.getYear()) {
                return 1;
            }
            if (new1.getYear() > new2.getYear()) {
                return 0;
            }
            if (new1.getSortNum() < new2.getSortNum()) {
                return 1;
            }
            return 0;
        };

        list.sort(comparator);
        for (BackgroundNews backgroundNews : list) {
            System.out.println(backgroundNews.getYear() + "  " + backgroundNews.getSortNum());
        }
    }
}