import { Component, inject, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { SidebarService } from '../../core/services/sidebar.service';

@Component({
  selector: 'app-sidebar',
  imports: [RouterLink],
  templateUrl: './sidebar.html',
  styleUrl: './sidebar.css'
})
export class Sidebar implements OnInit {
 private sideBarService: SidebarService
 constructor(){
  this.sideBarService = inject(SidebarService)
 }

 isExpanded = true
  ngOnInit(): void {
    this.sideBarService.isExpanded$.subscribe(expanded =>{
      this.isExpanded = expanded
    })
  }
}
