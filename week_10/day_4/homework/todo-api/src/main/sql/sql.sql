use nhn_academy_4;

DROP TABLE  event;
CREATE TABLE `event` (
                         `id` bigint NOT NULL COMMENT 'ai',
                         `subject` varchar(255) NOT NULL COMMENT '이벤트 명',
                         `user_id` varchar(255) NOT NULL COMMENT '유저 아이디',
                         `event_at` date NOT NULL COMMENT '이벤트 일자',
                         `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


ALTER TABLE `event`
    ADD PRIMARY KEY (`id`),
    ADD KEY `idx_user_id_event_at` (`user_id`,`event_at`) USING BTREE;

--
-- 덤프된 테이블의 AUTO_INCREMENT
--

--
-- 테이블의 AUTO_INCREMENT `event`
--
ALTER TABLE `event`
    MODIFY `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ai';
COMMIT;

