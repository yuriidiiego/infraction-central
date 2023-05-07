FROM node:20

WORKDIR /app

ENV PATH /app/node_modules/.bin:$PATH

COPY package.json ./

RUN yarn 

COPY . .

EXPOSE 5173

CMD ["yarn", "dev"]