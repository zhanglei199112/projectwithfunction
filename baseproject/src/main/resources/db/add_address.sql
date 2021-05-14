---------------------------------
-- 新增地址字段
---------------------------------
ALTER TABLE tb_user_ ADD COLUMN `address_` VARCHAR(255) DEFAULT null COMMENT '地址'