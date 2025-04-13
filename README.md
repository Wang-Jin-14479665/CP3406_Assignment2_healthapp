
# HealthApp

## Developer
> Wang Jin  
Student id: 14479665
James Cook University  
Mobile Application Development Project  

## Project Introduction
HealthApp is a health management Android application developed with Kotlin. The main features include:

- Displaying health data (Step Count, Heart Rate)
- Meal and Food record management
- Exercise record management
- Displaying health tips (retrieved from a remote API and stored locally using Room Database)

This project follows the MVVM architecture, utilizes Jetpack Compose for UI building, Room for local database storage, and Retrofit for network communication.

## Project Structure

com.example.healthapp
│
├── data          // Data Layer (Room Entity / Dao / Repository)
│
├── model         // Business Models (Meal / Food / Sport / HealthTip)
│
├── remote        // Network Layer (ApiService / RemoteDataSource / Response Models)
│
├── ui            // UI Layer (Jetpack Compose Screens)
│
├── viewmodel     // ViewModel Layer (Data Handling / State Management)
│
└── MainActivity.kt / NavGraph.kt  // Application Entry & Navigation

## Core Functionalities

| Module      | Description |
|-------------|-------------|
| Dashboard   | Displays step count, heart rate, latest meal record, latest exercise record, and health tips |
| Meal        | Add / Delete Meal records (with related Food items) |
| Sport       | Add / Delete Exercise records |
| Health Tips | Retrieve health tips from API, store in Room, and display in UI |

## Tech Stack

- Kotlin
- Jetpack Compose
- Room Database
- Retrofit2 (API calls)
- MVVM Architecture
- Coroutines (Asynchronous programming)
- Android Studio Dolphin or above

## How to Run

### 1. Clone the Project
git clone https://github.com/Wang-Jin-14479665/CP3406_Assignment2_healthapp.git

### 2. Open with Android Studio
Open the project directory using Android Studio.

### 3. Build & Run
Click the Run button to build and run the project on an emulator or a physical device.

## Testing Guide

The project contains both unit tests and UI tests.

Test locations:
src/test/          // Unit Tests (Repository / ViewModel)
src/androidTest/   // UI Instrumentation Tests (Screen Functionality)

### Test Coverage:
- Meal add/delete/search
- Food add/delete/search
- Sport add/delete/search
- Health Tip fetch and store
- UI Tests for MealScreen, SportScreen, DashboardScreen

## Database Information

Using Room Database with the following tables:
- meal
- food
- sport
- health_tip

### SQL to Clear Data during Testing:
DELETE FROM health_tip;
DELETE FROM meals;
DELETE FROM foods;
DELETE FROM sports;


