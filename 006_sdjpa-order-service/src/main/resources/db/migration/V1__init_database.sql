drop table if exists order_headers cascade;

create table order_headers(
    id        bigint not null auto_increment primary key,
    customer  varchar(255)
) engine = InnoDB;
