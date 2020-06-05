package cn.quyf.demo.gof.builder;

import lombok.Data;

import java.io.Serializable;

/**
 * @author quyf
 * @date 2020/5/25 11:34
 * @desc TODO
 **/
@Data
public class Person implements Serializable {
    private Integer id;
    private String name;
    private String address;
}
