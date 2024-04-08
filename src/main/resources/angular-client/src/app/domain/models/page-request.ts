export class PageRequest {
    name: string = "";
    page: number = 1;
    size: number = 10;
    sort: string = "id";
    direction: string = "asc";

    toQueryString() {
        const params = new URLSearchParams();
        for (const key in this) {
            let ref = <any>this;
            let value = ref[key];
            if (value) {
                params.append(key, value);
            }
        }
        return params.toString();
    }
}
