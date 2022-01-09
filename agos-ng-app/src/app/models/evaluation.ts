import {Professor} from "./professor";
import {Thesis} from "./thesis";

export interface Evaluation{
  id: number,
  remark: null,
  thesisNote: number,
  typeProfessor: string,
  thesis : Thesis,
  professor : Professor
}
