import {MedicalConsultation} from "@domain/models/medical-consultation";
import {DoctorRegister} from "@domain/models/doctor-register";

export const DOCTOR_SERVICE_NAME = "doctors"

export class Doctor extends DoctorRegister {
    private _id: number = 0;
    private _medicalConsultations: MedicalConsultation[] = [];


    constructor(json: any) {
        super(json);
        Object.assign(this, json);
    }

    get id(): number {
        return this._id;
    }

    set id(value: number) {
        this._id = value;
    }

    get medicalConsultations(): MedicalConsultation[] {
        return this._medicalConsultations;
    }

    set medicalConsultations(value: MedicalConsultation[]) {
        this._medicalConsultations = value;
    }

    static get serviceName(): string {
        return DOCTOR_SERVICE_NAME;
    }
}
