import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDestinatair } from '../destinatair.model';

@Component({
  selector: 'jhi-destinatair-detail',
  templateUrl: './destinatair-detail.component.html',
})
export class DestinatairDetailComponent implements OnInit {
  destinatair: IDestinatair | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ destinatair }) => {
      this.destinatair = destinatair;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
