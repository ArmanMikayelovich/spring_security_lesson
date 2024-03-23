
-- users: table
CREATE TABLE `users`
(
    `id`                varchar(255) NOT NULL,
    `first_name`        varchar(255) NOT NULL,
    `last_name`         varchar(255) NOT NULL,
    `registration_time` date       DEFAULT NULL,
    `email`             varchar(255) NOT NULL,
    `password`          varchar(255) NOT NULL,
    `is_active`         tinyint(1) DEFAULT '0',
    PRIMARY KEY (`id`),
    UNIQUE KEY `users_id_index` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- projects: table
CREATE TABLE `projects`
(
    `id`           varchar(255) NOT NULL,
    `name`         varchar(255) NOT NULL,
    `description`  varchar(2500)         DEFAULT NULL,
    `created_at`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `fk_publisher` varchar(255)          DEFAULT NULL,
    UNIQUE KEY `projects_id_uindex` (`id`),
    KEY `fk_publisher` (`fk_publisher`),
    CONSTRAINT `fk_publisher` FOREIGN KEY (`fk_publisher`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- sprints: table
CREATE TABLE `sprints`
(
    `id`            bigint       NOT NULL AUTO_INCREMENT,
    `fk_project_id` varchar(255) NOT NULL,
    `fk_user_id`    varchar(255) NOT NULL,
    `created_at`    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `dead_line`     datetime     NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `sprints_id_uindex` (`id`),
    KEY `sprint_user_id_FK` (`fk_user_id`),
    KEY `sprints_projects_id_fk` (`fk_project_id`),
    CONSTRAINT `sprint_user_id_FK` FOREIGN KEY (`fk_user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `sprints_projects_id_fk` FOREIGN KEY (`fk_project_id`) REFERENCES `projects` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- tasks: table
CREATE TABLE `tasks`
(
    `id`                  bigint       NOT NULL AUTO_INCREMENT,
    `name`                varchar(255) NOT NULL,
    `description`         varchar(2500) DEFAULT NULL,
    `fk_creator_user_id`  varchar(255) NOT NULL,
    `fk_assigned_user_id` varchar(255) NOT NULL,
    `fk_sprint_id`        bigint       NOT NULL,
    `task_type`           varchar(50)  NOT NULL,
    `task_state`          varchar(50)  NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_asigned_user_id` (`fk_assigned_user_id`),
    KEY `fk_creator_user_id` (`fk_creator_user_id`),
    KEY `fk_sprint_id` (`fk_sprint_id`),
    CONSTRAINT `fk_asigned_user_id` FOREIGN KEY (`fk_assigned_user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_creator_user_id` FOREIGN KEY (`fk_creator_user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_sprint_id` FOREIGN KEY (`fk_sprint_id`) REFERENCES `sprints` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 40
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- users_projects: table
CREATE TABLE `users_projects`
(
    `id`            bigint       NOT NULL AUTO_INCREMENT,
    `fk_user_id`    varchar(255) NOT NULL,
    `fk_project_id` varchar(255) NOT NULL,
    `role`          varchar(50)  NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `users_projects_id_uindex` (`id`),
    KEY `fk_project_id` (`fk_project_id`),
    KEY `fl_user_id` (`fk_user_id`),
    CONSTRAINT `fk_project_id` FOREIGN KEY (`fk_project_id`) REFERENCES `projects` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fl_user_id` FOREIGN KEY (`fk_user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 19
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- comments: table
CREATE TABLE `comments`
(
    `id`                 bigint        NOT NULL AUTO_INCREMENT,
    `fk_user_id`         varchar(255)  NOT NULL,
    `fk_task_id`         bigint        NOT NULL,
    `text`               varchar(1000) NOT NULL,
    `creation_timestamp` datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_timestamp`   datetime               DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `comments_id_uindex` (`id`),
    KEY `fk_user_id` (`fk_user_id`),
    KEY `fk_task_id` (`fk_task_id`),
    CONSTRAINT `fk_task_id` FOREIGN KEY (`fk_task_id`) REFERENCES `tasks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_user_id` FOREIGN KEY (`fk_user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;