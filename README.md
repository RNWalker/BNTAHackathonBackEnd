# Hack4Change 2023 - Mind Mentor

## Hack for Change: Harnessing AI for Good
Hack for Change challenges some of the brightest young minds in technology, all from the award-winning tech accelerator, B﻿right Network Technology Academy, to harness the power of technology to drive diversity and inclusion. 

Unlike most hackathons, which typically last 24-72 hours, Hack for Change give the teams two weeks to hone and craft their hack. The challenge this year 2023, is to harness the power of AI for good to transform the workplace into a completely inclusive environment. 

Technology is key to achieve positive change in our workplaces, and Hack for Change would like to inspire the next generation of tech talent to be champions of diversity and inclusion by showing them power of technology to transform our world.

# Mind Mentor




## MVP

*Conversational AI Chatbot:*
Users can chat in real-time about their feelings, challenges, or stressors.
Employs empathetic and neutral language, designed to make the user feel understood and supported.

*Confidentiality Assurance:*
The app should clearly communicate that all conversations are confidential and only aggregated, anonymized data (not individual conversations) is available to the employer

*Intervention Recommendations:*
Based on user interactions, the bot suggests personalized interventions like taking a break, practicing mindfulness, seeking counseling, etc

*Resource Hub:*
Provides resources such as articles, videos, or audio focused on mental well-being, stress reduction, coping mechanisms, etc.


## Extensions 
*Mood Tracking*
Allows users to regularly input and track their mood or stress levels.
Visual graphs show mood trends over time, helping to identify potential triggers or patterns.

*Integration with External Counseling:*
If the chatbot determines that a user could benefit from professional help, it could provide contact information for in-network therapists or mental health professionals.

*Feedback Mechanism*
Allows users to give feedback on the bot’s responses and effectiveness, which can be used for continuous improvement

*Event-triggered Check-ins:*
If the workplace has had a particularly challenging event (e.g., layoffs, a challenging project), the bot could proactively check in with employees.

## Setup instructions
This is a full stack project composed by two different repositories. This repository refers to the backend. This link will take you to the frontend repository. 

To set up the backend you will need to:
1. Install IntelliJ IDEA or another IDE that is suitable for JDK 17.
2. Install postgreSQL.
3. Clone the project repository from GitHub.
4. Import the project into your IDE.
5. Make a postgreSQL database on your system with the following command `createdb mind_mentor`. See the `application.properties` file for the path that the application requires to access the database.
6. Add a package with the name `key` inside `mind_mentor/src/main/java/com/hackathon/mind_mentor`
7. Inside `key` you will need to create 3 different documents.
   - The first one must be called `api_key.txt`. In order to obtain an api_key...
   - Second one will be `encryption_key.txt`. You will need to add your own encryption key
   - Last one must be called `initVector_key.txt`. You will need to add your own key.
9. Run the project.

## The RESTful route endpoints:

The default port the project will be run on is port 8080 but any can be used. You can access the API endpoints with the following URL **`https://localhost:{port}/{endpoint}`**. See the table below for all available endpoints.

|Controller | Mapping |Path | Description |
|----------|-----------|------|-------------|
| Restaurants | GET	| `localhost:8080/users` | Shows all users
| | GET	| `localhost:8080/restaurants?borough={boroughName}`	| Shows all chats
| | GET	| `localhost:8080/restaurants?cuisine={cuisineName}`	| Shows all messages
|

## Entity Relationship Diagram
<img src = "./src/main/resources/diagrams/ERD.png" alt= "entity relationship diagram"/>

## Class Diagram
<img src ="./src/main/resources/diagrams/ClassDiagram.png" alt= "class diagram"/>

## Tech Stack

The technologies used for the backend are:

- Intelliji IDEA, running JDK 17
- Spring Boot
- Postman
- Postico

For the frontend, please refer to the following link

## Dependencies

The dependencies required for the backend to run are:
- SpringBoot Web
- SpringBoot DevTools
- PostgreSQL
- SpringBoot Starter Data JPA

## Collaborators

- Blezzy Dela Cruz
- Kevin Chan
- Rebecca Walker
- Rohaib Ahmed
- Sarah O'Connor
- Sandra Martinez Dominguez
