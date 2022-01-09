--#테이블 출력
select  no,
        name,
        password,
        content,
        to_char(reg_date, 'yyyy-mm-dd hh:mi:ss') reg_date
from guestbook
order by reg_date desc;

-------------------------------------------
--#테이블 삭제
drop table guestbook;
--#시퀀스 삭제
drop sequence seq_guestbook_no;

--#테이블 생성
create table guestbook(
        no number(10),
        name varchar2(80),
        password varchar2(20),
        content varchar2(2000),
        reg_date date,
        primary key (no) 
);

--#시퀀스 생성
create sequence seq_guestbook_no
increment by 1
start with 1
nocache
;

commit;
-----------------------------------------------
--#데이터 입력 insert
insert into guestbook
values(seq_guestbook_no.nextval, '장서윤','1234','장서윤방문했습니다',sysdate);

insert into guestbook
values(seq_guestbook_no.nextval, '홍길동','2345','홍길동방문했습니다',sysdate);

insert into guestbook
values(seq_guestbook_no.nextval, '이승기','3456','이승기방문했습니다',sysdate);

commit;

--#데이터 삭제 delete
delete from guestbook
where no = 1 
and password = '1234' ;

--#password, no 출력
select  no,
        password
from guestbook;