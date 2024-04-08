import {MedicalConsultationRegister} from "@domain/models/medical-consultation-register";

export const MEDICAL_CONSULTATION_SERVICE_NAME = "medical-consultations";

export class MedicalConsultation extends MedicalConsultationRegister {
    private _id: number;
    private _doctorName: string;
    private _patientName: string;

    constructor(diagnostic: string,
                treatment: string,
                date: Date,
                doctorId: number,
                patientId: number,
                id: number,
                doctorName: string,
                patientName: string) {
        super(diagnostic, treatment, date, doctorId, patientId);
        this._id = id;
        this._doctorName = doctorName;
        this._patientName = patientName;
    }

    get id(): number {
        return this._id;
    }

    set id(value: number) {
        this._id = value;
    }

    get doctorName(): string {
        return this._doctorName;
    }

    set doctorName(value: string) {
        this._doctorName = value;
    }

    get patientName(): string {
        return this._patientName;
    }

    set patientName(value: string) {
        this._patientName = value;
    }

    static get serviceName(): string {
        return MEDICAL_CONSULTATION_SERVICE_NAME;
    }
}
