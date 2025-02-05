alter table products
   add column quantity_on_hand integer default 0;

update products
    set product.quantity_on_hand = 10;