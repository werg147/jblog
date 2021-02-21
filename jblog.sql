--테이블 생성----------------------------------------------------------------
--users 테이블
create table users(
    userNo number primary key,
    id varchar2(50) not null unique,
    userName varchar2(100) not null,
    password varchar2(50) not null,
    joinDate date not null
);

--blog 테이블
create table blog(
    id varchar2(50) primary key,
    blogTitle varchar2(200) not null,
    logoFile varchar2(200),
    constraint c_blog_fk foreign key(id)
    references users (id)
);

--category 테이블
create table category(
    cateNo number primary key,
    id varchar2(50),
    cateName varchar2(200) not null,
    description varchar2(500),
    regDate date not null,
    constraint c_category_fk foreign key(id)
    references blog (id)
);

--post 테이블
create table post(
    postNo number primary key,
    cateNo number,
    postTitle varchar2(300) not null,
    postContent varchar2(4000),
    regDate date not null,
    constraint c_post_fk foreign key(cateNo)
    references category (cateNo)
);

--comments 테이블
--추가과제할때 생성예정


--시퀀스----------------------------------------------------------------
--users 시퀀스
create sequence seq_users_no
increment by 1
start with 1
nocache;

--category 시퀀스
create sequence seq_category_no
increment by 1
start with 1
nocache;

--insert----------------------------------------------------------------
--user 회원가입
insert into users
values(seq_users_no.nextval, 
       'aaa', 
       '에이', 
       123, 
       sysdate
);

--blog정보 테스트
insert into blog
values('bbb', 'bbb', null);

--cate 등록
insert into category
values(
    seq_category_no.nextval,
    'bbb',
    '카테고리명 테스트',
    '카테고리 테스트 설명',
    sysdate
);



--select----------------------------------------------------------------
--users
select *
from users;

select *
from blog;

select *
from category;

--user로그인
select id,
       userNo,
       userName
from users
where id = 'aaa'
and password = 123;

--blog한개 정보 가져오기
select us.id,
       us.userName,
       bl.blogtitle,
       bl.logofile
from users us, blog bl
where us.id = bl.id;

--category 리스트 가져오기
select cateNo,
       id,
       cateName,
       description,
       regDate
from category
where id = 'bbb';

--category정보 1개 가져오기
select cateNo,
       id,
       cateName,
       description,
       regDate
from category
where cateNo = 3;

--update----------------------------------------------------------------
--blog 기본설정(블로그제목, 로고파일)
update blog
set blogtitle = '블로그타이틀',
    logofile = '파일경로'
where id = 'bbb';




-------------------------------------------------------------------------
commit;

rollback;

drop table blog;