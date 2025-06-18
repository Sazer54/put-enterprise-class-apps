import { Component } from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';
import {StudentsComponent} from './students/students.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, StudentsComponent, RouterLink],
  templateUrl: './app.html',
  standalone: true,
  styleUrl: './app.css'
})
export class App {
  protected title = 'StudentsFront';
}
