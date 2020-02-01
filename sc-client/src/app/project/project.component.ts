import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Magazine} from '../../../../pc-client/src/app/types/magazine';
import {ResearchPaper} from '../types/researchPaper/researchPaper';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {
  //
  // private projects: ResearchPaper[];
  // private magazineId: string;

  constructor(private http: HttpClient, private route: ActivatedRoute) {
    // console.log('usao');
  }

  ngOnInit() {
    // const id = +this.route.snapshot.params['code'];
    // console.log(id);
  //   console.log('nginit');
  //   this.route.paramMap.subscribe(
  //     param => {
  //       this.magazineId = param.get('magazineId');
  //     }
  //   );
  //   this.getProjects();
  // }

  //
  // getProjects() {
  //   console.log(this.magazineId);
  //   this.http.get<ResearchPaper>('http://localhost:8095/research-papers/' + this.magazineId).subscribe((res) => {
  //     console.log(res);
  //   });
  }

}
