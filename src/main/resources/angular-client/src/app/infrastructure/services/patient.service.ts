import {Injectable} from '@angular/core';
import {ApiService} from "@core/services/api.service";
import {PatientRepository} from "@domain/interfaces/patient-repository";
import {PatientRegister} from "@domain/models/patient-register";
import {map, Observable, tap} from "rxjs";
import {Patient} from "@domain/models/patient";
import {MedicalConsultation} from "@domain/models/medical-consultation";

@Injectable({
    providedIn: 'root'
})
export class PatientService extends ApiService implements PatientRepository {

    constructor() {
        super("patients");
    }

    getAll(): Observable<Patient[]> {
        return this._http.get<Patient[]>(this._endpoint)
            .pipe(
                tap((data: Patient[]) => console.debug("get patients: ", data)),
                map((data: Patient[]) => data.map((patient: Patient) => new Patient(patient)))
            );
    }

    get(id: number): Observable<Patient> {
        return this._http.get<Patient>(`${this._endpoint}/${id}`)
            .pipe(tap((data: Patient) => console.debug("get patient: ", data)));
    }

    create(patient: PatientRegister): Observable<Patient> {
        return this._http.post<Patient>(`${this._endpoint}`, patient)
            .pipe(tap((data: Patient) => console.debug("create patient: ", data)));
    }

    update(id: number, patient: PatientRegister): Observable<Patient> {
        return this._http.put<Patient>(`${this._endpoint}/${id}`, patient)
            .pipe(tap((data: Patient) => console.debug("update patient: ", data)));
    }

    delete(id: number): Observable<void> {
        return this._http.delete<void>(`${this._endpoint}/${id}`)
            .pipe(tap(_ => console.debug("delete patient: ", id)));
    }

    getMedicalConsultations(patientId: number): Observable<MedicalConsultation[]> {
        return this._http.get<MedicalConsultation[]>(`${this._endpoint}/${patientId}/${MedicalConsultation.serviceName}`)
            .pipe(tap((data: MedicalConsultation[]) => console.debug("get medical consultations: ", data)));
    }
}
