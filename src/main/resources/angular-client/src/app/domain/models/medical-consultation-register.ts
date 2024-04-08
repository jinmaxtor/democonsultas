export class MedicalConsultationRegister {
    private _diagnostic: string;
    private _treatment: string;
    private _date: Date;

    private _doctorId: number;
    private _patientId: number;

    constructor(diagnostic: string, treatment: string, date: Date, doctorId: number, patientId: number) {
        this._diagnostic = diagnostic;
        this._treatment = treatment;
        this._date = date;
        this._doctorId = doctorId;
        this._patientId = patientId;
    }

    get diagnostic(): string {
        return this._diagnostic;
    }

    set diagnostic(value: string) {
        this._diagnostic = value;
    }

    get treatment(): string {
        return this._treatment;
    }

    set treatment(value: string) {
        this._treatment = value;
    }

    get date(): Date {
        return this._date;
    }

    set date(value: Date) {
        this._date = value;
    }

    get doctorId(): number {
        return this._doctorId;
    }

    set doctorId(value: number) {
        this._doctorId = value;
    }

    get patientId(): number {
        return this._patientId;
    }

    set patientId(value: number) {
        this._patientId = value;
    }
}
