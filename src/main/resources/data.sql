INSERT INTO DRIVER1 (Name, Surname, Date_of_birth, Start_of_career, End_of_career, Photo) VALUES
('Lewis', 'Hamilton', '1985-01-07', '2007-03-18', NULL, 'lewis_hamilton.jpg'),
('Max', 'Verstappen', '1997-09-30', '2015-03-15', NULL, 'max_verstappen.jpg'),
('Charles', 'Leclerc', '1997-10-16', '2018-03-25', NULL, 'charles_leclerc.jpg'),
('Sebastian', 'Vettel', '1987-07-03', '2007-06-17', '2021-12-12', 'sebastian_vettel.jpg'),
('Fernando', 'Alonso', '1981-07-29', '2001-03-04', NULL, 'fernando_alonso.jpg'),
('Lando', 'Norris', '1999-11-13', '2019-03-17', NULL, 'lando_norris.jpg'),
('Carlos', 'Sainz', '1994-09-01', '2015-03-15', NULL, 'carlos_sainz.jpg'),
('Daniel', 'Ricciardo', '1989-07-01', '2011-07-11', NULL, 'daniel_ricciardo.jpg'),
('Sergio', 'Perez', '1990-01-26', '2011-03-27', NULL, 'sergio_perez.jpg'),
('Esteban', 'Ocon', '1996-09-17', '2016-08-28', NULL, 'esteban_ocon.jpg'),
('Pierre', 'Gasly', '1996-02-07', '2017-10-01', NULL, 'pierre_gasly.jpg'),
('George', 'Russell', '1998-02-15', '2019-03-17', NULL, 'george_russell.jpg'),
('Nicholas', 'Latifi', '1995-06-29', '2020-07-05', NULL, 'nicholas_latifi.jpg'),
('Kimi', 'Raikkonen', '1979-10-17', '2001-03-04', '2021-12-12', 'kimi_raikkonen.jpg'),
('Mick', 'Schumacher', '1999-03-22', '2021-03-28', NULL, 'mick_schumacher.jpg'),
('Yuki', 'Tsunoda', '2000-05-11', '2021-03-28', NULL, 'yuki_tsunoda.jpg'),
('Valtteri', 'Bottas', '1989-08-28', '2013-03-17', NULL, 'valtteri_bottas.jpg'),
('Nico', 'Hulkenberg', '1987-08-19', '2010-03-14', NULL, 'nico_hulkenberg.jpg'),
('Robert', 'Kubica', '1984-12-07', '2006-08-06', NULL, 'robert_kubica.jpg'),
('Kevin', 'Magnussen', '1992-10-05', '2014-03-16', NULL, 'kevin_magnussen.jpg');



INSERT INTO TEAM (Name, Country_of_origin, FOUNDATION_DATE, Team_principle, Logo) VALUES
('Mercedes', 'Germany', '1954-01-01', 'Toto Wolff', 'mercedes_logo.jpg'),
('Red Bull Racing', 'Austria', '2005-01-01', 'Christian Horner', 'red_bull_logo.jpg'),
('Ferrari', 'Italy', '1929-11-16', 'Mattia Binotto', 'ferrari_logo.jpg'),
('McLaren', 'United Kingdom', '1963-09-02', 'Zak Brown', 'mclaren_logo.jpg'),
('Aston Martin', 'United Kingdom', '1913-01-15', 'Mike Krack', 'aston_martin_logo.jpg'),
('Alpine', 'France', '1955-06-25', 'Laurent Rossi', 'alpine_logo.jpg'),
('AlphaTauri', 'Italy', '1985-11-01', 'Franz Tost', 'alphatauri_logo.jpg'),
('Williams', 'United Kingdom', '1977-05-28', 'James Vowles', 'williams_logo.jpg'),
('Alfa Romeo', 'Switzerland', '1910-06-24', 'Frédéric Vasseur', 'alfa_romeo_logo.jpg'),
('Haas', 'United States', '2014-04-14', 'Guenther Steiner', 'haas_logo.jpg');



INSERT INTO Team_Driver (Driver_ID, Team_ID, DATE_JOINED , DATE_LEFT ) VALUES
(1, 1, '2013-01-01', NULL),
(2, 2, '2015-01-01', NULL),
(3, 3, '2018-01-01', NULL),
(4, 4, '2009-01-01', '2021-12-12'),
(5, 5, '2021-01-01', NULL),
(6, 6, '2019-01-01', NULL),
(7, 7, '2017-01-01', NULL),
(8, 8, '2018-01-01', NULL),
(9, 9, '2017-01-01', NULL),
(10, 10, '2019-01-01', NULL);


INSERT INTO USER1 (UserName, Name, Surname, Password, Favourite_team, Is_Admin) VALUES
('user1', 'John', 'Doe', 'password123', 1, 'Y'),
('user2', 'Jane', 'Smith', 'password456', 2, 'N'),
('user3', 'Alice', 'Johnson', 'password789', 3, 'N'),
('user4', 'Bob', 'Brown', 'password321', 4, 'Y');

INSERT INTO Season (DATE_YEAR, Driver_id, Team_id, Start_date, End_date) VALUES
('2023-01-01', 1, 1, '2023-01-01', '2023-12-31'),
('2022-01-01', 2, 2, '2022-01-01', '2022-12-31'),
('2021-01-01', 3, 3, '2021-01-01', '2021-12-31'),
('2020-01-01', 4, 4, '2020-01-01', '2020-12-31'),
('2019-01-01', 5, 5, '2019-01-01', '2019-12-31');


INSERT INTO Race (Name, Place, Date, LAPS, Weather, Season) VALUES
('Australian Grand Prix 2023', 'Melbourne', '2023-03-20', 58, 'Sunny', 1),
('Bahrain Grand Prix 2023', 'Sakhir', '2023-03-27', 57, 'Sunny', 1),
('Chinese Grand Prix 2023', 'Shanghai', '2023-04-10', 56, 'Rainy', 1),
('Monaco Grand Prix 2023', 'Monaco', '2023-05-28', 78, 'Sunny', 1),
('British Grand Prix 2023', 'Silverstone', '2023-07-16', 52, 'Cloudy', 1);


INSERT INTO Driver_Race (Race_id, Driver_id,  Position_Started,   Position_Finished) VALUES
(4, 1, 5, 1);
INSERT INTO Driver_Race (Race_id, Driver_id,  Position_Started,   Position_Finished) VALUES
(4, 2, 2, 9);
INSERT INTO Driver_Race (Race_id, Driver_id,  Position_Started,   Position_Finished) VALUES
(4, 3, 10, 2);
INSERT INTO Driver_Race (Race_id, Driver_id,  Position_Started,   Position_Finished) VALUES
(2, 1, 1, -1);
INSERT INTO Driver_Race (Race_id, Driver_id,  Position_Started,   Position_Finished) VALUES
(2, 2, 2, 1);
INSERT INTO Driver_Race (Race_id, Driver_id,  Position_Started,   Position_Finished) VALUES
(2, 3, 11, 8);
INSERT INTO Driver_Race (Race_id, Driver_id,  Position_Started,   Position_Finished) VALUES
(3, 1, 1, 3);
INSERT INTO Driver_Race (Race_id, Driver_id,  Position_Started,   Position_Finished) VALUES
(3, 2, 2, 2);
INSERT INTO Driver_Race (Race_id, Driver_id,  Position_Started,   Position_Finished) VALUES
(3, 3, 3, 1);

INSERT INTO Penalty (Race_id, Driver_id, Reason, Punishment) VALUES
                                                                 (4, 1, 'Speeding in the pit lane', '5-second time penalty'),
                                                                 (4, 2, 'Causing a collision', '10-second time penalty'),
                                                                 (4, 3, 'Ignoring blue flags', 'Drive-through penalty'),
                                                                 (2, 1, 'False start', 'Drive-through penalty'),
                                                                 (2, 2, 'Unsafe release', '5-second time penalty'),
                                                                 (2, 3, 'Blocking another driver', '10-second time penalty'),
                                                                 (3, 1, 'Track limits violation', '5-second time penalty'),
                                                                 (3, 2, 'Overtaking under safety car', '10-second time penalty'),
                                                                 (3, 3, 'Causing a collision', '10-second time penalty');



INSERT INTO Season (DATE_YEAR, Driver_id, Team_id, Start_date, End_date) VALUES
                                                    ('2024-01-01', NULL, NULL, '2024-01-01', '2024-12-31'),
INSERT INTO Race (Name, Place, Date, LAPS, Weather, Season) VALUES
                                                                ('Australian Grand Prix 2024', 'Melbourne', '2024-03-20', 58, 'Sunny', 6),
                                                                ('Bahrain Grand Prix 2024', 'Sakhir', '2024-03-27', 57, 'Sunny', 6),
                                                                ('Chinese Grand Prix 2024', 'Shanghai', '2024-04-10', 56, 'Rainy', 6),
                                                                ('Monaco Grand Prix 2024', 'Monaco', '2024-05-28', 78, 'Sunny', 6),
                                                                ('British Grand Prix 2024', 'Silverstone', '2024-07-16', 52, 'Cloudy', 6);