select a.id,a.receivestatus,b.title,b.content,b.sendtime,b.sender 
from t_mail_receiver a left join t_mail b 
on a.mailid = b.id where 
a.reveiverid = 61
and a.receivestatus = 'δ��' or a.receivestatus ='�Ѷ�' 
order by a.receivestatus asc

select * from t_mail_receiver for update
select * from t_mail t for update
select * from t_user for update

select id,title,content,sendtime,sender from t_mail 
where sendestatus = 'ɾ��' 
union all 
select b.id,b.title,b.content,b.sendtime,b.sender 
from t_mail_receiver a left join t_mail b 
on a.mailid = b.id where a.reveiverid = 61
and a.receivestatus = 'ɾ��'
