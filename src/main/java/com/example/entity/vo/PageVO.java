package com.example.entity.vo;

import com.example.entity.User;
import lombok.Data;

import java.util.List;

// 封装查询结果的POJO
@Data
public class PageVO<T> {

    /**
     * 每页条数
     */
    private int size;
    /**
     * 页码
     */
    private int current;

    /**
     * 总页数
     */
    private int pages;
    /**
     * 总条数
     */
    private int total;

    /**
     * 当前页的数据
     */
    private List<T> records;

    public PageVO(int size, int current, int total, List<T> records) {
        this.size = size;
        this.current = current;
        this.total = total;
        this.records = records;
        this.pages = total / size + (total % size == 0 ? 0 : 1);
    }


    // 省略get/set方法
}
