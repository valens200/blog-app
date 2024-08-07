# Blog Application

This is a simple blog application developed using React for the frontend and Spring Boot for the backend. All features are implemented except for editing comments. The application can be run locally after proper configuration and installation of all dependencies.

## Table of Contents

1. [Features](#features)
2. [Technologies Used](#technologies-used)
3. [Prerequisites](#prerequisites)
4. [Installation](#installation)
5. [Configuration](#configuration)
6. [Database Setup](#database-setup)
7. [Running the Application](#running-the-application)
8. [API Endpoints](#api-endpoints)
9. [Authentication Process](#authentication-process)
10. [User manual](#user-manual)
11. [Project structure](#project-structure)

## Features

- User registration and login
- Blog post management (create, read, update, delete)
- Comment management (add, read, delete)
- Secure authentication using JWT and Spring security.

## Technologies Used

- **Frontend**: React
- **Backend**: Spring Boot with Spring Data JPA, Spring security, Hibernate..
- **Database**: PostgreSQL

## Prerequisites

- Node.js and npm (for frontend)
- Java Development Kit (JDK) (for backend)
- PostgreSQL
- Git

## Installation

### Backend

### Using Docker

If you don't have either javascript or java environment, please use docker to build the app and run it with zero issues

```sh
docker build -t <image_name> .
docker run -p <desired port>:<desired port> <image_name>
```

- You can also run it locally with the following steps if you don't prefer Docker.

1. Clone the repository:
   ```sh
   git clone https://github.com/valens200/blog-app
   ```
2. Navigate to the backend directory:
   ```sh
   cd server
   ```
3. Install dependencies:
   ```sh
   ./mvnw install
   ```

### Frontend

1. Navigate to the frontend directory:
   ```sh
   cd client
   ```
2. Install dependencies:
   ```sh
   npm install
   ```

## Configuration

## Project Structure

### Backend

- `src/main/java/valens/qt/v1`:
  - `annotations`: Custom annotations
    - `validators`: Annotation validators (e.g., `EnableCors`, `ValidPassword`)
  - `audits`: Audit-related classes
  - `config`: Configuration classes
  - `controllers`: REST controllers
  - `dtos`: Data Transfer Objects
  - `exceptions`: Exception handlers
  - `models`: Data models
    - `enums`: Enumerations
      - `Comment`
      - `Notification`
      - `Person`
      - `Post`

### Frontend

- `src/components`: Contains React components
- `src/hooks`: Contains custom hooks for the application
- `src/pages`: Contains page components
- `src/utils`: Contains utils functions that are reusable accroass pages
- `src/types`: Contains Typescript function for the application
-

### Backend

1. Manipulate the `application.properties` file in the `src/main/resources` directory with the following content:
   ```properties
   spring.jpa.database=postgresql
   spring.datasource.username=postgres
   spring.datasource.password=valens
   spring.jpa.hibernate.ddl-auto=update
   spring.datasource.url=jdbc:postgresql://localhost:5432/qt?useSSL=false
   spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
   ```
   In the aboove file, replace the database name `blog-app` with your database name and also change the `spring.datasource.username` and `spring.datasource.password` as well.

## Database Setup ( PostgreSQL)

1. Create the database:
   ```sql
   CREATE DATABASE blog;
   ```
2. Create the following tables :

#### If you don't want to create these days manually, go there and the run the application they will be created automatically by `Hibernate`

```
-- Create profiles table
CREATE TABLE profiles (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL -- Assuming EAccountStatus is an enum, you might need to handle this as text or integer
);

-- Create roles table
CREATE TABLE roles (
    role_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    role_name VARCHAR(255) NOT NULL
);

-- Create join table for many-to-many relationship between profiles and roles
CREATE TABLE profile_roles (
    profile_id UUID,
    role_id UUID,
    PRIMARY KEY (profile_id, role_id),
    CONSTRAINT fk_profile FOREIGN KEY(profile_id) REFERENCES profiles(id),
    CONSTRAINT fk_role FOREIGN KEY(role_id) REFERENCES roles(role_id)
);

-- Create posts table
CREATE TABLE posts (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(255),
    image_url VARCHAR(255),
    content TEXT,
    author_id UUID,
    is_owner BOOLEAN,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT fk_author FOREIGN KEY(author_id) REFERENCES profiles(id)
);

-- Create comments table
CREATE TABLE comments (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(255),
    content TEXT,
    author_id UUID,
    post_id UUID,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT fk_author_comment FOREIGN KEY(author_id) REFERENCES profiles(id),
    CONSTRAINT fk_post_comment FOREIGN KEY(post_id) REFERENCES posts(id)
);

-- Create users table
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_name VARCHAR(255) NOT NULL,
    mobile VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    dob DATE,
    profile_id UUID,
    CONSTRAINT fk_profile_user FOREIGN KEY(profile_id) REFERENCES profiles(id)
);

```

## Running the Application

### Backend

1. Start the Spring Boot application:
   ```sh
   ./mvnw spring-boot:run
   ```

### Frontend

1. Start the React application:
   ```sh
   npm start
   ```

## API Endpoints

### Authentication

- **POST /api/auth/register**: Register a new user
- **POST /api/auth/login**: Login a user

### Posts

![](https://s3.hedgedoc.org/hd1-demo/uploads/467a2d57-772f-480a-ac8a-09448843d7cd.png)

### Comments

![](https://s3.hedgedoc.org/hd1-demo/uploads/d162571a-df7e-4cb0-aabb-55ffb210542f.png)

## Authentication Process

1. **Registration**: Users can register by sending a POST request to `/api/auth/register` with their username, email and password.
2. **Login**: Users can login by sending a POST request to `/api/auth/login` with their email and password. A JWT token will be returned, which should be included in the `Authorization` header for subsequent requests.

## User manual

## Authentication

- Strong authentication and validation mechanism using were used to ensure data integrity and confidentiality.
- Also sensitive data such as passswords are hashed to prevent from being visible to un authorized access.
  -- Data validation : `yup` and `regular expressions` on the frontend plus `Javax.validation` on the backend.
  -- Data encryption and hashing: `JWT` and `SHA-256` hashing algorithm.
  -- Security implementation: `Spring security`

#### Registration ( username , email and password)

![](https://s3.hedgedoc.org/hd1-demo/uploads/506c6a8b-deef-42c3-9744-27310b420068.png)

#### Login ( email and password)

![](https://s3.hedgedoc.org/hd1-demo/uploads/31069e98-ce10-42ee-ba9c-344aae5cf93b.png)

## Posts and comments management

#### View comments for each test

![](https://s3.hedgedoc.org/hd1-demo/uploads/4e217993-44f3-4973-b567-0c1536b36e5c.png)

#### Edit comment

![](https://s3.hedgedoc.org/hd1-demo/uploads/1e276efe-d467-48b5-b761-f430fe5d6565.png)

![](https://s3.hedgedoc.org/hd1-demo/uploads/de485f5d-06fe-4992-b442-71a481c0e6e0.png)

#### Delete comment

![](https://s3.hedgedoc.org/hd1-demo/uploads/767778f0-fb52-433f-96c7-7945f1c79c4e.png)

![](https://s3.hedgedoc.org/hd1-demo/uploads/ad1174e9-0f8a-43cd-9d09-f1d33b6e1cbd.png)

#### Edit post

![](https://s3.hedgedoc.org/hd1-demo/uploads/9c632667-e78d-46e0-b340-5b3f94d71da0.png)

![](https://s3.hedgedoc.org/hd1-demo/uploads/36160107-ed36-46a1-92e4-03e686b4a8f7.png)

#### Delete post

![](https://s3.hedgedoc.org/hd1-demo/uploads/4f554b5e-ef48-41f2-87af-08cdc1a60103.png)

![](https://s3.hedgedoc.org/hd1-demo/uploads/9c98ed6e-2e35-4acf-9cab-298d5a6b428d.png)

####

## Here is a detailed vieo showing all functionalities https://drive.google.com/file/d/1jIH4VThYDQ-qRzLJ4DGlRQ6ISkrmHdEx/view?usp=sharing

---

This documentation should help you set up and run the application locally. If you encounter any issues, please refer to the project's README or contact me at valensniyonsenga2003@gmail.com.
