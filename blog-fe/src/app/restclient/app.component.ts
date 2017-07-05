import { Http} from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable} from "rxjs/Observable";
import {Component} from "@angular/core";

@Component({
  selector: 'app-root',
  template: `<h1>All Posts</h1>
  <ul>
    <li *ngFor="let post of posts">
       {{post.title}}
    </li>
  </ul>
  `})
export class AppComponent {

  posts: Array<string> = [];

  theDataSource: Observable<string>;

  constructor(private http: Http) {

    this.theDataSource = this.http.get('/blog/api/posts')
      .map(res => res.json());
  }

  ngOnInit(){

    // Get the data from the REST server
    this.theDataSource.subscribe(
      data => {
        if (Array.isArray(data)){
          this.posts=data;
        } else{
          this.posts.push(data);
        }
      },
      err =>
        console.log("Can't get products. Error code: %s, URL: %s ",  err.status, err.url),
      () => console.log('Product(s) are retrieved')
    );
  }
}
