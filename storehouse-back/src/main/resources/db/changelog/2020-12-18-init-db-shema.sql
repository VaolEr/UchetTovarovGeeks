-- liquibase formatted sql
-- Format: --changeset author:id attribute1:value1 attribute2:value2 [...]

-- changeset Dmitry:Create_items_table
CREATE TABLE "items"
(
    "id"          int  NOT NULL,
    "name"        TEXT NOT NULL,
    "sku"         TEXT NOT NULL,
    "supplier_id" int  NOT NULL,
    "category_id" int  NOT NULL,
    CONSTRAINT "items_pk" PRIMARY KEY ("id")
);

-- changeset Dmitry:Create_categories_table
CREATE TABLE "categories"
(
    "id"   int NOT NULL,
    "name" int NOT NULL,
    CONSTRAINT "categories_pk" PRIMARY KEY ("id")
);

-- changeset Dmitry:Create_storehouses_table
CREATE TABLE "storehouses"
(
    "id"   int  NOT NULL,
    "name" TEXT NOT NULL,
    CONSTRAINT "storehouses_pk" PRIMARY KEY ("id")
);

-- changeset Dmitry:Create_suppliers_table
CREATE TABLE "suppliers"
(
    "id"   serial NOT NULL,
    "name" TEXT   NOT NULL,
    CONSTRAINT "suppliers_pk" PRIMARY KEY ("id")
);

-- changeset Dmitry:Create_items_storehouses_table
CREATE TABLE "items_storehouses"
(
    "item_id"       int NOT NULL,
    "storehouse_id" int NOT NULL,
    "qty"           int NOT NULL
);

-- changeset Dmitry:Alter_items_table_fks
ALTER TABLE "items"
    ADD CONSTRAINT "items_fk0" FOREIGN KEY ("supplier_id") REFERENCES "suppliers" ("id");
ALTER TABLE "items"
    ADD CONSTRAINT "items_fk1" FOREIGN KEY ("category_id") REFERENCES "categories" ("id");

-- changeset Dmitry:Alter_items_storehouses_table_fks
ALTER TABLE "items_storehouses"
    ADD CONSTRAINT "items_storehouses_fk0" FOREIGN KEY ("item_id") REFERENCES "items" ("id");
ALTER TABLE "items_storehouses"
    ADD CONSTRAINT "items_storehouses_fk1" FOREIGN KEY ("storehouse_id") REFERENCES "storehouses" ("id");
