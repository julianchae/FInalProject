export class User {

  id: number | null;
  username: string | null;
  password: string |null;
  email: string | null;
  enabled: boolean | null;
  role: string| null;
  firstName: string | null;
  lastName: string |null;
  imgUrl: string | null;

  constructor(
    id: number | null = 0,
    username: string | null = '',
    password: string |null = '',
    email: string |null = '',
    enabled: boolean | null = true,
    role: string| null = '',
    firstName: string | null = '',
    lastName: string |null = '',
    imgUrl: string | null = ''
  )

  {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.enabled = enabled;
    this.role = role;
    this.firstName = firstName;
    this.lastName = lastName;
    this.imgUrl = imgUrl;
  }


}
