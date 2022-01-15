import {Session} from "./session.model";

export interface Crenel{
   id: number,
   day : string,
   startTime: string,
   endTime : string,
   session : Session
}
