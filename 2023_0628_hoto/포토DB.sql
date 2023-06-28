/*

create sequence  seq_photo_p_idx 


create table photo
(
   p_idx   			int,							--일련(회원)번호
   p_subject  		varchar2(100) not null, 		--이름
   p_content		varchar2(100) not null,					--아이디
   p_filename		varchar2(100) not null,			--비밀번호
   p_ip 	 		varchar2(100) not null,			--우편번호
   p_regdate  		date  default sysdate,			--주소
   p_modifydate	 	varchar2(100) not null,	
   mem_idx			int
   
)


alter table photo
   add constraint pk_photo_p_idx  primary key(p_idx) ;


alter table photo
   add constraint fk_mem_idx  primary key(p_idx) ;


insert into photo values( seq_photo_p_idx.nextVal,
                           '사진관',
                           'admin',
                           '1234',
                           '12345',
                           '서울시 관악구 시흥대로 552',
                           '192.168.0.23',
                           sysdate,
                           '관리자'
                         ) ;
                         
                         
                         
*/