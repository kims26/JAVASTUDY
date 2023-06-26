/*

--�Ϸù�ȣ
create sequence seq_member_mem_idx

--���̺�
create table member
(
   mem_idx   	int,							--�Ϸ�(ȸ��)��ȣ
   mem_name  	varchar2(100) not null, 		--�̸�
   mem_id	 	varchar2(100),					--���̵�
   mem_pwd	 	varchar2(100) not null,			--��й�ȣ
   mem_zipcode 	varchar2(100) not null,			--�����ȣ
   mem_addr  	varchar2(500) not null,			--�ּ�
   mem_ip	 	varchar2(100) not null,			--������
   mem_regdate 	date  default sysdate,			--��������
   mem_grade 	varchar2(100) default '�Ϲ�' 	--ȸ������(�Ϲ�/������)   
)


--�⺻Ű
alter table member
   add constraint pk_member_mem_idx  primary key(mem_idx) ;
   
--unique����
alter table member
   add constraint unique_member_mem_id unique(mem_id) ;




--sample data
insert into member values( seq_member_mem_idx.nextVal,
                           '�����',
                           'admin',
                           '1234',
                           '12345',
                           '����� ���Ǳ� ������ 552',
                           '192.168.0.23',
                           sysdate,
                           '������'
                         ) ;
                         
insert into member values( seq_member_mem_idx.nextVal,
                           '�ϱ浿',
                           'one',
                           '1234',
                           '12345',
                           '����� ���Ǳ� ������ 552',
                           '192.168.0.23',
                           sysdate,
                           '�Ϲ�'
                         ) ;
                         
--JDBC�� insert                         
insert into member values(seq_member_mem_idx.nextVal,?,?,?,?,?,?,sysdate,'�Ϲ�')
                                                  
commit                         


select * from member where mem_idx=2









*/