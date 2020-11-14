DROP TABLE IF EXISTS product cascade;
DROP TABLE IF EXISTS brand cascade;

create table brand (
 id integer not null auto_increment,
 name varchar(255) not null,
 country varchar(255) not null,
 primary key(name)
);

create table product (
 id integer not null auto_increment,
 reference varchar(255) not null,
 brand_name varchar(255) not null,
 denomination varchar(255) not null,
 price double not null,
 weight double not null,
 volume double not null,
 primary key(reference),
 foreign key (brand_name)
         references brand(name)
         on delete cascade
);

INSERT INTO BRAND (ID, NAME, COUNTRY )
VALUES(10001,  'Apple', 'China');
INSERT INTO BRAND (ID, NAME, COUNTRY )
VALUES(10002,  'Samsung', 'US');

INSERT INTO PRODUCT (ID, REFERENCE, BRAND_NAME, DENOMINATION, PRICE, WEIGHT, VOLUME )
VALUES(10001,  'IPHONE10', 'Apple', 'USD', 10000.0, 1.0, 1.0);
INSERT INTO PRODUCT (ID, REFERENCE, BRAND_NAME, DENOMINATION, PRICE, WEIGHT, VOLUME )
VALUES(10002,  'IPHONE15', 'Apple', 'USD', 15000.0, 1.0, 1.0);

DROP TABLE IF EXISTS virus_family cascade;
DROP TABLE IF EXISTS virus cascade;

create table virus_family (
 id integer not null auto_increment,
 name varchar(255) not null UNIQUE,
 size_min double not null,
 size_max double not null,
 strand varchar(255) not null,
 enveloped boolean,
 primary key(name)
);

INSERT INTO virus_family (ID, NAME, SIZE_MIN, SIZE_MAX, STRAND, ENVELOPED )
VALUES(0,  'Adenoviridae', 80.0, 100.0, 'DS Linear', false);
INSERT INTO virus_family (ID, NAME, SIZE_MIN, SIZE_MAX, STRAND, ENVELOPED )
VALUES(1,  'Asfarviridae', 175.0, 215.0, 'DS Linear', true);

create table virus (
 id integer not null auto_increment,
 name varchar(255) not null UNIQUE,
 human_affected boolean,
 animal_affected boolean,
 family_id integer,
 primary key(name),
 foreign key (family_id)
          references virus_family(id)
          on delete cascade
);

INSERT INTO virus (ID, NAME, HUMAN_AFFECTED, ANIMAL_AFFECTED, FAMILY_ID)
VALUES(0,  'Bovine adenoviruses A, B, C ', false, true, 0);
INSERT INTO virus (ID, NAME, HUMAN_AFFECTED, ANIMAL_AFFECTED, FAMILY_ID)
VALUES(1,  'Canine adenovirus (infectious canine hepatitis)', false, true, 0);
