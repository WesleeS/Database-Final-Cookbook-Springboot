# Table Creation

```SQL
CREATE TABLE WSRecipeDatabase.Cookbook (
    Title VARCHAR(64) PRIMARY KEY,
    Date DATE,
    Publisher VARCHAR(64)
);

CREATE TABLE WSRecipeDatabase.Allergen (
    Name VARCHAR(64) PRIMARY KEY,
    TriggeringConditions VARCHAR(128)
);

CREATE TABLE WSRecipeDatabase.Ingredient (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    Name VARCHAR(64) NOT NULL,
    Calories INT,
    ShelfLife INT,
    Subclass VARCHAR(64),
    DairyAnimal VARCHAR(64),
    GrainType VARCHAR(64),
    VegetableType VARCHAR(64),
    FruitType VARCHAR(64),
    MeatAnimal VARCHAR(64),
    isVegan BIT DEFAULT 0,
    CONSTRAINT chk_Subclass CHECK (Subclass IN ('Dairy', 'Grain', 'Vegetable', 'Fruit', 'Meat')),
    CONSTRAINT chk_GrainType CHECK (GrainType IN ('Whole', 'Refined') OR GrainType IS NULL),
    CONSTRAINT chk_VegetableType CHECK (VegetableType IN ('Flower', 'Root') OR VegetableType IS NULL),
    CONSTRAINT chk_FruitType CHECK (FruitType IN ('Simple', 'Aggregate') OR FruitType IS NULL),
    CONSTRAINT chk_MeatAnimal CHECK (MeatAnimal IS NULL OR MeatAnimal != '')
);

CREATE TABLE WSRecipeDatabase.Store (
    StoreCode VARCHAR(64) PRIMARY KEY,
    Address VARCHAR(128)
);

CREATE TABLE WSRecipeDatabase.Recipe (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    Name VARCHAR(64),
    CookbookTitle VARCHAR(64),
    CookingTime INT,
    VariationOf INT,
    Difficulty VARCHAR(16) CHECK (Difficulty IN ('Easy', 'Medium', 'Hard')),
    FOREIGN KEY (CookbookTitle) REFERENCES WSRecipeDatabase.Cookbook(Title),
    FOREIGN KEY (VariationOf) REFERENCES WSRecipeDatabase.Recipe(ID)
);

CREATE TABLE WSRecipeDatabase.Step (
    RecipeID INT NOT NULL,
    Step INT NOT NULL,
    Directions VARCHAR(128),
    PRIMARY KEY (RecipeID, Step),
    FOREIGN KEY (RecipeID) REFERENCES WSRecipeDatabase.Recipe(ID)
);

CREATE TABLE WSRecipeDatabase.Allergen_to_Ingredient (
    AllergenName VARCHAR(64),
    IngredientID INT,
    PRIMARY KEY (AllergenName, IngredientID),
    FOREIGN KEY (AllergenName) REFERENCES WSRecipeDatabase.Allergen(Name),
    FOREIGN KEY (IngredientID) REFERENCES WSRecipeDatabase.Ingredient(ID)
);

CREATE TABLE WSRecipeDatabase.Step_to_Ingredient (
    RecipeID INT,
    Step INT,
    IngredientID INT,
    Quantity DECIMAL(10, 2),
    Measurement VARCHAR(16) CHECK (Measurement IN ('Count', 'Ounce', 'Fluid Ounce')),
    PRIMARY KEY (RecipeID, Step, IngredientID),
    FOREIGN KEY (RecipeID) REFERENCES WSRecipeDatabase.Recipe(ID),
    FOREIGN KEY (IngredientID) REFERENCES WSRecipeDatabase.Ingredient(ID)
);

CREATE TABLE WSRecipeDatabase.Store_to_Ingredient (
    StoreCode VARCHAR(64),
    IngredientID INT,
    Price DECIMAL(10, 2),
    Measurement VARCHAR(16) CHECK (Measurement IN ('Count', 'Ounce', 'Fluid Ounce')),
    PRIMARY KEY (StoreCode, IngredientID),
    FOREIGN KEY (StoreCode) REFERENCES WSRecipeDatabase.Store(StoreCode),
    FOREIGN KEY (IngredientID) REFERENCES WSRecipeDatabase.Ingredient(ID)
);

CREATE VIEW WSRecipeDatabase.CookbookWithRecipeCount AS
SELECT 
    c.Title,
    c.Date,
    c.Publisher,
    COUNT(r.ID) AS NumberOfRecipes
FROM 
    WSRecipeDatabase.Cookbook c
LEFT JOIN 
    WSRecipeDatabase.Recipe r ON r.CookbookTitle = c.Title
GROUP BY 
    c.Title, c.Date, c.Publisher;
```

---

# Tuple Information


```SQL

INSERT INTO WSRecipeDatabase.Cookbook (Title, Date, Publisher) VALUES
('Sad Bastard Cookbook, The', '2022-01-01', 'Independent Publishing Network');


INSERT INTO WSRecipeDatabase.Ingredient (ID, Name, Calories, ShelfLife, Subclass, DairyAnimal, GrainType, VegetableType, FruitType, MeatAnimal, isVegan) VALUES
(1, 'Apple', 62, 5, 'Fruit', NULL, NULL, NULL, 'Simple', NULL, 1),
(2, 'Cinnamon', 0, 365, 'Vegetable', NULL, NULL, 'Root', NULL, NULL, 1),
(3, 'Sugar', 30, 730, 'Vegetable', NULL, NULL, NULL, NULL, NULL, 1),
(4, 'Crackers', 120, 730, 'Grain', NULL, 'Refined', NULL, NULL, NULL, 1),
(5, 'Brie Cheese', 300, 30, 'Dairy', 'Cow', NULL, NULL, NULL, NULL, 0),
(6, 'Bread', 80, 7, 'Grain', NULL, 'Refined', NULL, NULL, NULL, 1),
(7, 'Garlic', 5, 182, 'Vegetable', NULL, NULL, 'Root', NULL, NULL, 1),
(8, 'Butter', 120, 180, 'Dairy', 'Cow', NULL, NULL, NULL, NULL, 0),
(9, 'Orange', 45, 14, 'Fruit', NULL, NULL, NULL, 'Simple', NULL, 1);


INSERT INTO WSRecipeDatabase.Recipe (ID, Name, CookbookTitle, CookingTime, VariationOf, Difficulty) VALUES
(1, '"Baked" Apples', 'Sad Bastard Cookbook, The', 1, NULL, 'Easy'),
(2, 'Crackers and Stuff', 'Sad Bastard Cookbook, The', 1, NULL, 'Easy'),
(3, 'Fancy Cheese and Crackers', 'Sad Bastard Cookbook, The', 20, 2, 'Medium'),
(4, 'Garlic Bread', 'Sad Bastard Cookbook, The', 15, NULL, 'Easy'),
(5, 'Moroccan Oranges with Cinnamon', 'Sad Bastard Cookbook, The', 10, NULL, 'Easy');

INSERT INTO WSRecipeDatabase.Step (RecipeID, Step, Directions) VALUES
(1, 1, 'Cut apples into quarters and remove the cores.'),
(1, 2, 'Put apple quarters in a bowl with a little bit of water in the bottom.'),
(1, 3, 'Sprinkle sugar and cinnamon on top.'),
(1, 4, 'Microwave 1 minute at a time until the apples are mushy.'),
(2, 5, 'Eat the crackers out of the box while watching bad reruns on TV.'),
(3, 1, 'You''re gonna fully melt the cheese, so you need it to have a rind to contain it in the oven.'),
(3, 2, 'Ideally your baking dish has edges so it acts as an extra layer of containment.'),
(3, 3, 'Put in the oven at 350°F or 175°C. It''ll probably take about 15 minutes.'),
(3, 4, 'Serve with crackers. Guests will expect a knife to spread the cheese on the crackers but you can dip if you''re not a coward.'),
(3, 5, 'Put slices of the cheese on crackers on a baking tray.'),
(3, 6, 'Put in the oven at 350°F or 175°C for just long enough to make the cheese melty. Maybe 5 minutes?'),
(4, 1, 'Take bread.'),
(4, 2, 'Put oil-substance (butter, margarine, vegetable oil, or olive oil) on the non-crust part of the bread.'),
(4, 3, 'Put garlic on top. If using melted butter/margarine or oil, mix the garlic in with it for easier spreading.'),
(4, 4, 'Bake in the oven at 350°F (175°C) until hot and deliciously toasty. Wrapping in tinfoil is recommended'),
(4, 5, 'If using the oven is too much, microwave it instead to melt the margarine and heat the bread.'),
(5, 1, 'Slice the oranges over a bowl so that none of the juice escapes. Ideally slice thinly. Don''t bother peeling them yet.'),
(5, 2, 'Sprinkle cinnamon in the bowl and stir the whole thing up.'),
(5, 3, 'Let it sit for a bit. The juice and cinnamon will mix together to form a delicious goo-like substance.'),
(5, 4, 'Take out the orange slices and sprinkle more cinnamon over them.'),
(5, 5, 'Eat! You probably don''t want to eat the peel.');

INSERT INTO WSRecipeDatabase.Step_to_Ingredient (RecipeID, Step, IngredientID, Quantity, Measurement) VALUES
(1, 1, 1, 1, 'Count'),
(1, 3, 2, 0.04, 'Ounce'),
(1, 3, 3, 0.04, 'Ounce'),
(2, 5, 4, 6, 'Count'),
(3, 1, 5, 1, 'Count'),
(3, 4, 4, 6, 'Count'),
(4, 1, 6, 1, 'Count'),
(4, 2, 7, 1, 'Ounce'),
(4, 3, 8, 0.5, 'Ounce'),
(5, 1, 9, 2, 'Count'),
(5, 2, 2, 0.25, 'Ounce'),
(5, 4, 2, 0.1, 'Ounce');

```