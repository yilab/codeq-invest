ALTER TABLE PROJECT ADD COLUMN PROFILE_ID BIGINT NOT NULL;
ALTER TABLE PROJECT ADD CONSTRAINT PROFILE_FK FOREIGN KEY (PROFILE_ID) REFERENCES QUALITY_PROFILE (ID);