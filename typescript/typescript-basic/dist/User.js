export default class User {
    constructor(name, email) {
        this.name = name;
        this.email = email;
    }
    logout() {
        console.log(`${this.name} logged out`);
    }
}
export function userHelper() {
    console.log(`USER HELPER!`);
}
