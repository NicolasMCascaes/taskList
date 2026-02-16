import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SidebarService {
  private isExpandedSubject = new BehaviorSubject<boolean>(true)
  public isExpanded$ = this.isExpandedSubject.asObservable()

  toogleSideBar(){
    this.isExpandedSubject.next(!this.isExpandedSubject.value)
  }
  getSideBarState(): boolean{
    return this.isExpandedSubject.value
  }
}
