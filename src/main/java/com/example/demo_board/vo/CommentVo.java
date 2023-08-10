package com.example.demo_board.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentVo {
    
    int    comment_idx;
    String comment_content;
    String comment_ip;
    String comment_regdate;
    int    b_idx;
    int    mem_idx;
    String mem_id;
    String mem_name;
}
