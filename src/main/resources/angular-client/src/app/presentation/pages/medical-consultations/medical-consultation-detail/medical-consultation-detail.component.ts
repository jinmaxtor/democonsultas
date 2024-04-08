import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-medical-consultation-detail',
  standalone: true,
  imports: [],
  templateUrl: './medical-consultation-detail.component.html',
  styleUrl: './medical-consultation-detail.component.scss'
})
export default class MedicalConsultationDetailComponent {

    id: number = 0;
    doctorId: number = 0;
    patientId: number = 0;

    constructor(private route: ActivatedRoute) {
        this.route.params.subscribe(params => {
            this.id = params['id'];
            this.doctorId = params['doctorId'];
            this.patientId = params['patientId'];
        });
    }
}
