CREATE TABLE NEWMEMBER (
	NEW_NO	number	NOT NULL,
	NEW_NAME	VARCHAR2(20)	NULL,
	NEW_RANK	VARCHAR2(20)	NULL,
	NEW_DEPT	VARCHAR2(30)	NULL
);

ALTER TABLE NewMember ADD CONSTRAINT PK_NEWMEMBER PRIMARY KEY (
	NEW_NO
);

CREATE SEQUENCE SEQ_NEW_NO;


commit;