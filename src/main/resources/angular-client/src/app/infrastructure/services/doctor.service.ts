import {Injectable} from '@angular/core';
import {ApiService} from "@core/services/api.service";
import {DoctorRepository} from "@domain/interfaces/doctor-repository";
import {Doctor} from "@domain/models/doctor";
import {map, Observable, tap} from "rxjs";
import {MedicalConsultationRegister} from "@domain/models/medical-consultation-register";
import {MedicalConsultation} from "@domain/models/medical-consultation";
import {Patient} from "@domain/models/patient";
import {Page} from "@domain/models/page";
import {PageRequest} from "@domain/models/page-request";

@Injectable({
    providedIn: 'root'
})
export class DoctorService extends ApiService implements DoctorRepository {

    constructor() {
        super(Doctor.serviceName);
    }

    getAll(pageRequest: PageRequest): Observable<Page<Doctor>> {
        let pageParameters = pageRequest.toQueryString();
        return this._http.get<Page<Doctor>>(`${this._endpoint}?${pageParameters}`)
            .pipe(
                tap((data: Page<Doctor>) => console.debug("get doctors: ", data)),
                map((data: Page<Doctor>) => new Page<Doctor>(Doctor, data))
            );
    }

    get(id: number): Observable<Doctor> {
        return this._http.get<Doctor>(`${this._endpoint}/${id}`)
            .pipe(tap((data: Doctor) => console.debug("get doctor: ", data)));
    }

    create(doctor: Doctor): Observable<Doctor> {
        return this._http.post<Doctor>(`${this._endpoint}`, doctor)
            .pipe(tap((data: Doctor) => console.debug("create doctor: ", data)));
    }

    update(id: number, doctor: Doctor): Observable<Doctor> {
        return this._http.put<Doctor>(`${this._endpoint}/${id}`, doctor)
            .pipe(tap((data: Doctor) => console.debug("update doctor: ", data)));
    }

    delete(id: number): Observable<void> {
        return this._http.delete<void>(`${this._endpoint}/${id}`)
            .pipe(tap(_ => console.debug("delete doctor: ", id)));
    }

    getMedicalConsultations(doctorId: number): Observable<MedicalConsultation[]> {
        return this._http.get<MedicalConsultation[]>(`${this._endpoint}/${doctorId}/${MedicalConsultation.serviceName}`)
            .pipe(tap((data: MedicalConsultation[]) => console.debug("get medical consultations: ", data)));
    }

    createMedicalConsultation(medicalConsultation: MedicalConsultationRegister): Observable<MedicalConsultation> {
        return this._http.post<MedicalConsultation>(`${this._endpoint}/${MedicalConsultation.serviceName}`, medicalConsultation)
    }

    updateMedicalConsultation(id: number, medicalConsultation: MedicalConsultationRegister): Observable<MedicalConsultation> {
        return this._http.put<MedicalConsultation>(`${this._endpoint}/${MedicalConsultation.serviceName}/${id}`, medicalConsultation)
            .pipe(tap((data: MedicalConsultation) => console.debug("update medical consultation: ", data)));
    }

    deleteMedicalConsultation(id: number): Observable<void> {
        return this._http.delete<void>(`${this._endpoint}/${MedicalConsultation.serviceName}/${id}`)
            .pipe(tap(_ => console.debug("delete medical consultation: ", id)));
    }

    getPatients(doctorId: number): Observable<Patient[]> {
        return this._http.get<Patient[]>(`${this._endpoint}/${doctorId}/${Patient.serviceName}`)
            .pipe(
                tap((data: Patient[]) => console.debug("get patients: ", data)),
                map((data: Patient[]) => data.map((patient: Patient) => new Patient(patient)))
            );
    }
}
