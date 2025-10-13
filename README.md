# CS 151 Project 1: Turn-Based RPG with 1 player and 1 enemy

## Table of Contents
1. [Overview](#Overview)
2. [Design](#Design)
3. [Setup](#Setup)
4. [Usage](#Usage)
5. [Contributors](#contributors)

## Overview
This project aims to learn and showcase the application of Java fundamentals and Object-Oriented Programming principles.
The medium we chose is a turn-based role-playing game (RPG). There will be one playable character and one NPC (non-playable) enemy.

## Design
Refer to the full UML diagram for details. This project follows an objected-oriented design built around modular game components.
Classes are separated into separate files and compartmentalized into different packages.
- **Core**: The driver class `Main` setups the game, manages UI/UX (`Menus`, `Scene`), and manages game logic through `BattlefieldManager` which keeps track and coordinates turns.
- **Characters**: An abstract class `Characters` defines the blueprint for `PlayableCharacter` and `Enemy` that extends this class with its common variables and methods.
- **Actions**: `Ability` with `Usable` interface provide the player with a mechanism for skill use.
- **Inventory**: All `Characters` has one `Inventory` where `Items` can be stored, keeping track of capacity and weight.
- **Item**: Many instances of `Item` can go into one `Inventory`. All collectable items are stored in Inventories and share a common blueprint.
- **Weapon**: A `Weapon` is a generalization of an `Item` and inherits properties accordingly. `Weapon` is equipable by `Characters` for use in combat.
- **Exceptions**: `MaxInstanceLimitException` is the most important exception to limit instances up to 100 per class. This limit may be shared between super and subclasses.

The design considerations are as follows:
- Emphasis on encapsulation and inheritance for reusability. "DRY" principle.
- Separation of UI/UX and game logic.
- Scalability for adding new characters, abilities, or items.

## Setup
0. Clone the repo:
```
git clone https://github.com/ray-sjsu/cs-151-fall-2025-project.git
```
### Automatic
1. Open the repo using **Intellj IDEA** or any IDE of your choice.
2. In the project directory, navigate to the file:
```
src/main/java/rpg/Main/Main.java
```
3. With `Main.java` opened as the active window:
- In **Intellj IDEA**, click the green **Run** bottom in the top-left corner.
- In other IDEs, use their respective **Run** feature to compile and execute the main class.
### Manual (Console)
1. Open a command-line terminal and navigate to the source directory:
```
cd src/main/java
```
2. Compile the Java files recursively to create `.class` files:
```
javac rpg/**/*.java
```
3. Run the program using the class name:
```
java rpg.Main
```

## Usage
Follow the terminal prompts and interact with the choices by pressing a number followed by `ENTER`.

Game ends once either the player or enemy fall.

## Contributors
### Earl Dozier
- Class: `Inventory`, `Item`, `Weapon`
- Interface: `Usable`
- Exception: `InventoryFullException`, `MaxInstancesLimitException`
- Enums: `StatusType`, `RarityType`
### Welson Kuang
- Class: `Ability`, `Characters`, `PlayableCharacter`, `Enemy`
- Exception: `AbilityOnCooldownException`
- Enums: `StatType`, `ActionType`
### Raymund Mercader
- Class: `BattlefieldManager`, `TurnAction`
- UI/UX Class: `Main`, `Menus`, `Scene`
- UML Diagram and `README.md`

Note: Refer to UML diagram. For simplicity, each contributed entry is only listed once. 



