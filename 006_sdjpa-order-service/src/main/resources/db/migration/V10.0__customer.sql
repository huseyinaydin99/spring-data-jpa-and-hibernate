create table customers (
    id                 bigint not null auto_increment primary key,
    customer_name      varchar(50),
    address            varchar(30),
    city               varchar(30),
    state              varchar(30),
    zip_code           varchar(30),
    phone              varchar(20),
    email              varchar(255),
    created_date       timestamp,
    last_modified_date timestamp
);

alter table order_header
    add column customer_id bigint;

alter table order_header
    add constraint order_customer_fk
        foreign key (customer_id) references customers (id);

alter table order_header drop column customer;

insert into customers (customer_name, address, city, state, zip_code, phone, email)
    values ('Bekir Tekir', '51 Niğde', 'Niğde', 'TR', '51200', '0555 555 55 51',
            'bakirsikertenkelmeyenmengene31@hotmail.com' );

update order_header set order_header.customer_id = (select id from customers limit 1);