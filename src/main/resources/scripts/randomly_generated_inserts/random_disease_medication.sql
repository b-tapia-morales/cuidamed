SET search_path = "residence";

DROP PROCEDURE IF EXISTS insert_diseases();
DROP PROCEDURE IF EXISTS insert_medications();

CREATE OR REPLACE PROCEDURE insert_diseases()
    LANGUAGE plpgsql AS
$$
DECLARE
    names_disease TEXT ARRAY DEFAULT ARRAY [
        'Bupivacaína',
        'Artritis',
        'Alzheimer',
        'Neumonia',
        'Artrosis',
        'Demencia Senil',
        'Parkinson',
        'Ela',
        'Osteoporosis',
        'Ictus',
        'Diabetes',
        'Hipertensión',
        'Infarto',
        'Fibromialgia',
        'Anemia',
        'Estreñimiento',
        'Nauseas y Vómitos',
        'Bronquitis Aguda',
        'Resfriado Común',
        'Infección de Oído',
        'Influenza',
        'Sinusitis',
        'Infecciones de la Piel',
        'Dolor de Garganta',
        'Infección Urinaria',
        'Boletines DDR',
        'Diarrea',
        'Insuficiencia Renal Crónica',
        'Sida',
        'Epilepsia',
        'Hemofilia',
        'Fibrosis Quística',
        'Esclerosis Múltiple',
        'Hepatitis B',
        'Hepatitis C',
        'Hipotiroidismo',
        'Lupus',
        'Helicobacter Pylori',
        'Ántrax',
        'Asma',
        'Autismo',
        'Clamidia',
        'Culebrilla',
        'Déficit de Atención',
        'Ebola',
        'Escarlatina',
        'Estreptococo B',
        'Gonorrhea',
        'Herpes Genital',
        'Meningitis',
        'Paperas',
        'Poliomielitis',
        'Rabia',
        'Rotavirus',
        'Shigelosis',
        'Sífilis',
        'Silicosis',
        'Síndrome Alcohólico Fetal',
        'Síndrome Fatiga Crónica',
        'Síndrome de Tourette',
        'Taco',
        'Tosferina',
        'Tricomoniasis',
        'Tuberculosis',
        'Vaginosis Bacteriana',
        'Zika',
        'Arritmia',
        'Ampollas',
        'Infección Rectal',
        'Apendicitis',
        'Dismenorrea',
        'Díspepsia',
        'Distonia',
        'Esclerodermia',
        'Epoc',
        'Escleritis',
        'Fiebre de Lassa',
        'Galactorrea',
        'Gastroenteritis',
        'Hirsutismo',
        'Insomnio',
        'Intolerancia a la Histamina',
        'Ladillas',
        'Laringitis',
        'Lepra',
        'Lipotimia',
        'Microcefalia',
        'Migraña',
        'Melasma',
        'Osteonecrosis',
        'Oncocercosis',
        'Psoriasis',
        'Priapismo',
        'Pian',
        'Pancreatitis',
        'Reflujo Gastroesofágico',
        'Sarampión',
        'Sarcoidosis',
        'Sarna'
        ];
    n             INTEGER DEFAULT cardinality(names_disease);
    chronic       BOOLEAN ARRAY DEFAULT ARRAY [TRUE, FALSE];
BEGIN
    FOR i in 1..n
        LOOP
            INSERT INTO disease
            VALUES (names_disease[i], floor(random() * 14 + 1)::smallint, chronic[floor(random() * 2 + 1)::int]);
        END LOOP;
END;
$$;

CREATE OR REPLACE PROCEDURE insert_medications()
    LANGUAGE plpgsql AS
$$
DECLARE
    medication_names TEXT ARRAY DEFAULT ARRAY [
        'Paracetamol',
        'Ibuprofeno',
        'Galantamina',
        'Azitromicina',
        'Demerol',
        'Donepezilo',
        'Levodopa',
        'Alteplasa',
        'Riluzol',
        'Suplementos de Calcio y Vitamina D',
        'Tolbutamida',
        'Acebutolol',
        'Atenolol',
        'Nitroglicerina',
        'Duloxetina',
        'Pregabalina',
        'Suplementos Vitamínicos',
        'Lubiprostona',
        'Pepto Bismol',
        'Kaopectate',
        'Codeína',
        'Morfina',
        'Alopurinol',
        'Cloroquina',
        'Azatioprina',
        'Metotrexato',
        'Penicilamina',
        'Sulfasalazina',
        'Clorfenamina',
        'Dexametasona',
        'Epinefrina',
        'Hidrocortisona',
        'Prednisolona',
        'Carbon Activado',
        'Acetilcisteína',
        'Cloruro de Metiltioninio',
        'Atropina',
        'Deferoxamina',
        'Dimercaprol',
        'Dl Metionina',
        'Edetato de Calcio y Sodio',
        'Gluconato de Calcio',
        'Naloxona',
        'Nitrito de Sodio',
        'Tiosulfato de Sodio',
        'Ácido Valproico',
        'Carbamazepina',
        'Diazepam',
        'Fenitoina',
        'Fenobarbital',
        'Sulfato de Magnesio',
        'Albendazol',
        'Levamisol',
        'Mebendazol',
        'Niclosamida',
        'Pirantel',
        'Prazicuantel',
        'Ivermectina',
        'Aldinam',
        'Triciabendazol',
        'Amoxicilina',
        'Ampicilina',
        'Bencilpenicilina',
        'Cefazolina',
        'Cefixima',
        'Cloxacilina',
        'Fenoximetilpenicilina',
        'Ciprofloxacino',
        'Cloranfenicol',
        'Doxiciclina',
        'Eritromicina',
        'Espectinomicina',
        'Gentamicina',
        'Metronidazol',
        'Nitrofurantoína',
        'Sulfametoxazol',
        'Trimetoprima',
        'Clofazimina',
        'Dapsona',
        'Rifampicina',
        'Estreptomicina',
        'Etambutol',
        'Isoniazida',
        'Pirazinamida',
        'Anafranil',
        'Amikacina',
        'Capreomicina',
        'Cicloserina',
        'Etionamida',
        'Kanamicina',
        'Ofloxacino',
        'Clotrimazol',
        'Fluconazol',
        'Griseofulvina',
        'Nistatina',
        'Anfotericina',
        'Flucitosina',
        'Ioduro de Potasio',
        'Aciclovir'];
    n                INTEGER DEFAULT cardinality(medication_names);
BEGIN
    FOR i in 1..n
        LOOP
            INSERT INTO medication
            VALUES (medication_names[i], floor(random() * 2 + 1)::smallint, floor(random() * 4 + 1)::smallint,
                    floor(random() * 2 + 1)::smallint);
        END LOOP;
END;
$$;