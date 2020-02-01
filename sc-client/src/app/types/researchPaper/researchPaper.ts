import {Author} from '../author';

export interface ResearchPaper {

  id: number;
  title: string;
  paperAbstract: string;
  authors: Author[];

}
