import {Crenel} from "./crenel";
import {Room} from "./room";

export interface Thesis{
  id: number,
  title : string,
  type : string,
  time: string,
  finalNote: number,
  summary: string,
  crenel : Crenel,
  room : Room
}
