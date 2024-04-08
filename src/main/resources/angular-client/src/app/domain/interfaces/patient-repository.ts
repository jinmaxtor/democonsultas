import {Observable} from "rxjs";
import {Patient} from "@domain/models/patient";
import {PatientRegister} from "@domain/models/patient-register";
import {MedicalConsultation} from "@domain/models/medical-consultation";

export abstract class PatientRepository {
  abstract getAll(): Observable<Patient[]>;
  abstract get(id: number): Observable<Patient>;
  abstract create(patient: PatientRegister): Observable<Patient>;
  abstract update(id:number, patient: PatientRegister): Observable<Patient>;
  abstract delete(id: number): Observable<void>;
  abstract getMedicalConsultations(patientId: number): Observable<MedicalConsultation[]>;
}
