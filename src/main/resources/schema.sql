CREATE TABLE IF NOT EXISTS `QUOTE`(
    ID bigint AUTO_INCREMENT primary key,
    USER_ID bigint not null,
    NAME varchar(255) not null,
    ORDER_ID bigint not null,
    PRODUCT_ID bigint not null,
    PRODUCT_VALUE decimal(10,2) NOT NULL,
    DATE_BUY datetime not null
);