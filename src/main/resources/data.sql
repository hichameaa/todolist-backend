INSERT INTO users (first_name, last_name, email, user_name, password, created_date, updated_date)
VALUES
    ('John', 'Doe', 'john.doe@mail.com', 'johndoe', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Jane', 'Smith', 'jane.smith@mail.com', 'janesmith', 'securepwd', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO category (category_id, name, description, created_date, updated_date, user_id)
VALUES
    (1, 'Personal', 'Personal tasks', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    (2, 'Work', 'Work-related tasks', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    (3, 'Home', 'Home tasks', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2);

INSERT INTO todo (todo_id, title, description, start_date, done, favorite, created_date, updated_date, category_id)
VALUES
    (1, 'Buy groceries', 'Milk, eggs, bread', CURRENT_TIMESTAMP, false, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    (2, 'Finish report', 'Due by Friday', CURRENT_TIMESTAMP, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    (3, 'Clean the house', 'Dust, vacuum, and mop', CURRENT_TIMESTAMP, true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3);

