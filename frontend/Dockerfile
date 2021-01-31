# Single stage - If build is already created

# FROM nginx:alpine
# RUN rm -rf /usr/share/nginx/html/*
# COPY /dist/frontend /usr/share/nginx/html
# COPY nginx.conf /etc/nginx/conf.d/default.conf
# EXPOSE 80

# Multistage build
FROM node:14 As builder
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build --prod

FROM nginx:alpine
RUN rm -rf /usr/share/nginx/html/*
COPY --from=builder /app/dist/frontend /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80

# Not required as its alreadypresent in nginx image
# CMD ["nginx", "-g", "daemon off;"]
