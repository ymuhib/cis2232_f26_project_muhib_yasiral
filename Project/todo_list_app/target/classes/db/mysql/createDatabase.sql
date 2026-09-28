# For localhost
DROP DATABASE IF EXISTS cis2232_todo_list_app;
CREATE DATABASE cis2232_todo_list_app;
use cis2232_todo_list_app;

-- ------------------------------------------------------------------------------
-- Note the table below to hold data associated with the project.
-- ------------------------------------------------------------------------------

CREATE TABLE task (
                      id              INT             NOT NULL AUTO_INCREMENT,
                      taskName        VARCHAR(100)    NOT NULL,
                      description     VARCHAR(500)    NOT NULL,
                      dueDate         VARCHAR(10)     NOT NULL,
                      estimatedTime   INT             NOT NULL DEFAULT 0,
                      status          VARCHAR(30)     NOT NULL,
                      notes           VARCHAR(500),
                      category        VARCHAR(50)     NOT NULL,
                      priority        VARCHAR(20)     NOT NULL,
                      PRIMARY KEY (id)
);

INSERT INTO task
(taskName, description, dueDate, estimatedTime, status, notes, category, priority)
VALUES
-- Not started task
('Complete Java Assignment',
 'Finish the Java programming assignment.',
 '2026-09-30', 90, 'Not Started',
 'Review requirements before submitting.',
 'School', 'High'),

-- In progress task
('Study for Database Test',
 'Review SQL and database concepts.',
 '2026-10-02', 120, 'In Progress',
 'Practice SQL queries.',
 'School', 'High'),

-- Completed task
('Submit Literature Review',
 'Submit the completed literature review.',
 '2026-09-25', 60, 'Completed',
 'Assignment submitted.',
 'School', 'Medium'),

-- Personal task
('Buy Groceries',
 'Purchase groceries for the week.',
 '2026-09-29', 45, 'Not Started',
 'Buy milk and vegetables.',
 'Personal', 'Medium'),

-- Work task
('Complete Work Schedule',
 'Complete next weeks work schedule.',
 '2026-10-01', 30, 'Not Started',
 'Check availability.',
 'Work', 'Low'),

-- In progress task
('Work on Database Project',
 'Create and test the database.',
 '2026-10-05', 150, 'In Progress',
 'Test SQL statements.',
 'School', 'High'),

-- Personal task
('Clean Room',
 'Organize and clean the workspace.',
 '2026-10-03', 60, 'Not Started',
 'Clean the study area.',
 'Personal', 'Low'),

-- Completed work task
('Complete Work Training',
 'Complete required workplace training.',
 '2026-09-27', 75, 'Completed',
 'Training completed.',
 'Work', 'Medium');
