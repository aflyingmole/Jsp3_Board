create table myboard
(
    num number(5) primary key,
    writer varchar2(30),
    title varchar2(30),
    content varchar2(4000),
    regdate date
);

create sequence myboard_seq;

insert into myboard values(myboard_seq.nextval,'test','hello','비가 오네요...',sysdate);
commit;