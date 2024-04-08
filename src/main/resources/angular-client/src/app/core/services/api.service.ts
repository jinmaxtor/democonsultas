import {environment} from "@env/environment";
import {HttpClient} from "@angular/common/http";
import {inject} from "@angular/core";

export class ApiService {
  private readonly _baseApiUrl: string = environment.apiBaseUrl;
  protected readonly _http: HttpClient = inject(HttpClient);
  protected readonly _endpoint: string;

  constructor(endpoint: string) {
    this._endpoint = `${this._baseApiUrl}/${endpoint}`;
  }


}
