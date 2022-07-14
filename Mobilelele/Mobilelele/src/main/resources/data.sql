
# INSERT INTO brands (id, name)
# VALUES (1, 'Ford'),
#        (2, 'Toyota');

insert into users(first_name, last_name, password, email, created, modified, is_active)
values ('Medin', 'Tarakchiev', '12345', 'medin123@abv.bg', '2012/12/12', '2012/12/12', true);

insert into users_role
values (2, 3);

# INSERT INTO models (id, name, category, start_year, end_year, brand_id, image_url)
# VALUES (1, 'Fiesta', 'CAR', 1976, 2006, 1, 'https://upload.wikimedia.org/wikipedia/commons/7/7d/2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg'),
#        (2, 'Escort', 'CAR', 1968, 2000, 1, 'https://www.auto-data.net/images/f110/Ford-Escort-VII-Hatch-GAL-AFL.jpg'),
#        (3, 'Yaris', 'CAR', 1999, 2018, 2, 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/2020_Toyota_Yaris_Design_HEV_CVT_1.5_Front.jpg/1280px-2020_Toyota_Yaris_Design_HEV_CVT_1.5_Front.jpg');