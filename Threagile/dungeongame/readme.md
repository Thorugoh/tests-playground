# Dungeon Game REST Service

This project provides a RESTful API to solve the "Dungeon Game" problem from LeetCode. The solution is implemented in **Java 23** using the **Spring Boot** framework, with game results persisted in a **PostgreSQL** database. The entire application stack is containerized using **Docker** for easy setup and deployment.

## Features

- **REST API**: Exposes a single endpoint to calculate the minimum health required for the knight.

- **Dynamic Programming Solution**: Implements an efficient, space-optimized dynamic programming algorithm.

- **Database Persistence**: Saves every calculation request and its result to a PostgreSQL database.

- **Containerized**: Fully containerized with Docker and Docker Compose for one-command setup.

## Technology Stack

- **Backend**: Java 23, Spring Boot 3.3

- **Database**: PostgreSQL 16

- **Build Tool**: Maven

- **Deployment**: Docker & Docker Compose

- **Threat Modeling**: Threagile

## Prerequisites

You must have **Docker** and **Docker Compose** installed on your machine. No local installation of Java, Maven, or PostgreSQL is required.

- [Install Docker Desktop](https://www.docker.com/products/docker-desktop/)

## How to Run

1. **Clone the repository** or ensure all project files (`pom.xml`, `Dockerfile`, `docker-compose.yml`, `threagile.yaml`, and the `src` folder) are in the same directory.

2. **Open a terminal** or command prompt and navigate to the root directory of the project.

3. **Build and start the services** using Docker Compose:

```
cd Threagile
cd dungeongame

docker-compose up --build
```

This command will:

- Build the Java application's Docker image (using Java 23).

- Start a PostgreSQL container.

- Start the Spring Boot application container.

The API will be running and available at `http://localhost:8080`.

## API Endpoint Documentation

### Calculate Minimum Health

- **URL**: `/api/dungeon/calculate`

- **Method**: `POST`

- **Headers**: `Content-Type: application/json`

#### Request Body

**Example:**

```

{
"dungeon": [
[-2, -3, 3],
[-5, -10, 1],
[10, 30, -5]
]
}

```

#### Responses

- **Success (200 OK)**:

```

{
"minInitialHealth": 7
}

```

- **Bad Request (400 Bad Request)**:

```

{
"error": "Dungeon layout cannot be null."
}

```

## How to Test the API

Run the following `curl` command in your terminal after starting the application:

```

curl -X POST http://localhost:8080/api/dungeon/calculate
\-H "Content-Type: application/json"
\-d '{
"dungeon": [
[-2, -3, 3],
[-5, -10, 1],
[10, 30, -5]
]
}'

```

## Automated Threat Modeling with Threagile

This project uses [Threagile](https://threagile.io/) for automated threat modeling as code. The entire architecture is defined in the `threagile.yaml` file.

### How to Run the Analysis

1. Make sure Docker is running.

2. From the root directory of the project, run the following command in your terminal:

```

docker run --rm -it
\-v "$(pwd)":/app/work
threagile/threagile -verbose -model /app/work/threagile.yaml

```

Win:

```
docker run --rm -it `
  -v "${PWD}:/app/work" `
  threagile/threagile `
  -verbose `
  -model /app/work/threagile.yaml

```

This command will:

- Download the Threagile Docker image.

- Mount your current project directory (`pwd`) into the container.

- Run the analysis against your `threagile.yaml` file.

Upon completion, Threagile will create a `threagile-report` folder in your project directory containing a detailed HTML report, data-flow diagrams, and other analysis artifacts.
