LOAD DATA LOCAL INFILE :file INTO TABLE csv2AmgHospitalization  FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\r\n' STARTING BY 'Physicians First Choice Census'  IGNORE 1 LINES
