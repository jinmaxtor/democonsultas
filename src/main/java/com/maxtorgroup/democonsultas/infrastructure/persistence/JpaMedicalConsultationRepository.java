package com.maxtorgroup.democonsultas.infrastructure.persistence;

import com.maxtorgroup.democonsultas.domain.contract.MedicalConsultationRepository;
import com.maxtorgroup.democonsultas.infrastructure.entity.MedicalConsultation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaMedicalConsultationRepository implements MedicalConsultationRepository {

    private final EntityManager entityManager;
    private final SimpleJpaRepository<MedicalConsultation, Long> dataRepository;

    public JpaMedicalConsultationRepository(EntityManager entityManager, SimpleJpaRepository<MedicalConsultation, Long> dataRepository) {
        this.entityManager = entityManager;
        this.dataRepository = dataRepository;
    }

    @Override
    public List<MedicalConsultation> getMedicalConsultations() {
        return dataRepository.findAll();
    }

    @Override
    public List<MedicalConsultation> getMedicalConsultationsByPatientId(Long patientId) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MedicalConsultation> criteria = criteriaBuilder.createQuery(MedicalConsultation.class);

        Root<MedicalConsultation> mc = criteria.from(MedicalConsultation.class);

        criteria.select(mc)
                .where(criteriaBuilder.equal(mc.get("patient").get("id"), patientId));

        TypedQuery<MedicalConsultation> query = entityManager.createQuery(criteria);

        return query.getResultList();
    }

    @Override
    public List<MedicalConsultation> getMedicalConsultationsByDoctorId(Long doctorId) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MedicalConsultation> criteria = criteriaBuilder.createQuery(MedicalConsultation.class);

        Root<MedicalConsultation> mc = criteria.from(MedicalConsultation.class);


        criteria.select(mc)
                .where(criteriaBuilder.equal(mc.get("doctor").get("id"), doctorId));

        TypedQuery<MedicalConsultation> query = entityManager.createQuery(criteria);

        return query.getResultList();
    }

    @Override
    public MedicalConsultation saveMedicalConsultation(MedicalConsultation medicalConsultation) {
        return dataRepository.save(medicalConsultation);
    }

    @Override
    public Boolean deleteMedicalConsultationById(Long id) {
        dataRepository.deleteById(id);
        return true;
    }
}
