import { Component } from '@angular/core';

@Component({
  selector: 'app-about',
  imports: [],
  templateUrl: './about.component.html',
  standalone: true,
  styleUrl: './about.component.css'
})
export class AboutComponent {
  get date(): string {
    return new Date().toLocaleDateString();
  }
}
