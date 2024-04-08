export abstract class User {
    private _firstName: string = "";
    private _lastName: string = ""
    private _birthDate: Date = new Date();
    private _email: string = ""
    private _address: string = "";
    private _image: string = "";

    constructor(json: any) {
        Object.assign(this, json);
    }

    get firstName(): string {
        return this._firstName;
    }

    set firstName(value: string) {
        this._firstName = value;
    }

    get lastName(): string {
        return this._lastName;
    }

    set lastName(value: string) {
        this._lastName = value;
    }

    get birthDate(): Date {
        return this._birthDate;
    }

    set birthDate(value: Date) {
        this._birthDate = value;
    }

    get email(): string {
        return this._email;
    }

    set email(value: string) {
        this._email = value;
    }

    get address(): string {
        return this._address;
    }

    set address(value: string) {
        this._address = value;
    }

    get image(): string {
        return this._image;
    }

    set image(value: string) {
        this._image = value;
    }

    get fullName(): string {
        return `${this.firstName} ${this.lastName}`;
    }
}
