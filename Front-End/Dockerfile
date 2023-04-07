FROM node:19-alpine as build
WORKDIR /Front-End
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build --prod
FROM nginx:alpine
RUN rm /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/conf.d
COPY --from=build Front-End/dist/front-end /usr/share/nginx/html
EXPOSE 80
CMD ["nginx","-g","daemon off;"]