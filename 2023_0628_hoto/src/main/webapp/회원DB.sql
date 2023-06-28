/*

--일련번호
create sequence seq_member_mem_idx

--테이블
create table member
(
   mem_idx   	int,							--일련(회원)번호
   mem_name  	varchar2(100) not null, 		--이름
   mem_id	 	varchar2(100),					--아이디
   mem_pwd	 	varchar2(100) not null,			--비밀번호
   mem_zipcode 	varchar2(100) not null,			--우편번호
   mem_addr  	varchar2(500) not null,			--주소
   mem_ip	 	varchar2(100) not null,			--아이피
   mem_regdate 	date  default sysdate,			--가입일자
   mem_grade 	varchar2(100) default '일반' 	--회원구분(일반/관리자)   
)


--기본키
alter table member
   add constraint pk_member_mem_idx  primary key(mem_idx) ;
   
--unique제약
alter table member
   add constraint unique_member_mem_id unique(mem_id) ;




--sample data
insert into member values( seq_member_mem_idx.nextVal,
                           '김관리',
                           'admin',
                           '1234',
                           '12345',
                           '서울시 관악구 시흥대로 552',
                           '192.168.0.23',
                           sysdate,
                           '관리자'
                         ) ;
                         
insert into member values( seq_member_mem_idx.nextVal,
                           '일길동',
                           'one',
                           '1234',
                           '12345',
                           '서울시 관악구 시흥대로 552',
                           '192.168.0.23',
                           sysdate,
                           '일반'
                         ) ;
                         
--JDBC용 insert                         
insert into member values(seq_member_mem_idx.nextVal,?,?,?,?,?,?,sysdate,'일반')
                                                  
commit                         


select * from member where mem_idx=2


update member set mem_name=?,mem_id=?,mem_pwd=?,mem_zipcode=?,mem_addr=?,mem_ip=?,mem_grade=?,mem_regdate=sysdate where mem_idx=?






*/