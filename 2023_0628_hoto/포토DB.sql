/*

create sequence  seq_photo_p_idx 


create table photo
(
   p_idx   			int,							--�Ϸ�(ȸ��)��ȣ
   p_subject  		varchar2(100) not null, 		--�̸�
   p_content		varchar2(100) not null,					--���̵�
   p_filename		varchar2(100) not null,			--��й�ȣ
   p_ip 	 		varchar2(100) not null,			--�����ȣ
   p_regdate  		date  default sysdate,			--�ּ�
   p_modifydate	 	varchar2(100) not null,	
   mem_idx			int
   
)


alter table photo
   add constraint pk_photo_p_idx  primary key(p_idx) ;


alter table photo
   add constraint fk_mem_idx  primary key(p_idx) ;


insert into photo values( seq_photo_p_idx.nextVal,
                           '������',
                           'admin',
                           '1234',
                           '12345',
                           '����� ���Ǳ� ������ 552',
                           '192.168.0.23',
                           sysdate,
                           '������'
                         ) ;
                         
                         
                         
*/