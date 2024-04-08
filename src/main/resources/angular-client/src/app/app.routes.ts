import {Routes} from '@angular/router';

export const routes: Routes = [
    {
        path: "doctors",
        loadComponent: () => import('@pages/doctors/doctors.component')
    },
    {
        path: "doctors/:id",
        loadComponent: () => import('@pages/doctors/doctor-detail/doctor-detail.component')
    },
    {
        path: "doctors/:doctorId/medical-consultations",
        loadComponent: () => import('@pages/medical-consultations/medical-consultations.component')
    },
    {
        path: "doctors/:doctorId/patients",
        loadComponent: () => import('@pages/patients/patients.component')
    },
    {
        path: "patients",
        loadComponent: () => import('@pages/patients/patients.component')
    },
    {
        path: "patients/:id",
        loadComponent: () => import('@pages/patients/patient-detail/patient-detail.component')
    },
    {
        path: "patients/:patientId/medical-consultations",
        loadComponent: () => import('@pages/medical-consultations/medical-consultations.component')
    },
    {
        path: "medical-consultations",
        loadComponent: () => import('@pages/medical-consultations/medical-consultations.component')
    },
    {
        path: "medical-consultations/:id",
        loadComponent: () => import('@pages/medical-consultations/medical-consultation-detail/medical-consultation-detail.component')
    }
];
