# Stage 1: Build the Angular application
FROM node:20-alpine AS build

WORKDIR /app

# Copy package files
COPY package*.json ./

# Install dependencies
RUN npm ci

# Copy application source
COPY . .

# Build the application for production
RUN npm run build

# Stage 2: Serve the application with nginx
FROM nginx:alpine

# Copy built application from build stage
COPY --from=build /app/dist/frontend-app/browser /usr/share/nginx/html

# Copy nginx configuration for Angular routing
RUN echo 'server { \
    listen 80; \
    server_name localhost; \
    root /usr/share/nginx/html; \
    index index.html; \
    location / { \
        try_files $uri $uri/ /index.html; \
    } \
}' > /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
