import { Injectable, Injector } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, OntimizeBaseService, Util } from 'ontimize-web-ngx';

@Injectable()
export class IBalance extends OntimizeBaseService {

  constructor(protected injector: Injector, private http: HttpClient) {
    super(injector);
  }

  public query(kv?: Object, av?: Array<string>, entity?: string, sqltypes?: Object): Observable<any> {
    const columns= [ 'user_','BALANCE']
    const url = this.constructUrl('http://localhost:33333/users/balance', columns, kv);
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Basic ' + btoa("demo" + ':' + "demouser"), 
        'Content-Type': 'application/json'
      })
    };

    
    return this.doRequest({
      method: 'GET',
      url: url,
      options: httpOptions 
    }); 
  }

  private constructUrl(baseUrl: string, columns: string[], queryObject: any): string {
    let url = `${baseUrl}?columns=${columns.join(',')}`;
    if (queryObject && Object.keys(queryObject).length > 0) {
      url += '&' + this.objectToQueryString(queryObject);
    }

    return url;
  }

  private objectToQueryString(obj: any): string {
    return Object.keys(obj).map(key => `${encodeURIComponent(key)}=${encodeURIComponent(obj[key])}`).join('&');
  }

}

