FROM node:20-alpine as build

WORKDIR /app
COPY package*.json ./
RUN npm install

COPY . .

RUN rm -rf .env .env.*

ARG VITE_API_URL=http://localhost:8080

ENV VITE_API_URL=$VITE_API_URL

RUN echo "BUILDING FRONTEND WITH API URL: $VITE_API_URL"

RUN npm run build

FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]