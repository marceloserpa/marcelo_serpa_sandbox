# KrakenD + Keycloak Integration

This POC aims to implement the authentication/authorization flows using Krakend as API Gateway and Keycloak as IdP.

## Architecture Overview

```mermaid
sequenceDiagram
    participant Client
    participant Keycloak
    participant KrakenD
    participant MoviesAPI

    Note over Client,MoviesAPI: Authentication & Authorization Flow

    Client->>Keycloak: 1. POST /token (username + password)
    Note right of Keycloak: Validates credentials
    Keycloak-->>Client: 2. Returns JWT Token

    Client->>KrakenD: 3. GET /api/movie/v1/movies<br/>Authorization: Bearer {token}
    Note right of KrakenD: Validates JWT:<br/>- Fetches JWK from Keycloak<br/>- Verifies signature<br/>- Checks issuer<br/>- Validates expiration
    
    alt Token Valid
        KrakenD->>MoviesAPI: 4. Forward request
        MoviesAPI-->>KrakenD: 5. Returns movie data
        KrakenD-->>Client: 6. Returns response
    else Token Invalid
        KrakenD-->>Client: 401 Unauthorized
    end
```

## System Components

```mermaid
graph TB
    subgraph "System Components"
        Client[Client]
        
        subgraph "Authentication"
            Keycloak[Keycloak<br/>Port 8080<br/>Realm: marcelocorp]
            Postgres[(PostgreSQL<br/>Database)]
        end
        
        subgraph "API Gateway"
            KrakenD[KrakenD Gateway<br/>Port 9000<br/>JWT Validator]
        end
        
        subgraph "Backend Services"
            MoviesAPI[Movies API<br/>Port 3000]
        end
    end
    
    Client -->|1. Auth Request| Keycloak
    Keycloak -->|Uses| Postgres
    Client -->|2. API Request + JWT| KrakenD
    KrakenD -->|Validates JWT| Keycloak
    KrakenD -->|3. Proxies Request| MoviesAPI
    
    style Keycloak fill:#6F50D9,stroke:#333,stroke-width:2px
    style KrakenD fill:#6F50D9,stroke:#333,stroke-width:2px
    style MoviesAPI fill:#6F50D9,stroke:#333,stroke-width:2px
    style Postgres fill:#6F50D9,stroke:#333,stroke-width:2px
```


## Keycloak Configuration

After starting the services, you need to configure Keycloak:

1. Access Keycloak admin console: http://localhost:8080
2. Login with credentials:
   - Username: `admin`
   - Password: `admin_password`
3. Create a realm named `marcelocorp`
4. Create a client named `movies-app`
5. Create a user named `marcelo` with password `marcelo`

## Running Tests

### 1. Get Authentication Token

```bash
# Run the authentication script
./scripts/auth-request.sh
```

This will return a JSON response with an `access_token`. Copy the token value.

**Example response:**
```json
{
  "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9...",
  "expires_in": 300,
  "token_type": "Bearer"
}
```

### 2. Make API Request with Token

```bash
# Use the token to call the movies API through KrakenD
./scripts/movies-api-request.sh "YOUR_ACCESS_TOKEN_HERE"
```

**Example:**
```bash
./scripts/movies-api-request.sh "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9..."
```

**Expected response:**
```json
[
  {
    "title": "End Game"
  }
]
```

### 3. Test Without Token (Should Fail)

```bash
curl -X GET "http://localhost:9000/api/movie/v1/movies"
```

This should return a 401 Unauthorized error, demonstrating that the gateway is properly protecting the API.

## Endpoints

- **Keycloak Admin**: http://localhost:8080
- **Keycloak Token Endpoint**: http://localhost:8080/realms/marcelocorp/protocol/openid-connect/token
- **KrakenD Gateway**: http://localhost:9000
- **Movies API (via Gateway)**: http://localhost:9000/api/movie/v1/movies
- **Movies API (direct)**: http://localhost:3000/movie/v1/movies/
