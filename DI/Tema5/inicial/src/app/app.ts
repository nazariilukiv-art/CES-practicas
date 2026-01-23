import { Component, signal } from '@angular/core';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  standalone: false,
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('inicial');
  nombre: string = "Borja"
  apellido: string = "Martin herrera"
  curso: number = 2
  nombreCurso: string = "Desarrollo de Interfaces";

  nota: number = 0;


  palsarComenzar(notaInput: string) {
        Swal.fire({
      title: 'Error!',
      text: 'Do you want to continue',
      icon: 'error',
      confirmButtonText: 'Cool'
    })
  }

}
