/**
 * Author:  Alexander
 * Created: May 29, 2018
 */
DROP TABLE IF EXISTS expenditure;
CREATE TABLE expenditure (
    id              INT             NOT NULL AUTO_INCREMENT,
    date            VARCHAR(255)    DEFAULT SYSDATE,
    description     VARCHAR(255)    ,
    type            VARCHAR(25)     ,
    spent           DOUBLE          DEFAULT 0,
    currency        VARCHAR(10)     ,
    debtID          INT             ,
    PRIMARY KEY(id)
);