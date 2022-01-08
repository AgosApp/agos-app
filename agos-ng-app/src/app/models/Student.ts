import {Formation} from "./formation";

export interface Student {
  id : number,
  firstName: string,
  lastName: string,
  username: string,
  password: string,
  formation : Formation
}
