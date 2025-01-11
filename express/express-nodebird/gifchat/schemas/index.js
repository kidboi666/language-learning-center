const mongoose = require('mongoose');

const { MONGODB_ID, MONGODB_PASSWORD, NODE_ENV } = process.env;
const MONGODB_URL = `mongodb://${MONGODB_ID}:${MONGODB_PASSWORD}@localhost:27017/admin`;

const connect = () => {
  if (NODE_ENV === 'production') {
    mongoose.set('debug', true);
  }

  mongoose
    .connect(MONGODB_URL, {
      dbName: 'admin',
    })
    .then(() => {
      console.log('MongoDB Connected!');
    })
    .catch((err) => {
      console.error(err);
    });
};

mongoose.connection.on('error', (err) => {
  console.error('몽고디비 연결 에러', err);
});

mongoose.connection.on('disconnected', (err) => {
  console.error('몽고디비 연결이 끊겼습니다. 연결을 재시도합니다.');
  connect();
});

module.exports = connect;
