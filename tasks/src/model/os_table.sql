

CREATE TABLE task ( id int(11) AUTO_INCREMENT, label text NOT NULL, status varchar(10) NOT NULL, date_task datetime NOT NULL, PRIMARY KEY (id));

INSERT INTO task (label, status, date_task) VALUES
('Saisir mon Jira !', 'todo', '2019-04-10 14:28:00');

