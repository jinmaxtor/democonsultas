import {User} from "@domain/models/user";

export class DoctorRegister extends User {
    private _specialty: string = "";

    constructor(json: any) {
        super(json);

        Object.assign(this, json);
    }

    get specialty(): string {
        return this._specialty;
    }

    set specialty(value: string) {
        this._specialty = value;
    }
}
