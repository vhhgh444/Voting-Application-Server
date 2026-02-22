🗳️ Voting Application – Backend
📌 Overview

The Voting Application Backend is a RESTful API built using Java to manage digital elections.
It allows administrators to create elections, manage candidates, record votes, and declare results securely and efficiently.

This backend is designed with a layered architecture to ensure scalability, validation, and clean separation of concerns.

🚀 Features

✅ Create and manage elections

✅ Register candidates for an election

✅ Cast votes securely

✅ Prevent duplicate voting

✅ Automatically calculate total votes

✅ Declare election results

✅ Retrieve all declared results

✅ Input validation and error handling

✅ REST API architecture (JSON-based communication)

🏗️ Project Architecture

The project follows a standard layered structure:

controller  →  Handles HTTP Requests
service     →  Business Logic
repository  →  Database Access Layer
entity      →  Database Models
DTO         →  Request/Response Mapping
🛠️ Tech Stack

Java

REST API Framework

ORM-based persistence layer

Relational Database (MySQL or compatible)

Maven (Dependency Management)

Validation API

Lombok (for boilerplate reduction)

📂 API Endpoints
🗳️ Election Result APIs
✅ Declare Election Result
POST /api/election_result/declared

Request Body

{
  "electionName": "College President 2025"
}

Response

{
  "electionName": "College President 2025",
  "totalVotes": 320,
  "winnerId": 5,
  "winnerVotes": 210
}
📄 Get All Results
GET /api/election_result

Returns all declared election results.

📦 Installation & Setup
1️⃣ Clone Repository
git clone https://github.com/your-username/voting-application.git
cd voting-application
2️⃣ Configure Database

Create a database:

CREATE DATABASE voting_app;

Update your configuration file:

db.url=jdbc:mysql://localhost:3306/voting_app
db.username=your_username
db.password=your_password
3️⃣ Build Project
mvn clean install
4️⃣ Run Application
mvn spring-boot:run

Server starts at:

http://localhost:8080
🧠 How Result Calculation Works

When /declared is called:

System fetches all candidates for the given election.

Counts total votes.

Determines candidate with highest vote count.

Stores the computed result in election_result table.

Returns structured response.

🔐 Validation Rules

Election name must not be blank.

Each election result can only be declared once.

Votes are counted only from valid voters.

📊 Database Tables (Simplified)
candidate

| id | name | election_name | vote_count |

vote

| id | voter_id | candidate_id |

election_result

| id | election_name | total_votes | winner_id |

🧪 Future Enhancements

🔐 JWT Authentication & Role-based Access

📊 Admin Dashboard (Frontend Integration)

📅 Schedule-based Elections

📈 Live Vote Counting

☁️ Deployment Support (Docker / Cloud)
