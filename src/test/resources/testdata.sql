INSERT INTO authorities (id, username, authority, nickname, birthday, status, profile)
VALUES (4, "test@xxx.co.jp", "$2a$10$GidMYSDKXr43.ItokilNPeQbFYczwwpC6x5W3qy2L7AYCRoDmPEUi", "テストユーザー", "2001/12/01", "社会人", "テストユーザーです");	

INSERT INTO chara (id, kind)
VALUES (50, "仮の性格");

INSERT INTO chara (id, kind)
VALUES (51, "仮の性格２");	

INSERT INTO tweet (id, text, user_id, character_id, created_at)
VALUES (98, "テストの投稿-2", 4, 50, "2020-05-24 12:54:47");	

INSERT INTO tweet (id, text, user_id, character_id, created_at)
VALUES (99, "テストの投稿-1", 4, 50, "2020-06-01 12:54:47");	

INSERT INTO tweet (id, text, user_id, character_id, created_at)
VALUES (100, "テストの投稿", 4, 50, "2020-06-25 18:39:27");

INSERT INTO tweet (id, text, user_id, character_id, created_at)
VALUES (102, "テストの投稿2", 4, 50, "2020-07-01 12:43:58");	

INSERT INTO comment (id, text, tweet_id, user_id, created_at)
VALUES (100, "テストのコメント", 100, 4, "2020-06-25 18:39:27");

INSERT INTO comment (id, text, tweet_id, user_id, created_at)
VALUES (101, "テストのコメント2", 100, 4, "2020-07-01 12:43:58");

