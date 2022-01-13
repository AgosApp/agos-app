import {Professor} from "./professor";
import {Thesis} from "./thesis";
import {Criteria} from "./criteria";

export interface CriteriaEvaluation{
  id: number,
  professor: Professor,
  thesis: Thesis,
  note: number,
  criteria : Criteria
}
