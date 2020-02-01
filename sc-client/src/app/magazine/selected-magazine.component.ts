import { Component, OnInit } from '@angular/core';
import {ResearchPaper} from '../types/researchPaper/researchPaper';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-selected-magazine',
  templateUrl: './selected-magazine.component.html',
  styleUrls: ['./selected-magazine.component.css']
})
export class SelectedMagazineComponent implements OnInit {

  private projects: ResearchPaper[];
  private magazineId: string;

  constructor(private http: HttpClient, private route: ActivatedRoute) {

  }

  ngOnInit() {

    this.route.paramMap.subscribe(
      param => {
        this.magazineId = param.get('magazineId');
      }
    );
    this.getProjects();
  }


  getProjects() {
    this.http.get<ResearchPaper[]>('http://localhost:8095/magazine/research-papers/' + this.magazineId).subscribe((res) => {
      console.log(res);
      this.projects = res;
    });
  }
}
