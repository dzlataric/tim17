import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Magazine} from '../types/magazineType/magazine';
import { Router } from '@angular/router';


@Component({
  selector: 'app-magazine',
  templateUrl: './magazine.component.html',
  styleUrls: ['./magazine.component.css']
})
export class MagazineComponent implements OnInit {

  private magazines: Magazine[];


  constructor(private http: HttpClient, private router: Router) {
    this.getMagazine();
  }

  ngOnInit() {
  }


  getMagazine() {
    this.http.get<Magazine[]>('http://localhost:8095/magazine').subscribe((res) => {
      console.log(res);
      this.magazines = res;
    });

  }

  selectMagazine(magazineId: number): void {
    this.router.navigate(['magazine/' + magazineId]);
  }
}
