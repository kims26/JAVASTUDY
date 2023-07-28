package com.example.demo_sawon.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("sawon")
public class SawonVo {
    

    int sabun;
    String saname;
    String sasex;
    int deptno;
    String sajob;
    String sahire;
    int samgr;
    int sapay;

}
