import { sample as randomSample, add, pi } from './utils.js';
import User, { userHelper } from './User.js';
console.log(randomSample([12, 13, 14]));
console.log(add(1, 2));
console.log(pi);
const sample = 1234;
const user = new User('abey', 'mail@gamil.com');
console.log(user.name);
userHelper();
