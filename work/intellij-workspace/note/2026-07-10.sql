CREATE TABLE tbl_attachment (
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id        int NOT NULL,
    original_filename VARCHAR(255) NOT NULL,   -- 원본 파일명
    saved_filename    VARCHAR(255) NOT NULL,   -- UUID 저장명
    file_size         BIGINT,                  -- 파일 크기(byte)
    file_type         VARCHAR(50),             -- 확장자
    created_at        DATETIME DEFAULT NOW(),
    FOREIGN KEY (account_id)
        REFERENCES accounts(id)
        ON DELETE CASCADE    -- 계좌 삭제 시 첨부파일도 함께 삭제
);


INSERT INTO tbl_attachment
    (account_id, original_filename, saved_filename, file_size, file_type)
VALUES
(1, '신분증.jpg',
 'a1b2c3d4-1111-2222-3333-aaa.jpg', 204800, 'jpg'),
(1, '통장사본.pdf',
 'b2c3d4e5-1111-2222-3333-bbb.pdf', 512000, 'pdf'),
(2, '재직증명서.pdf',
 'c3d4e5f6-1111-2222-3333-ccc.pdf', 307200, 'pdf'),
(3, '소득확인서.png',
 'd4e5f6a7-1111-2222-3333-ddd.png', 153600, 'png'),
(4, '인감증명서.pdf',
 'e5f6a7b8-1111-2222-3333-eee.pdf', 409600, 'pdf');
 
 
SELECT *
FROM accounts a
LEFT OUTER JOIN tbl_attachment t ON a.id = t.account_id
ORDER BY a.id ASC;

SELECT
a.id AS account_id,
a.account_no,
a.owner_name,
a.balance,
a.account_type,
t.id AS att_id,
t.original_filename,
t.saved_filename,
t.file_size,
t.file_type
FROM accounts a
LEFT JOIN tbl_attachment t ON a.id = t.account_id
ORDER BY a.id DESC, t.id ASC;



        SELECT
        a.id AS account_id,
        a.account_no,
        a.owner_name,
        a.balance,
        a.account_type,
        a.create_at,
        t.id AS att_id,
        t.original_filename,
        t.saved_filename,
        t.file_size,
        t.file_type,
        t.created_at AS att_created_at
        FROM accounts a
        LEFT JOIN tbl_attachment t ON a.id = t.account_id
        WHERE a.id = 4
        ORDER BY t.id ASC;