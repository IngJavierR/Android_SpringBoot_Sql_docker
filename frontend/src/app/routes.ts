import {Component, OnInit} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainComponent} from './main/main';
import {AboutComponent} from './about/about';
import {ContactComponent} from './contact/contact';
import { DataService } from './services/data.service';

@Component({
  selector: 'fountain-root',
  template: require('./routes.html')
})
export class RootComponent implements OnInit{

  isLoading: boolean = false;
  constructor(private _data: DataService) { }
  
  ngOnInit(){ 
    this._data
      .getIsLoadingEvent()
      .subscribe(x => {
        this.isLoading = x;
      });
  }

}

export const routes: Routes = [
  {
    path: '',
    component: MainComponent
  },
  {
    path: 'about',
    component: AboutComponent
  },
  {
    path: 'contact',
    component: ContactComponent
  }
];

export const routing = RouterModule.forRoot(routes);
