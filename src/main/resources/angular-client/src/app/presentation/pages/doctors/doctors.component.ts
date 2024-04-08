import {Component, OnDestroy, OnInit} from '@angular/core';
import {DoctorRepository} from "@domain/interfaces/doctor-repository";
import {Doctor} from "@domain/models/doctor";
import {DoctorService} from "@app/infrastructure/services/doctor.service";
import {DatePipe} from "@angular/common";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {Subscription} from "rxjs";
import {Page} from "@domain/models/page";
import {PageRequest} from "@domain/models/page-request";
import {FormsModule} from "@angular/forms";

@Component({
    selector: 'app-doctor',
    standalone: true,
    imports: [
        DatePipe,
        RouterLink,
        FormsModule
    ],
    templateUrl: './doctors.component.html',
    styleUrl: './doctors.component.scss',
    providers: [{provide: DoctorRepository, useClass: DoctorService}],
})
export default class DoctorsComponent implements OnInit, OnDestroy {
    doctorsSubscription?: Subscription;

    doctors: Page<Doctor> = {} as Page<Doctor>;
    currentDoctor: Doctor = {} as Doctor;
    currentIndex = -1;
    title = '';

    pageSecuence: number[] = [];

    protected pageRequest: PageRequest;

    constructor(
        private router: Router,
        private activatedRoute: ActivatedRoute,
        private doctorService: DoctorRepository) {
        this.pageRequest = new PageRequest();
        this.pageRequest.page = 1;
        this.pageRequest.size = 10;
    }

    ngOnInit(): void {
        console.debug("DoctorsComponent.ngOnInit");
        this.loadDoctors()
    }

    ngOnDestroy(): void {
        console.debug("DoctorsComponent.ngOnDestroy");

        this.doctorsSubscription?.unsubscribe();
    }

    getActivePage(page: number): string {
        if (page === this.pageRequest.page) {
            return 'active';
        } else {
            return '';
        }
    }

    loadNextPage(): void {
        if (this.pageRequest.page >= this.doctors.totalPages) return;

        this.pageRequest.page++;
        this.loadDoctors();
    }

    loadPreviousPage(): void {
        if (this.pageRequest.page <= 1) return;
        this.pageRequest.page--;
        this.loadDoctors();
    }

    loadPageParameter(value: number): void {
        this.pageRequest.page = value;
        this.loadDoctors();
        const queryParams = { page: value };
        this.router.navigate(
            [],
            {
                relativeTo: this.activatedRoute,
                queryParams: queryParams,
                queryParamsHandling: 'merge' // mantiene otros query params
            }
        );
    }

    loadDoctors(): void {
        console.debug("DoctorsComponent.getDoctors");
        this.doctorsSubscription = this.doctorService.getAll(this.pageRequest).subscribe({
            next: (data) => {
                this.doctors = data;
                this.pageSecuence = Array.from(Array(data.totalPages).keys());

                this.pageRequest.page = this.doctors.currentPage;

                if (this.doctors.totalPages < this.pageRequest.page) {
                    this.pageRequest.page = this.doctors.totalPages;
                    this.loadDoctors();
                }

                console.debug("loaded doctors: ", data);
            },
            error: (e) => console.error(e)
        });
    }

    refreshList(): void {
        this.loadDoctors();
        this.currentDoctor = {} as Doctor;
        this.currentIndex = -1;
    }

    setActiveDoctor(doctor: Doctor, index: number): void {
        this.currentDoctor = doctor;
        this.currentIndex = index;
    }
}
