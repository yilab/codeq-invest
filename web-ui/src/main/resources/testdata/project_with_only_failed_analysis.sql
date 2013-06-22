INSERT INTO PROJECT (ID, PROFILE_ID, NAME, LOWERCASENAME, CRONEXPRESSION, SONAR_URL,
                     SONAR_PROJECT, SONAR_USERNAME, SONAR_PASSWORD,
                     SCM_TYPE, SCM_URL, SCM_USERNAME, SCM_PASSWORD,
                     SCM_FILE_ENCODING, CODE_CHANGE_METHOD, CODE_CHANGE_DAYS, HADANALYSIS)
  VALUES (5, 1, 'Project With Only Failed Analysis', 'project with only failed analysis', '* * 3 * * *',
          'http://localhost', 'sonarProject', NULL, NULL,
          0, 'http://svn.localhost', NULL, NULL, 'UTF-8',
          0, 21, FALSE);

INSERT INTO QUALITY_ANALYSIS (ID, PROJECT_ID, SUCCESSFUL, FAILUREREASON, CREATED) VALUES (5, 5, FALSE, 'Error 1', TIMESTAMP '2013-06-04 20:00:00.000+02:00');
INSERT INTO QUALITY_ANALYSIS (ID, PROJECT_ID, SUCCESSFUL, FAILUREREASON, CREATED) VALUES (6, 5, FALSE, 'Error 2', TIMESTAMP '2013-06-05 20:00:00.000+02:00');
INSERT INTO QUALITY_ANALYSIS (ID, PROJECT_ID, SUCCESSFUL, FAILUREREASON, CREATED) VALUES (7, 5, FALSE, 'Error 3', TIMESTAMP '2013-06-06 20:00:00.000+02:00');
