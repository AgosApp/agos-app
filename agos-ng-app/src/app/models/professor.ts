import {Department} from "./department";

export interface Professor{

  id : number,
  firstName: string,
  lastName: string,
  username: string,
  password: string,
  department : Department
  admin:boolean
}
