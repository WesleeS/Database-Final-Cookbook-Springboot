# Outline

This is the result of my Database Springboot final, presented in a cleaner dynamic webpage to emulate a user's interaction with the system. It is connected to an Azure database, and uses Jquery to parse and load JSON information onto the page passed by the Springboot.
My personal name for this stack is ASS, or **A**zure **S**pringboot j**S**cript. I hope this can convey the emotions of working with this backend.
Consider "MASS" for **M**aven **A**zure **S**pringboot j**S**cript if you want something more friendly.

A User should be able to open this (or access via postman, or beam the query into their mind psionically) and see a cookbook, along with the recipes. Then, select a recipe and view the steps required to make it.
Undeveloped is the pricematching, which would return the total cost of a recipe by referring to the store-ingredient relation's price and the step-ingredient relation's amount.

# Functional Requirements

A **Cookbook** contains information about the book's name, release, and publication. Each cookbook is Uniquely identified by the name. We can see the number of recipes contained in each cookbook in a view.

A **Recipe** is described by its ID, and a non-unique name, and requires a cookbook reference. It contains information about the food's name, the difficulty to make it, the time to cook it, and what the recipe is a variation of (if anything). 

An **Ingredient** is described by the ID and a non-unique name. Each ingredient has information regarding the calories, shelf life, if it is a vegan ingredient, and is split into multiple subtypes including if it is a dairy, a grain, a vegetable, a fruit, or a meat. Depending on the subtype, it also contains information of what animal the meat or dairy is from, whether it is a refined or whole grain, if it's a root or flower vegetable, or if it's a simple or aggregate fruit.

A Recipe's instructions are separated into individual **Steps**. A Step is a single instruction connected to a recipe, described by both the recipe and the step's numerical place. They contain the text for the direction, and are connected to the Ingredients if an ingredient is used that step -- including the quantity used. A recipe may have one or multiple steps, and a step may use multiple or no ingredients.

An **Allergen** is described by its unique name, and a string of conditions which may be impacted by the allergen (such as a gluten allergen being triggering for Coeliac disease). An allergen is also related to any number of ingredients, and any number of allergens may be present within a single ingredient.

A **Store** distributes Ingredients, and is identified by the store code. The name and address are also contained for user identification, and a store sells any number of ingredients. The relationship with each ingredient also concerns the selling price of the ingredient and the amount sold (the price/per on the tag).


# E-ER Diagram

![database1](https://github.com/user-attachments/assets/48816cd0-6368-477b-9d10-95f127dfee4a)

# Relational Schema

![RelationalSchema](https://github.com/user-attachments/assets/05711a36-7d2d-4a7b-a3b2-170c80b4b066)
