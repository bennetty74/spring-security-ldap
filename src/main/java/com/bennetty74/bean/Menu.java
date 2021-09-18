package com.bennetty74.bean;

import lombok.Data;

/**
 * @author Bennetty74
 * @date 2021.9.18
 */
@Data
public class Menu {
    private Integer id;
    private String name;
    private String icon;
    private String url;
    private Integer order;
    private Integer parentId;
}
