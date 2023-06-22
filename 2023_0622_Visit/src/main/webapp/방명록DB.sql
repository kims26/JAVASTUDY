/*

--일련번호 관리객체
create sequence  seq_visit_idx



--테이블생성

create table visit
(
	idx     int,			 			--일련번호
	name    varchar2(100)  not null,	--작성자
	content varchar2(2000) not null,	--내용
	pwd		varchar2(100)  not null,	--비밀번호
	ip		varchar2(100)  not null,	--IP
	regdate date   default sysdate		--작성일자  
)

--primary key
alter table visit
  add constraint  pk_visit_idx primary key(idx);

--sample data
insert into visit values( seq_visit_idx.nextVal,
                          '일길동',
                          '내가 1등이다~~',
                          '1234',
                          '192.168.0.23',
                          sysdate
                        ) ;

insert into visit values( seq_visit_idx.nextVal,
                          '이길동',
                          '아쉽네 내가 1등할 수 있었는데',
                          '1234',
                          '192.168.0.23',
                          sysdate
                        ) ;
--JDBC SQL(DAO)
insert into visit values(seq_visit_idx.nextVal, ? , ? ,? , ? , sysdate)                        
                        
                        
select * from visit where idx=5 




*/