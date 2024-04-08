export class Page<EntityType> {
    private _totalPages: number = 0;
    private _totalElements: number = 0;
    private _currentPage: number = 0;
    private _size: number = 0;
    private _contentSize: number = 0;
    private _isFirst: boolean = false;
    private _isLast: boolean = false;
    private _isEmpty: boolean = true;
    private _content: EntityType[] = [];

    private _constructorReference: { new (json:any): EntityType };

    constructor(constructorReference: { new (json:any): EntityType }, json: any) {
        this._constructorReference = constructorReference;
        Object.assign(this, json);
    }

    get totalPages(): number {
        return this._totalPages;
    }

    set totalPages(value: number) {
        this._totalPages = value;
    }

    get totalElements(): number {
        return this._totalElements;
    }

    set totalElements(value: number) {
        this._totalElements = value;
    }

    get currentPage(): number {
        return this._currentPage;
    }

    set currentPage(value: number) {
        this._currentPage = value;
    }

    get size(): number {
        return this._size;
    }

    set size(value: number) {
        this._size = value;
    }

    get contentSize(): number {
        return this._contentSize;
    }

    set contentSize(value: number) {
        this._contentSize = value;
    }

    get isFirst(): boolean {
        return this._isFirst;
    }

    set isFirst(value: boolean) {
        this._isFirst = value;
    }

    get isLast(): boolean {
        return this._isLast;
    }

    set isLast(value: boolean) {
        this._isLast = value;
    }

    get isEmpty(): boolean {
        return this._isEmpty;
    }

    set isEmpty(value: boolean) {
        this._isEmpty = value;
    }

    get content(): EntityType[] {
        return this._content;
    }

    set content(value: EntityType[]) {
        this._content = value.map((data: any) => this.createInstance(data));
    }

    createInstance(data: any): EntityType {
        return new this._constructorReference(data)
    }
}
