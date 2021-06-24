export class Account {
  id: number;
  username: string;
  password: string;
  role: number;

  constructor(id: number, username: string, password: string, role: number) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.role = role;
  }

}
