--��ѯ�ñ��ע��
select * from user_tab_comments where table_name = 'T_ASSET'
--��ѯ�ñ�������ֶμ��ֶ�ע��
select * from user_col_comments where table_name = 'T_ASSET'
select * from user_col_comments where table_name = 'T_ASSET2'
select * from T_ASSET2
delete from T_ASSET2

select a.id,a.num,a.name,
b.name typeid,to_char(a.buydate,'yyyy-MM-dd') buydate ,
d.realname userid,a.price,c.name factory,
--decode(a.status,'1','�ڿ�','2','����','����') status,
a.content  from t_asset a,t_assettype b,t_factory c,t_user d 
where a.typeid =b.id(+) and a.userid =d.id(+) 
and a.factory =c.id(+)