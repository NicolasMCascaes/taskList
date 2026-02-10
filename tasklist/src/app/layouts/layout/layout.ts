import { Component } from '@angular/core';
import { Header } from "../../features/header/header";
import { Sidebar } from "../../features/sidebar/sidebar";
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-layout',
  imports: [Header, RouterOutlet, Sidebar],
  templateUrl: './layout.html',
  styleUrl: './layout.css'
})
export class Layout {

}
