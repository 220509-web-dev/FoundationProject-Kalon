set search_path to shoe_collection_app;

-- Create some base user statuses
insert into user_account_level (status) values ('Admin'), ('Basic'), ('Premium');

-- Create a new Shoe Collector user
insert into app_users (first_name, last_name, birth_date, email, username, pword, status_id)
values ('Kalon', 'Penagraph', '1997-10-01','kdp131@txstate.edu', 'kdp131', 'Password', 1),
('Test1', 'Test1', '1001-01-01','test1@txstate.edu', 'test1', 'Password1', 2),
('Test2', 'Test2', '2002-02-02','test2@txstate.edu', 'test2', 'Password2', 3),
('Test3', 'Test3', '3003-03-03','test3@txstate.edu', 'test3', 'Password3', 2);

-- Create some categories for shoes
insert into categories (name) 
values ('Basketball'), ('Lifestyle'), ('Running'), ('Boots'), ('Sandals'), ('Other');

-- Create shoes made by the newly created user
insert into shoes (shoe_brand, main_color, shoe_size, owner_id, category_id)
values 
  ('Nike','White', 11, 1, 1),
  ('Adidas','Blue', 11, 2, 2),
  ('Jordan','Green', 9, 3, 1),
  ('Justin','Black', 12, 2, 4),
  ('Adidas','Yellow', 12, 1, 3),
  ('Adidas','Gray', 9, 3, 3),
  ('Crocs','Multi', 12, 2, 5);
	
-- Create shoe list belonging to the users
insert into shoe_list (name, owner_id)
values 
  ('Nike Shoe List', 1),
  ('Justin Boot List', 2),
  ('Crocs', 2);

-- Add the new shoes to the newly created shoe list
insert into shoe_list_shoes (list_id, shoe_id)
values
  (1, 1), 
  (2, 4), 
  (3, 7);
   
-- Get user by username
select * from app_users where username = 'test1';

-- Get users by birth date
select * from app_users where birth_date = '1997-10-01';

-- Get user by username and password 
select * from app_users where username = 'test2' and pword = 'Password2';

-- Get shoes where the size is greater than 10 and main color is white
select * from shoes where shoe_size > 10 and main_color = 'White';

-- Get all Premium users
select * from app_users where status_id = 3;

-- Get shoes belonging to user with id 1 and sort them by their size.
select * 
from shoes
where owner_id = 1
order by shoe_size;
