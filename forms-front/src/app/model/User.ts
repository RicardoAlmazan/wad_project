export class User {
  id: number;
  nombres: string;
  apellidos: string;
  email: string;
  authenticatedAt: number;
  userType: boolean;

  static load(): User
  {
    const usrJson = localStorage.getItem('user');
    let user: any = null;

    if (usrJson != null)
    {
      user = JSON.parse(usrJson);
    }

    return user;
  }
}