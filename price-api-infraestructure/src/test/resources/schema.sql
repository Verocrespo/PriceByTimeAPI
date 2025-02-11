CREATE TABLE `PRICES` (
    `PRICE_LIST` BIGINT not null AUTO_INCREMENT,
    `BRAND_ID` BIGINT not null,
    `PRODUCT_ID` BIGINT not null,
    `START_DATE` DATETIME not null,
    `END_DATE` DATETIME not null,
    `PRIORITY` TINYINT not null,
    `PRICE` DECIMAL(15,2) not null,
    `CURR` VARCHAR(3) not null
);
