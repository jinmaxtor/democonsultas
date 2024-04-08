import {Doctor} from "@domain/models/doctor";
import {Observable} from "rxjs";
import {MedicalConsultationRegister} from "@domain/models/medical-consultation-register";
import {MedicalConsultation} from "@domain/models/medical-consultation";
import {Patient} from "@domain/models/patient";
import {Page} from "@domain/models/page";
import {PageRequest} from "@domain/models/page-request";

export abstract class DoctorRepository {
    abstract getAll(pageRequest: PageRequest): Observable<Page<Doctor>>
    abstract get(id: number): Observable<Doctor>
    abstract create(doctor: Doctor): Observable<Doctor>
    abstract update(id: number, doctor: Doctor): Observable<Doctor>
    abstract delete(id: number): Observable<void>
    abstract createMedicalConsultation(medicalConsultation: MedicalConsultationRegister): Observable<MedicalConsultation>
    abstract updateMedicalConsultation(id: number, medicalConsultation: MedicalConsultationRegister): Observable<MedicalConsultation>
    abstract deleteMedicalConsultation(id: number): Observable<void>
    abstract getMedicalConsultations(doctorId: number): Observable<MedicalConsultation[]>
    abstract getPatients(doctorId: number): Observable<Patient[]>
}
