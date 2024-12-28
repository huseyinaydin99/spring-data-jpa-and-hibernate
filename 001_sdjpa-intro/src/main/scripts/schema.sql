drop table if exists books cascade;
drop table if exists authors;

create table books(
    id        bigint not null auto_increment primary key,
    isbn      varchar(255),
    publisher varchar(255),
    title     varchar(255),
    author_id BIGINT
) engine = InnoDB;

create table authors(
    id         bigint not null auto_increment primary key,
    first_name varchar(255),
    last_name  varchar(255)
) engine = InnoDB;

alter table books
    add constraint book_author_fk foreign key (author_id) references authors (id);

insert into authors (first_name, last_name) values ('Hüseyin', 'AYDIN');

insert into books (isbn, publisher, title, author_id) values ('471-6437291749', 'Hüseyin AYDIN',
       'Spring İle Yol Al',(select id from authors where first_name = 'Hüseyin' and last_name = 'AYDIN') );

insert into books (isbn, publisher, title, author_id) values ('471-6437291749', 'Hüseyin AYDIN',
    'Spring İle Yol Al',(select id from authors where first_name = 'Hüseyin' and last_name = 'AYDIN') );

insert into books (isbn, publisher, title, author_id) values ('471-6437291749', 'Hüseyin AYDIN',
    'Spring İle Yol Al',(select id from authors where first_name = 'Hüseyin' and last_name = 'AYDIN') );

insert into authors (first_name, last_name) values ('Hüseyin', 'AYDIN');

insert into books (isbn, publisher, title, author_id) values ('271-6437291749', 'Hüseyin AYDIN',
    'İş Güdümlü Tasarım',(select id from authors where first_name = 'Hüseyin' and last_name = 'AYDIN') );

insert into authors (first_name, last_name) values ('Hüseyin', 'AYDIN');

insert into books (isbn, publisher, title, author_id) values ('271-6437291749', 'Hüseyin AYDIN',
    'Temiz Temiz Kodlar Hacı',(select id from authors where first_name = 'Hüseyin' and last_name = 'AYDIN') );