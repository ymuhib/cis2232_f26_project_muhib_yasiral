# To-Do List Application
Project repo for CIS 2232 - AOOP
***

## Development Team 
* **Business Client**: *Bivan Fedha*
* **Lead Developer**: *Yasir Al Muhib*
* **PM/QA**: *Peter Logan*
***

## Description
The To-Do List Application is a simple task management tool designed to help students organize their schoolwork and daily activities. Users can create, update, and delete tasks while tracking important details such as due dates, priorities, categories, and completion status. The application also calculates the total estimated time required to finish all unfinished tasks, helping users better manage their workload.
***

## Color 

* Main Color: Light Blue
* Secondary Color: TBD
***

## Required Fields 
| Field | Data Type | Description |
|---------|---------|-------------|
| id | int | Unique identifier for the task in the database |
| taskName | String | The name of the task |
| description | String | A short description of what needs to be done |
| dueDate | String | The date the task should be completed |
| estimatedTime | int | Estimated number of minutes required to complete the task |
| status | String | Current task status (Not Started, In Progress, or Completed) |
| notes | String | Additional information or comments about the task |
| category | String | Type of task (e.g., School, Work, Personal) |
| priority | String | Priority level of the task (e.g., High, Medium, Low) |
***



## Calculation ##
The application will calculate the total estimated time for unfinished tasks.
The calculation will add the estimated time of every task that is not marked as completed (undone).

Example:
30 minutes + 60 minutes + 45 minutes = 135 minutes of work remaining. 
The total can also be displayed in hours and minutes. 
***

## Report Details 
To be determined in a future sprint.
***
