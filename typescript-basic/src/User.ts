import {type Person} from "./types.js";

export default class User implements Person {
  constructor(public name: string, public email: string) {
  }

  logout() {
    console.log(`${this.name} logged out`);
  }
}

export function userHelper() {
  console.log(`USER HELPER!`)
}