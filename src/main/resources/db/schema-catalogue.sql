drop table PRODUCT_CATALOGUE if exists;

create table PRODUCT_CATALOGUE (ID bigint identity primary key,
                        NAME varchar(50) not null, CATEGORY varchar(50), 
                        SUBCATEGORY varchar(50), MANUFACTUREDBY varchar(50));
                        
