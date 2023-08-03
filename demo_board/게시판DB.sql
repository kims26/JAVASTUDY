

--게시판 일련번호
create sequence seq_board_b_idx

--테이블 생성
create table board
(
   b_idx     int,                   --일련번호
   b_subject varchar2(500),         --제목
   b_content clob,                  --내용
   b_ip      varchar2(100),         --아이피
   b_regdate date,                  --작성일자
   b_readhit int default 0,         --조회수
   b_use     char(1) default 'y',   --사용유무
   mem_idx   int,                   --회원번호
   mem_name  varchar2(200),         --작성자명
   b_ref     int,                   --메인글번호
   b_step    int,                   --글순서
   b_depth   int                    --글깊이
)

--기본키
alter table board
  add constraint pk_board_b_idx primary key(b_idx);

--외래키
alter table board
  add constraint fk_board_mem_idx foreign key(mem_idx)
                                  references member(mem_idx) ;


select * from member






--sample data 
--새글쓰기
insert into board values(
                  seq_board_b_idx.nextVal, 
                  '내가 1등이다',
                  '이번에도 1등이네',
                  '192.168.0.23',
                  sysdate,
                  0,
                  'y',
                  2,
                  '일길동',
                  seq_board_b_idx.currVal,
                  0,
                  0
);

--답글쓰기
insert into board values(
                  seq_board_b_idx.nextVal, 
                  '아깝네',
                  '내가 1등할 수 있었ㄴㄴ데',
                  '192.168.0.23',
                  sysdate,
                  0,
                  'y',
                  4,
                  '홍길동',
                  1,
                  1,
                  1
);


--조회
select b_idx,b_subject,b_readhit,b_ip,b_regdate from board






