package cn.quyf.demo.gof.builder;

import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author quyf
 * @date 2020/5/25 11:31
 * @desc TODO
 **/
@Builder
public class User implements Serializable {
    private Integer id;
    private String name;
    private String address;
}
