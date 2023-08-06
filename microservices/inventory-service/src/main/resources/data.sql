--test data

insert into product_type (id, parent_product_id, type)
values (1, null, 'Kitchen');
insert into product_type (id, parent_product_id, type)
values (2, null, 'Bathroom');
insert into product_type (id, parent_product_id, type)
values (3, null, 'Bedroom');
insert into product_type (id, parent_product_id, type)
values (4, 1, 'Table');
insert into product_type (id, parent_product_id, type)
values (5, 2, 'Bath');

/*insert into products (id, name, type_id, quantity, price)
values (1, 'Plastic table', 3, 50, 500);
insert into products (id, name, type_id, quantity, price)
values (2, 'Jacuzzi', 4, 2, 1500);*/



