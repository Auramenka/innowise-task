CRUD API with Authentication <br/>
Setup instructions <br/>
**Clone the repository** <br/>
git clone https://github.com/Auramenka/innowise-task.git <br/>
**Open the project in IDE** <br/>
**Download the PostgreSQL image and run PostgreSQL container in Docker** <br/>
docker pull postgres:latest <br/>
docker run --name postgres -e POSTGRES_USER=your_username -e POSTGRES_PASSWORD=your_password -e POSTGRES_DB=your_database -p 5432:5432 -d postgres <br/>
Replace **your_username**, **your_password**, **your_database** with your values <br/>
**Update application.properties in src/main/resources with your PostgreSQL credentials, also JWT secret key and JWT expiration** <br/>
<br/>
![image](https://github.com/user-attachments/assets/1b1c56a0-84ec-477d-ba45-d04ce21ff364)
<br/>
![image](https://github.com/user-attachments/assets/7b7a6b9d-bcb7-442c-b674-54707fd1b712)
<br/>
**Run the application as a Spring Boot application** <br/>
**API Endpoints** <br/>
**Register user:** <br/>
POST /api/register <br/>
![image](https://github.com/user-attachments/assets/f8924d96-a807-4942-b6b2-38294e3d6923)
<br/>
**Login user:** <br/>
POST /api/login <br/>
![image](https://github.com/user-attachments/assets/a3ac3a2f-4924-46cd-ad57-91a5babbd819)
<br/>
![image](https://github.com/user-attachments/assets/3a8803b8-33d2-4b3d-9c47-a26837cbd102)
<br/>
**Get list of users:** <br/>
GET /api/users <br/>
Authorization: <JWT_TOKEN> <br/>
![image](https://github.com/user-attachments/assets/e46ea3db-b19f-4f33-9b5a-85bd4d98ec09)
![image](https://github.com/user-attachments/assets/5999495b-d601-4bb3-ad86-3b3f286b93b2)
<br/>
**Delete a user:** <br/>
DELETE /api/users/{id} <br/>
Authorization: <JWT_TOKEN> <br/>
![image](https://github.com/user-attachments/assets/42a7c387-7c35-4e43-990d-cb800623545d)
<br/>
**Update a user:** <br/>
PUT /api/users/{id} <br/>
Authorization: <JWT_TOKEN> <br/>
![image](https://github.com/user-attachments/assets/45b2379b-228b-4300-ae63-71c5e0f395cc)
