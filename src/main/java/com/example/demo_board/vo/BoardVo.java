package com.example.demo_board.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVo {
    
    int    b_idx;
    String b_subject;
    String b_content;
    String b_ip;
    String b_regdate;
    int    b_readhit;
    String b_use;
    int    mem_idx;
    String mem_name;
    int    b_ref;
    int    b_step;
    int    b_depth;

}
