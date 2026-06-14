CREATE SEQUENCE seq_board_bno;
CREATE TABLE board(
    bno NUMBER,
    title VARCHAR2(100) not null,
    contants VARCHAR2(255),
    writer VARCHAR2(100),
    regdate DATE DEFAULT sysdate,
    updatedate DATE,

    CONSTRAINT pk_board_bno primary key(bno)
);