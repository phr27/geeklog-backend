package com.geeklog.common.util;

import com.geeklog.common.exception.ValidatorException;

/**
 * @author 潘浩然
 * 创建时间 2018/09/12
 * 功能：分页工具类
 */
public class PageUtil {

    public static int getTotalPage(int total, int size) {
        Validator.min(total, 0, ValidatorException.unexpected("PageUtil.getTotalPage(total 不能小于 0)"));
        Validator.min(size, 1, ValidatorException.unexpected("PageUtil.getTotalPage(size 不能小于 1)"));

        int totalPage = total / size;
        if (total % size != 0) {
            totalPage++;
        }
        if (totalPage == 0) {
            totalPage = 1;
        }

        return totalPage;
    }
}
