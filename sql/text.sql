-- 공부하면서 데이터베이스 관련 쿼리 정리하는 메모장

drop table if exists member CASCADE;
create table member(
                       id bigint generated by default as identity,
                       name varchar(255),
                       primary key (id)
);


select * from member;

insert into member(name) values('spring')
