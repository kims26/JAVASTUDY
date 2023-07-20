/*

--��ٱ��� �Ϸù�ȣ
create sequence seq_cart_c_idx

--��ٱ��� ���̺�
create table cart
(
  c_idx   int,
  c_cnt   int  not null,
  p_idx   int,
  mem_idx int
)

--�⺻Ű
alter table cart
   add constraint pk_cart_c_idx primary key(c_idx) ;

--��ǰ���̺�(product)�� idx�� p_idx���� �ܷ�Ű ����
alter table cart
  add constraint fk_cart_p_idx foreign key(p_idx) 
                               references product(p_idx)
                               on delete cascade
                               
                    -- on delete cascade
                    -- parent������ �����ϴ� child��ϵ� ���� �������Ѷ�

alter table cart
  add constraint fk_cart_mem_idx foreign key(mem_idx)
                                 references member(mem_idx)
                                 on delete cascade


select * from product
select * from member

insert into cart values(seq_cart_c_idx.nextVal,1,1,2);
insert into cart values(seq_cart_c_idx.nextVal,1,2,2);

insert into cart values(seq_cart_c_idx.nextVal,1,2,4);
insert into cart values(seq_cart_c_idx.nextVal,1,3,5);

select * from cart

commit

-- Join�� ���ؼ� ��ȸ������ ����
create or replace view cart_view
as
	select
	   c_idx, p.p_idx, p_num,p_name,p_price,p_saleprice,
	   c_cnt, c_cnt* p_saleprice amount,mem_idx
	from product p inner join  cart c on p.p_idx = c.p_idx  

select * from cart_view where mem_idx=2

--��ٱ��� ��ǰ�� �Ѱ�
select nvl(sum(amount),0) from cart_view where mem_idx=2













*/