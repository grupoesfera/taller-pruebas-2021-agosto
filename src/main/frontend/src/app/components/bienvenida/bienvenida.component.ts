import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-bienvenida',
  templateUrl: './bienvenida.component.html',
  styleUrls: ['./bienvenida.component.css']
})
export class BienvenidaComponent implements OnInit {

  mostrar: boolean = true;

  constructor() { }

  ngOnInit(): void {
  }

  iniciar() {
    this.mostrar = false;
  }
}
