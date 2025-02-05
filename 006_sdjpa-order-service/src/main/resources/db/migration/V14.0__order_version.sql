alter table order_headers
    add column version integer;

alter table order_line
    add column version integer;