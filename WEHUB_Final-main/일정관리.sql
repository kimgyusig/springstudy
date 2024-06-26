CREATE TABLE CALENDAR (
	CAL_NO	NUMBER	NOT NULL,
	USER_NO	NUMBER	NOT NULL,
	CAL_YEAR	VARCHAR2(5)	NOT NULL,
	CAL_MONTH	VARCHAR2(3)	NOT NULL,
	CAL_DAY	VARCHAR2(3)	NOT NULL,
	CAL_CONTENT	VARCHAR2(1000)	NULL,
	CAL_DATE DATE DEFAULT SYSDATE
);

ALTER TABLE calendar ADD CONSTRAINT PK_CALENDAR PRIMARY KEY (
	CAL_NO
);

CREATE SEQUENCE SEQ_CAL_NO;
------------------------------------------------------------

--Ŀ�´�Ƽ ���̺� ����
CREATE TABLE COMMUNITY (
	CM_NO	NUMBER	NOT NULL,
	USER_NO	NUMBER	NOT NULL,
	CM_TITLE	VARCHAR2(300)	NULL,
	CM_CONTENT	VARCHAR2(1000)	NULL,
	CM_READCOUNT	NUMBER	NULL,
	CM_DATE	 DATE	DEFAULT SYSDATE,
	CM_MODIFY_DATE	DATE	DEFAULT SYSDATE,
	CM_STATUS	CHAR(1)	DEFAULT 'Y' CHECK(CM_STATUS IN('Y','N','F'))
);

ALTER TABLE COMMUNITY ADD CONSTRAINT PK_COMMUNITY PRIMARY KEY (
	CM_NO
);
CREATE SEQUENCE SEQ_CM_NO;

ALTER TABLE COMMUNITY ADD CONSTRAINT FK_MEMBER_TO_Community_1 FOREIGN KEY (
	USER_NO
)
REFERENCES MEMBER (
	USER_NO
);

---------------------------------------------------------------------------
--Ŀ�´�Ƽ ���ã��
CREATE TABLE CM_BOOKMARK (
	CM_BM_NO	NUMBER	NOT NULL,
	USER_NO	NUMBER	NOT NULL,
	CM_NO	NUMBER	NOT NULL
);

ALTER TABLE CM_BOOKMARK ADD CONSTRAINT PK_CM_BOOKMARK PRIMARY KEY (
	CM_BM_NO
);

CREATE SEQUENCE SEQ_BM_NO;

ALTER TABLE CM_BOOKMARK ADD CONSTRAINT FK_MEMBER_CM_BOOKMARK FOREIGN KEY (
	USER_NO
)
REFERENCES MEMBER (
	USER_NO
);

ALTER TABLE CM_BOOKMARK ADD CONSTRAINT FK_Community_CM_BOOKMARK FOREIGN KEY (
	CM_NO
)
REFERENCES Community (
	CM_NO
);
