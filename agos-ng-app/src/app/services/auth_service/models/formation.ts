import {Department} from "./department";

export interface Formation {
  id: number,
  name: string,
  description: string,
  department: Department
}
