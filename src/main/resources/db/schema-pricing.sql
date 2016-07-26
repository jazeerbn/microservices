drop table PRODUCT_PRICE if exists;

create table PRODUCT_PRICE (ID bigint identity primary key, PRODUCT_ID bigint not null,
                        PRICE decimal(8,2), unique(PRODUCT_ID));