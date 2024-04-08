DROP DATABASE IF EXISTS demo_consultas;

CREATE DATABASE demo_consultas;

USE demo_consultas;

CREATE TABLE users
(
    type       VARCHAR(31)  not null,
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    address    VARCHAR(255) NULL,
    birth_date DATETIME(6)  NULL,
    email      VARCHAR(255) NULL,
    first_name VARCHAR(255) NULL,
    image      VARCHAR(255) NULL,
    last_name  VARCHAR(255) NULL,
    specialty  VARCHAR(255) NULL
);

CREATE TABLE medical_consultations
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    date       DATETIME(6)  NULL,
    diagnostic VARCHAR(255) NULL,
    treatment  VARCHAR(255) NULL,
    doctor_id  BIGINT       NULL,
    patient_id BIGINT       NULL,
    CONSTRAINT FKPATIENT
        FOREIGN KEY (patient_id) REFERENCES users (id),
    constraint FKDOCTOR
        FOREIGN KEY (doctor_id) REFERENCES users (id)
);

DELIMITER $$

USE demo_consultas $$

CREATE PROCEDURE GenerateRandomUsers(IN numUsers INT)
BEGIN
    DECLARE genre BIT DEFAULT 0;
    DECLARE i INT DEFAULT 0;
    DECLARE userType VARCHAR(31);
    DECLARE userSpecialty VARCHAR(255);
    DECLARE userName VARCHAR(255);
    DECLARE userLastName VARCHAR(255);
    -- Arrays de nombres

    DROP TEMPORARY TABLE IF EXISTS temp_array_male_name;
    CREATE TEMPORARY TABLE temp_array_male_name (valor VARCHAR(255));
    INSERT INTO temp_array_male_name VALUES ('Mateo'), ('Santiago'), ('Matías'), ('Sebastián'), ('Liam'), ('Thiago'), ('Lucas'), ('Benjamín'), ('Nicolás'), ('Emiliano'), ('Samuel'), ('Gael'), ('Joaquín'), ('Leonardo'), ('Felipe'), ('Martín'), ('Alejandro'), ('Tomás'), ('Daniel'), ('Bruno');

    DROP TEMPORARY TABLE IF EXISTS temp_array_female_name;
    CREATE TEMPORARY TABLE temp_array_female_name (valor VARCHAR(255));
    INSERT INTO temp_array_female_name VALUES ('Sofía'), ('Isabella'), ('Emma'), ('Valentina'), ('Victoria'), ('Emilia'), ('Camila'), ('Martina'), ('Lucía'), ('Catalina'), ('Elena'), ('Olivia'), ('Julieta'), ('Renata'), ('Antonella'), ('Mía'), ('Sara'), ('Amelia'), ('Valeria'), ('Regina');

    DROP TEMPORARY TABLE IF EXISTS temp_array_last_names;
    CREATE TEMPORARY TABLE temp_array_last_names (valor VARCHAR(255));
    INSERT INTO temp_array_last_names VALUES ('Hernández García'), ('Martínez López'), ('González Pérez'), ('Rodríguez Sánchez'), ('Ramírez Torres'), ('Flores Romero'), ('Soto Méndez'), ('Chávez Jiménez'), ('Ramos Herrera'), ('Ortega Medina'), ('Silva Castro'), ('Vargas Morales'), ('Reyes Morales'), ('Morales Ortiz'), ('Cruz Rubio'), ('Álvarez Romero'), ('Gómez Méndez'), ('Torres González'), ('Mendoza Ruiz'), ('Castillo Herrera'), ('Castro García'), ('Romero Morales'), ('Vázquez Soto'), ('Guerrero Méndez'), ('Pérez Ortiz'), ('Fuentes Ríos'), ('Ríos Sánchez'), ('Méndez Torres'), ('Giménez López'), ('Sánchez Flores');

    -- Array de especialidades
    DROP TEMPORARY TABLE IF EXISTS temp_array_specialties;
    CREATE TEMPORARY TABLE temp_array_specialties (valor VARCHAR(255));
    INSERT INTO temp_array_specialties VALUES ('Cirugía'), ('General'), ('Alergología'), ('Cardiología'), ('Dermatología'), ('Ginecología'), ('Pediatría'), ('Oftalmología'), ('Neurología'), ('Psiquiatría'), ('Obstetricia');


    WHILE i < numUsers DO

            SET genre = IF(RAND() < 0.5, 0, 1);
            -- Genera 'patient' o 'doctor' de manera aleatoria
            SET userType = IF(RAND() < 0.5, 'patient', 'doctor');
            -- Selecciona una especialidad aleatoria del array
            SET userSpecialty = (SELECT valor FROM temp_array_specialties ORDER BY RAND() LIMIT 1);
            -- Selecciona un nombre aleatorio del array correspondiente
            SET userName = IF(genre = 0, (SELECT valor FROM temp_array_female_name ORDER BY RAND() LIMIT 1),
                              (SELECT valor FROM temp_array_male_name ORDER BY RAND() LIMIT 1));

            SET userLastName = (SELECT valor FROM temp_array_last_names ORDER BY RAND() LIMIT 1);

            INSERT INTO users (type, address, birth_date, email, first_name, image, last_name, specialty)
            VALUES (userType,
                    CONCAT('Dirección ', FLOOR(1 + RAND() * 100)), -- Genera una dirección aleatoria
                    NOW() - INTERVAL FLOOR(RAND() * 10000) DAY, -- Genera una fecha de nacimiento aleatoria
                    CONCAT('user', FLOOR(1 + RAND() * 10000), '@example.com'), -- Genera un email aleatorio
                    userName,
                    CONCAT('http://image.example.com/', FLOOR(1 + RAND() * 100)), -- Genera una URL de imagen aleatoria
                    userLastName,
                    userSpecialty
                   );
            SET i = i + 1;
        END WHILE;
END $$


CREATE PROCEDURE GenerateRandonMedicalConsultations(IN numItems INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE randDate DATETIME(6);
    DECLARE randDiagnostic VARCHAR(255);
    DECLARE randDescription VARCHAR(1000);
    DECLARE randTreatment VARCHAR(255);
    DECLARE randDoctorId BIGINT;
    DECLARE randPatientId BIGINT;



    WHILE i < numItems
        DO
            DROP TEMPORARY TABLE IF EXISTS temp_array_diagnostics;
            CREATE TEMPORARY TABLE temp_array_diagnostics
            (
                id          INT AUTO_INCREMENT,
                diagnostic  VARCHAR(255),
                description VARCHAR(1000),
                treatment   VARCHAR(1000),
                PRIMARY KEY (id)
            );

            INSERT INTO temp_array_diagnostics (diagnostic, description, treatment)
            VALUES ('Hipertensión arterial', 'Presión arterial crónicamente elevada',
                    'Medicamentos antihipertensivos, dieta baja en sal'),
                   ('Diabetes mellitus tipo 2', 'Alteración metabólica caracterizada por alta glucosa en sangre',
                    'Metformina, cambios en la dieta, ejercicio regular'),
                   ('Hipotiroidismo', 'Deficiencia de hormonas tiroideas', 'Levotiroxina sódica'),
                   ('Gastroenteritis aguda', 'Inflamación del estómago e intestinos que causa vómitos y diarrea',
                    'Reposo, hidratación oral, dieta blanda'),
                   ('Asma bronquial', 'Enfermedad inflamatoria crónica de las vías respiratorias',
                    'Inhaladores de corticosteroides, broncodilatadores'),
                   ('Dermatitis atópica', 'Condición crónica de la piel que causa picazón y enrojecimiento',
                    'Cremas hidratantes, corticosteroides tópicos'),
                   ('Infección del tracto urinario', 'Infección que afecta al sistema urinario',
                    'Antibióticos, aumento de la ingesta de líquidos'),
                   ('Anemia por deficiencia de hierro', 'Bajos niveles de hierro que causan fatiga y debilidad',
                    'Suplementos de hierro, dieta rica en hierro'),
                   ('Otitis media', 'Infección del oído medio', 'Analgésicos, antibióticos si es bacteriana'),
                   ('Conjuntivitis alérgica', 'Inflamación del ojo causada por alergias',
                    'Antihistamínicos, lágrimas artificiales'),
                   ('Cefalea tensional', 'Dolor de cabeza frecuente y recurrente',
                    'Analgésicos, técnicas de relajación'),
                   ('Reflujo gastroesofágico', 'El ácido del estómago regresa al esófago',
                    'Antiácidos, modificaciones dietéticas'),
                   ('Sinusitis aguda', 'Inflamación de los senos paranasales',
                    'Analgésicos, descongestionantes, antibióticos si es necesario'),
                   ('Esguince de tobillo', 'Lesión en los ligamentos del tobillo',
                    'Reposo, hielo, compresión, elevación'),
                   ('Depresión mayor', 'Trastorno del estado de ánimo con persistente tristeza',
                    'Antidepresivos, terapia psicológica'),
                   ('Ansiedad generalizada', 'Trastorno de ansiedad con preocupación excesiva',
                    'Ansiolíticos, terapia cognitivo-conductual'),
                   ('Insuficiencia cardíaca', 'Incapacidad del corazón para bombear sangre eficientemente',
                    'Diuréticos, inhibidores de la ECA, cambios en el estilo de vida'),
                   ('Neumonía', 'Infección pulmonar', 'Antibióticos, reposo, hidratación'),
                   ('Artritis reumatoide', 'Enfermedad autoinmune que afecta las articulaciones',
                    'Antiinflamatorios no esteroideos, fármacos modificadores de la enfermedad'),
                   ('Epilepsia', 'Trastorno neurológico con convulsiones recurrentes',
                    'Anticonvulsivos, seguimiento neurológico'),
                   ('Psoriasis', 'Enfermedad inflamatoria crónica de la piel', 'Corticosteroides tópicos, fototerapia'),
                   ('Infección por VIH', 'Virus que ataca el sistema inmunitario',
                    'Antirretrovirales, seguimiento médico regular'),
                   ('Tuberculosis', 'Enfermedad infecciosa causada por Mycobacterium tuberculosis',
                    'Tratamiento antituberculoso prolongado'),
                   ('Infección por Helicobacter pylori', 'Bacteria que causa úlceras estomacales',
                    'Antibióticos, inhibidores de la bomba de protones'),
                   ('Urticaria', 'Erupción cutánea con picazón', 'Antihistamínicos, evitar el alérgeno'),
                   ('Colelitiasis', 'Piedras en la vesícula biliar', 'Analgésicos, cirugía si es necesario'),
                   ('Faringitis estreptocócica', 'Infección de la garganta por bacterias estreptococos',
                    'Antibióticos, analgésicos'),
                   ('Lumbalgia', 'Dolor en la parte baja de la espalda', 'Analgésicos, fisioterapia'),
                   ('Migraña', 'Dolor de cabeza intenso y pulsátil',
                    'Medicamentos específicos para la migraña, prevención de desencadenantes'),
                   ('Bronquitis crónica', 'Inflamación de los bronquios a largo plazo',
                    'Broncodilatadores, corticosteroides, oxigenoterapia si es necesario'),
                   ('Acné vulgar', 'Afección de la piel con formación de granos o espinillas',
                    'Limpiadores faciales, medicamentos tópicos, antibióticos si es necesario');

            -- Genera una fecha y hora aleatoria dentro de los últimos 30 días
            SET randDate = DATE_ADD(NOW(), INTERVAL -FLOOR(RAND() * 30) DAY);


            SELECT diagnostic, description, treatment
            INTO randDiagnostic, randDescription, randTreatment
            FROM temp_array_diagnostics
            ORDER BY RAND()
            LIMIT 1;

            SET randDiagnostic = CONCAT(randDiagnostic, ': ', randDescription);

            -- Selecciona un doctor y un paciente aleatorios
            SET randDoctorId = (SELECT id FROM users WHERE type = 'doctor' ORDER BY RAND() LIMIT 1);
            SET randPatientId = (SELECT id FROM users WHERE type = 'patient' ORDER BY RAND() LIMIT 1);

            -- Inserta los datos aleatorios en la tabla
            INSERT INTO medical_consultations (date, diagnostic, treatment, doctor_id, patient_id)
            VALUES (randDate, randDiagnostic, randTreatment, randDoctorId, randPatientId);

            SET i = i + 1;
        END WHILE;
END $$

DELIMITER ;

CALL GenerateRandomUsers(100);
CALL GenerateRandonMedicalConsultations(500);