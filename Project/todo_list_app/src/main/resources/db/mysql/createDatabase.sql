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
                      priorityScore   INT             NOT NULL,
                      PRIMARY KEY (id)
);

INSERT INTO task
(taskName, description, dueDate, estimatedTime, status, notes, category, priority,priorityScore)
VALUES
-- Not started task
('Complete Java Assignment',
 'Finish the Java programming assignment.',
 '2026-09-30', 90, 'undone',
 'Review requirements before submitting.',
 'School', 'High', 270),

-- In progress task
('Study for Database Test',
 'Review SQL and database concepts.',
 '2026-10-02', 120, 'undone',
 'Practice SQL queries.',
 'School', 'High', 360),

-- Completed task
('Submit Literature Review',
 'Submit the completed literature review.',
 '2026-09-25', 60, 'done',
 'Assignment submitted.',
 'School', 'Medium', 120),

-- Personal task
('Buy Groceries',
 'Purchase groceries for the week.',
 '2026-09-29', 45, 'undone',
 'Buy milk and vegetables.',
 'Personal', 'Medium', 90),

-- Work task
('Complete Work Schedule',
 'Complete next weeks work schedule.',
 '2026-10-01', 30, 'done',
 'Check availability.',
 'Work', 'Low', 30),

-- In progress task
('Work on Database Project',
 'Create and test the database.',
 '2026-10-05', 150, 'undone',
 'Test SQL statements.',
 'School', 'High', 450),

-- Personal task
('Clean Room',
 'Organize and clean the workspace.',
 '2026-10-03', 60, 'done',
 'Clean the study area.',
 'Personal', 'Low', 60),

-- Completed work task
('Complete Work Training',
 'Complete required workplace training.',
 '2026-09-27', 75, 'done',
 'Training completed.',
 'Work', 'Medium', 150),

-- School task
('Prepare Project Presentation',
 'Create presentation slides for the final project.',
 '2026-10-07', 120, 'undone',
 'Add screenshots and diagrams.',
 'School', 'High', 360),

-- School task
('Read Chapter 8',
 'Read and summarize chapter 8.',
 '2026-10-06', 60, 'undone',
 'Focus on design patterns.',
 'School', 'Medium', 120),

-- Personal task
('Go to Gym',
 'Complete workout session.',
 '2026-10-04', 90, 'done',
 'Leg day workout.',
 'Personal', 'Low', 90),

-- Work task
('Team Status Meeting',
 'Attend weekly team meeting.',
 '2026-10-05', 45, 'done',
 'Discuss project updates.',
 'Work', 'Medium', 90),

-- School task
('Complete AOOP Lab',
 'Finish assigned lab exercises.',
 '2026-10-08', 100, 'undone',
 'Test all functionality.',
 'School', 'High', 300),

-- Personal task
('Pay Internet Bill',
 'Pay monthly internet service bill.',
 '2026-10-09', 15, 'undone',
 'Use online banking.',
 'Personal', 'Low', 15),

-- Work task
('Update Documentation',
 'Revise and update project documentation.',
 '2026-10-10', 80, 'undone',
 'Check formatting and spelling.',
 'Work', 'Medium', 160),

-- School task
('Practice SQL Queries',
 'Work through additional SQL exercises.',
 '2026-10-11', 70, 'undone',
 'Focus on joins and subqueries.',
 'School', 'High', 210),

-- Personal task
('Family Dinner',
 'Attend family dinner gathering.',
 '2026-10-12', 120, 'done',
 'Bring dessert.',
 'Personal', 'Medium', 240),

-- Work task
('Review Bug Reports',
 'Analyze and prioritize reported bugs.',
 '2026-10-13', 110, 'undone',
 'Prepare fixes for critical issues.',
 'Work', 'High', 330),

-- School task
('Submit Project Report',
 'Finalize and submit the project report.',
 '2026-10-15', 180, 'undone',
 'Review before submission.',
 'School', 'High', 540),

-- Personal task
('Clean Apartment',
 'Deep clean apartment and workspace.',
 '2026-10-14', 90, 'done',
 'Organize desk area.',
 'Personal', 'Low', 90);
