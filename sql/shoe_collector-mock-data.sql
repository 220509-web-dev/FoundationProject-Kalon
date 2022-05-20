set search_path to shoe_collection_app;

-- Create some base user statuses
insert into user_status (status) values ('ADMIN'), ('BASIC_USER'), ('PREMIUM_USER');

-- Create a new Shoe Collector user
insert into app_users (first_name, last_name, email, username, pword, status_id)
values ('Kalon', 'Penagraph', 'kdp131@txstate.edu', 'kdp131', 'Pa$$word', 1),
('Test', 'Test', 'test@txstate.edu', 'test', 'TestPa$$word', 2);

-- Create some categories for shoes
insert into categories (name) 
values ('Basketball'), ('Lifestyle'), ('Running'), ('Boots');

-- Create some shoes made by the newly created user
insert into shoes (shoe_brand, model_number, shoe_size, owner_id, category_id)
values 
  ('Nike','Basketball-1', 11, 1, 1),
  ('Justin','Boots-1', 12, 1, 4),
  ('Adidas','Lifestyle-1', 11, 1, 2),
  ('Jordan','Basketball-2', 11, 1, 1),
  ('Adidas','Running-2', 11, 1, 3),
  ('Adidas','Running-2', 11, 1, 3);
	
-- Create shoe list belonging to the new user
insert into shoe_list (name, owner_id)
values 
  ('Nike Shoe List', 1),
  ('Justin Boot List', 1),
  ('Adidas', 1);

-- Add the new shoes to the newly created shoe list
insert into shoe_list_shoes 
values
  (1, 1), (2,2), (3,3);
   
-- Get user by username
select * from app_users where username = 'kdp131';

-- Get user by email
select * from app_users where email = 'kdp131@txstate.edu';

-- Get user by username and password 
select * from app_users where username = 'kdp131' and pword = 'Pa$$word';

-- Get shoes where the size is greater than 10
select * from shoes where shoe_size > 10;


-- Get shoes belonging to user with id 1 and sort them by their size.
select * 
from shoes
where owner_id = 1
order by shoe_size; -- asc (ascending is implicity, use desc for descending order)
 






