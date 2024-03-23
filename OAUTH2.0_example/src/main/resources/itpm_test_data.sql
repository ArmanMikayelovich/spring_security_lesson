INSERT INTO itpm.users (id, first_name, last_name, registration_time, email, password, is_active)
VALUES ('1', 'first 1', 'last 1', '2020-08-08', '1@mail.com', '123456789', 1);
INSERT INTO itpm.users (id, first_name, last_name, registration_time, email, password, is_active)
VALUES ('2', 'first 2', 'last 2', '2020-08-08', '2@mail.com', '123456789', 1);
INSERT INTO itpm.users (id, first_name, last_name, registration_time, email, password, is_active)
VALUES ('3', 'first 3', 'last 3', '2020-08-08', '3@mail.com', '123456789', 1);
INSERT INTO itpm.users (id, first_name, last_name, registration_time, email, password, is_active)
VALUES ('4', 'first 4', 'last 4', '2020-08-08', '4@mail.com', '123456789', 1);

INSERT INTO itpm.projects (id, name, description, created_at, fk_publisher)
VALUES ('PR_1', 'project 1', 'description for project 1', '2020-08-08 11:36:37', '1');
INSERT INTO itpm.projects (id, name, description, created_at, fk_publisher)
VALUES ('PR_2', 'project 2', 'description for project 2', '2020-08-08 11:36:37', '2');
INSERT INTO itpm.projects (id, name, description, created_at, fk_publisher)
VALUES ('PR_3', 'project 3', 'description for project 3', '2020-08-08 11:36:37', '3');
INSERT INTO itpm.projects (id, name, description, created_at, fk_publisher)
VALUES ('PR_4', 'project 4', 'description for project 4', '2020-08-08 11:36:37', '4');

INSERT INTO itpm.sprints (id, fk_project_id, fk_user_id, created_at, dead_line)
VALUES (1, 'PR_1', '1', '2020-08-08 11:36:37', '2020-08-15 11:36:37');
INSERT INTO itpm.sprints (id, fk_project_id, fk_user_id, created_at, dead_line)
VALUES (2, 'PR_2', '2', '2020-08-08 11:36:37', '2020-08-15 11:36:37');
INSERT INTO itpm.sprints (id, fk_project_id, fk_user_id, created_at, dead_line)
VALUES (3, 'PR_3', '3', '2020-08-08 11:36:38', '2020-08-15 11:36:38');
INSERT INTO itpm.sprints (id, fk_project_id, fk_user_id, created_at, dead_line)
VALUES (4, 'PR_4', '4', '2020-08-08 11:36:38', '2020-08-15 11:36:38');

INSERT INTO itpm.tasks (id, name, description, fk_creator_user_id, fk_assigned_user_id, fk_sprint_id, task_type,
                        task_state)
VALUES (1, 'Task: 1', 'Description or task: 1', '1', '2', 1, 'TASK', 'TODO');
INSERT INTO itpm.tasks (id, name, description, fk_creator_user_id, fk_assigned_user_id, fk_sprint_id, task_type,
                        task_state)
VALUES (2, 'Task: 2', 'Description or task: 2', '1', '3', 1, 'TASK', 'IN_PROGRESS');
INSERT INTO itpm.tasks (id, name, description, fk_creator_user_id, fk_assigned_user_id, fk_sprint_id, task_type,
                        task_state)
VALUES (3, 'Task: 3', 'Description or task: 3', '1', '4', 1, 'TASK', 'DONE');
INSERT INTO itpm.tasks (id, name, description, fk_creator_user_id, fk_assigned_user_id, fk_sprint_id, task_type,
                        task_state)
VALUES (4, 'Task: 4', 'Description or task: 4', '1', '1', 1, 'TASK', 'TODO');
INSERT INTO itpm.tasks (id, name, description, fk_creator_user_id, fk_assigned_user_id, fk_sprint_id, task_type,
                        task_state)
VALUES (5, 'Task: 5', 'Description or task: 5', '2', '2', 2, 'TASK', 'IN_PROGRESS');
INSERT INTO itpm.tasks (id, name, description, fk_creator_user_id, fk_assigned_user_id, fk_sprint_id, task_type,
                        task_state)
VALUES (6, 'Task: 6', 'Description or task: 6', '2', '3', 2, 'TASK', 'TODO');
INSERT INTO itpm.tasks (id, name, description, fk_creator_user_id, fk_assigned_user_id, fk_sprint_id, task_type,
                        task_state)
VALUES (7, 'Task: 7', 'Description or task: 7', '2', '4', 2, 'TASK', 'TODO');
INSERT INTO itpm.tasks (id, name, description, fk_creator_user_id, fk_assigned_user_id, fk_sprint_id, task_type,
                        task_state)
VALUES (8, 'Task: 8', 'Description or task: 8', '2', '1', 2, 'TASK', 'IN_PROGRESS');
INSERT INTO itpm.tasks (id, name, description, fk_creator_user_id, fk_assigned_user_id, fk_sprint_id, task_type,
                        task_state)
VALUES (9, 'Task: 9', 'Description or task: 9', '3', '2', 3, 'TASK', 'TODO');
INSERT INTO itpm.tasks (id, name, description, fk_creator_user_id, fk_assigned_user_id, fk_sprint_id, task_type,
                        task_state)
VALUES (10, 'Task: 10', 'Description or task: 10', '3', '3', 3, 'TASK', 'TODO');
INSERT INTO itpm.tasks (id, name, description, fk_creator_user_id, fk_assigned_user_id, fk_sprint_id, task_type,
                        task_state)
VALUES (11, 'Task: 11', 'Description or task: 11', '3', '4', 3, 'TASK', 'IN_PROGRESS');
INSERT INTO itpm.tasks (id, name, description, fk_creator_user_id, fk_assigned_user_id, fk_sprint_id, task_type,
                        task_state)
VALUES (12, 'Task: 12', 'Description or task: 12', '3', '1', 3, 'TASK', 'TODO');
INSERT INTO itpm.tasks (id, name, description, fk_creator_user_id, fk_assigned_user_id, fk_sprint_id, task_type,
                        task_state)
VALUES (13, 'Task: 13', 'Description or task: 13', '4', '2', 4, 'TASK', 'TODO');
INSERT INTO itpm.tasks (id, name, description, fk_creator_user_id, fk_assigned_user_id, fk_sprint_id, task_type,
                        task_state)
VALUES (14, 'Task: 14', 'Description or task: 14', '4', '3', 4, 'TASK', 'IN_PROGRESS');
INSERT INTO itpm.tasks (id, name, description, fk_creator_user_id, fk_assigned_user_id, fk_sprint_id, task_type,
                        task_state)
VALUES (15, 'Task: 15', 'Description or task: 15', '4', '4', 4, 'TASK', 'TODO');

INSERT INTO itpm.users_projects (id, fk_user_id, fk_project_id, role)
VALUES (1, '1', 'PR_1', 'ADMINISTRATORS');
INSERT INTO itpm.users_projects (id, fk_user_id, fk_project_id, role)
VALUES (2, '2', 'PR_1', 'DEVELOPERS');
INSERT INTO itpm.users_projects (id, fk_user_id, fk_project_id, role)
VALUES (3, '3', 'PR_1', 'DEVELOPERS');
INSERT INTO itpm.users_projects (id, fk_user_id, fk_project_id, role)
VALUES (4, '4', 'PR_1', 'DEVELOPERS');
INSERT INTO itpm.users_projects (id, fk_user_id, fk_project_id, role)
VALUES (5, '1', 'PR_2', 'DEVELOPERS');
INSERT INTO itpm.users_projects (id, fk_user_id, fk_project_id, role)
VALUES (6, '2', 'PR_2', 'ADMINISTRATORS');
INSERT INTO itpm.users_projects (id, fk_user_id, fk_project_id, role)
VALUES (7, '3', 'PR_2', 'DEVELOPERS');
INSERT INTO itpm.users_projects (id, fk_user_id, fk_project_id, role)
VALUES (8, '4', 'PR_2', 'DEVELOPERS');
INSERT INTO itpm.users_projects (id, fk_user_id, fk_project_id, role)
VALUES (9, '1', 'PR_3', 'DEVELOPERS');
INSERT INTO itpm.users_projects (id, fk_user_id, fk_project_id, role)
VALUES (10, '2', 'PR_3', 'DEVELOPERS');
INSERT INTO itpm.users_projects (id, fk_user_id, fk_project_id, role)
VALUES (11, '3', 'PR_3', 'ADMINISTRATORS');
INSERT INTO itpm.users_projects (id, fk_user_id, fk_project_id, role)
VALUES (12, '4', 'PR_3', 'DEVELOPERS');
INSERT INTO itpm.users_projects (id, fk_user_id, fk_project_id, role)
VALUES (13, '1', 'PR_4', 'DEVELOPERS');
INSERT INTO itpm.users_projects (id, fk_user_id, fk_project_id, role)
VALUES (14, '2', 'PR_4', 'DEVELOPERS');
INSERT INTO itpm.users_projects (id, fk_user_id, fk_project_id, role)
VALUES (15, '3', 'PR_4', 'DEVELOPERS');
INSERT INTO itpm.users_projects (id, fk_user_id, fk_project_id, role)
VALUES (16, '4', 'PR_4', 'ADMINISTRATORS');


INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('1', 1, 'Comment for task:1 From User: 1@mail.com', '2020-08-08 11:36:48', '2020-08-10 12:35:54');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('2', 1, 'Comment for task:1 From User: 2@mail.com', '2020-08-08 11:36:49', '2020-08-10 12:35:54');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('3', 1, 'Comment for task:1 From User: 3@mail.com', '2020-08-08 11:36:49', '2020-08-10 12:35:54');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('4', 1, 'Comment for task:1 From User: 4@mail.com', '2020-08-08 11:36:49', '2020-08-10 12:35:55');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('1', 2, 'Comment for task:2 From User: 1@mail.com', '2020-08-08 11:36:49', '2020-08-10 12:35:55');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('2', 2, 'Comment for task:2 From User: 2@mail.com', '2020-08-08 11:36:49', '2020-08-10 12:35:55');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('3', 2, 'Comment for task:2 From User: 3@mail.com', '2020-08-08 11:36:49', '2020-08-10 12:35:55');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('4', 2, 'Comment for task:2 From User: 4@mail.com', '2020-08-08 11:36:49', '2020-08-10 12:35:55');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('1', 3, 'Comment for task:3 From User: 1@mail.com', '2020-08-08 11:36:49', '2020-08-10 12:35:56');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('2', 3, 'Comment for task:3 From User: 2@mail.com', '2020-08-08 11:36:49', '2020-08-10 12:35:56');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('3', 3, 'Comment for task:3 From User: 3@mail.com', '2020-08-08 11:36:50', '2020-08-10 12:35:56');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('4', 3, 'Comment for task:3 From User: 4@mail.com', '2020-08-08 11:36:50', '2020-08-10 12:35:56');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('1', 4, 'Comment for task:4 From User: 1@mail.com', '2020-08-08 11:36:50', '2020-08-10 12:35:57');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('2', 4, 'Comment for task:4 From User: 2@mail.com', '2020-08-08 11:36:50', '2020-08-10 12:35:57');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('3', 4, 'Comment for task:4 From User: 3@mail.com', '2020-08-08 11:36:50', '2020-08-10 12:35:57');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('4', 4, 'Comment for task:4 From User: 4@mail.com', '2020-08-08 11:36:50', '2020-08-10 12:35:57');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('1', 5, 'Comment for task:5 From User: 1@mail.com', '2020-08-08 11:36:50', '2020-08-10 12:35:57');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('2', 5, 'Comment for task:5 From User: 2@mail.com', '2020-08-08 11:36:50', '2020-08-10 12:35:58');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('3', 5, 'Comment for task:5 From User: 3@mail.com', '2020-08-08 11:36:51', '2020-08-10 12:35:58');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('4', 5, 'Comment for task:5 From User: 4@mail.com', '2020-08-08 11:36:51', '2020-08-10 12:35:58');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('1', 6, 'Comment for task:6 From User: 1@mail.com', '2020-08-08 11:36:51', '2020-08-10 12:35:58');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('2', 6, 'Comment for task:6 From User: 2@mail.com', '2020-08-08 11:36:51', '2020-08-10 12:35:58');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('3', 6, 'Comment for task:6 From User: 3@mail.com', '2020-08-08 11:36:51', '2020-08-10 12:35:58');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('4', 6, 'Comment for task:6 From User: 4@mail.com', '2020-08-08 11:36:51', '2020-08-10 12:35:58');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('1', 7, 'Comment for task:7 From User: 1@mail.com', '2020-08-08 11:36:51', '2020-08-10 12:35:58');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('2', 7, 'Comment for task:7 From User: 2@mail.com', '2020-08-08 11:36:52', '2020-08-10 12:35:58');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('3', 7, 'Comment for task:7 From User: 3@mail.com', '2020-08-08 11:36:52', '2020-08-10 12:35:58');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('4', 7, 'Comment for task:7 From User: 4@mail.com', '2020-08-08 11:36:52', '2020-08-10 12:35:59');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('1', 8, 'Comment for task:8 From User: 1@mail.com', '2020-08-08 11:36:52', '2020-08-10 12:35:59');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('2', 8, 'Comment for task:8 From User: 2@mail.com', '2020-08-08 11:36:52', '2020-08-10 12:35:59');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('3', 8, 'Comment for task:8 From User: 3@mail.com', '2020-08-08 11:36:52', '2020-08-10 12:35:59');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('4', 8, 'Comment for task:8 From User: 4@mail.com', '2020-08-08 11:36:52', '2020-08-10 12:36:00');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('1', 9, 'Comment for task:9 From User: 1@mail.com', '2020-08-08 11:36:53', '2020-08-10 12:36:00');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('2', 9, 'Comment for task:9 From User: 2@mail.com', '2020-08-08 11:36:53', '2020-08-10 12:36:00');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('3', 9, 'Comment for task:9 From User: 3@mail.com', '2020-08-08 11:36:53', '2020-08-10 12:36:00');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('4', 9, 'Comment for task:9 From User: 4@mail.com', '2020-08-08 11:36:53', '2020-08-10 12:36:00');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('1', 10, 'Comment for task:10 From User: 1@mail.com', '2020-08-08 11:36:53', '2020-08-10 12:36:00');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('2', 10, 'Comment for task:10 From User: 2@mail.com', '2020-08-08 11:36:53', '2020-08-10 12:36:00');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('3', 10, 'Comment for task:10 From User: 3@mail.com', '2020-08-08 11:36:53', '2020-08-10 12:36:00');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('4', 10, 'Comment for task:10 From User: 4@mail.com', '2020-08-08 11:36:53', '2020-08-10 12:36:00');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('1', 11, 'Comment for task:11 From User: 1@mail.com', '2020-08-08 11:36:54', '2020-08-10 12:36:01');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('2', 11, 'Comment for task:11 From User: 2@mail.com', '2020-08-08 11:36:54', '2020-08-10 12:36:01');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('3', 11, 'Comment for task:11 From User: 3@mail.com', '2020-08-08 11:36:54', '2020-08-10 12:36:01');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('4', 11, 'Comment for task:11 From User: 4@mail.com', '2020-08-08 11:36:54', '2020-08-10 12:36:01');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('1', 12, 'Comment for task:12 From User: 1@mail.com', '2020-08-08 11:36:54', '2020-08-10 12:36:01');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('2', 12, 'Comment for task:12 From User: 2@mail.com', '2020-08-08 11:36:54', '2020-08-10 12:36:01');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('3', 12, 'Comment for task:12 From User: 3@mail.com', '2020-08-08 11:36:54', '2020-08-10 12:36:02');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('4', 12, 'Comment for task:12 From User: 4@mail.com', '2020-08-08 11:36:54', '2020-08-10 12:36:02');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('1', 13, 'Comment for task:13 From User: 1@mail.com', '2020-08-08 11:36:55', '2020-08-10 12:36:02');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('2', 13, 'Comment for task:13 From User: 2@mail.com', '2020-08-08 11:36:55', '2020-08-10 12:36:02');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('3', 13, 'Comment for task:13 From User: 3@mail.com', '2020-08-08 11:36:55', '2020-08-10 12:36:02');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('4', 13, 'Comment for task:13 From User: 4@mail.com', '2020-08-08 11:36:55', '2020-08-10 12:36:02');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('1', 14, 'Comment for task:14 From User: 1@mail.com', '2020-08-08 11:36:55', '2020-08-10 12:36:02');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('2', 14, 'Comment for task:14 From User: 2@mail.com', '2020-08-08 11:36:55', '2020-08-10 12:36:03');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('3', 14, 'Comment for task:14 From User: 3@mail.com', '2020-08-08 11:36:55', '2020-08-10 12:36:03');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('4', 14, 'Comment for task:14 From User: 4@mail.com', '2020-08-08 11:36:55', '2020-08-10 12:36:03');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('1', 15, 'Comment for task:15 From User: 1@mail.com', '2020-08-08 11:36:55', '2020-08-10 12:36:03');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('2', 15, 'Comment for task:15 From User: 2@mail.com', '2020-08-08 11:36:56', '2020-08-10 12:36:03');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('3', 15, 'Comment for task:15 From User: 3@mail.com', '2020-08-08 11:36:56', '2020-08-10 12:36:03');
INSERT INTO itpm.comments (fk_user_id, fk_task_id, text, creation_timestamp, update_timestamp)
VALUES ('4', 15, 'Comment for task:15 From User: 4@mail.com', '2020-08-08 11:36:56', '2020-08-10 12:36:03');