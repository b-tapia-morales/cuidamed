SET search_path = "residence";

DROP PROCEDURE IF EXISTS insert_random_medication();
DROP PROCEDURE IF EXISTS insert_random_disease();

CREATE OR REPLACE PROCEDURE insert_random_medication()
    LANGUAGE plpgsql AS
$$
DECLARE
    names_medication TEXT ARRAY DEFAULT ARRAY ['paracetamol',
        'Ibuprofeno',
        'Galtatamina',
        'Azitromicina',
        'Demerol',
        'Donopezilo',
        'Levadopa',
        'Alteplasa',
        'Riluzol',
        'Suplementos de calcio y vitamina D',
        'Tolbutamida',
        'Acebutolol',
        'Atenolol',
        'Nitroglicerina',
        'Duloxetina',
        'Pregabalina',
        'Suplementos Vitaminicos',
        'Lubiprotona',
        'Pepto-bismol',
        'Kaopectate',
        'Codeina',
        'Morfina',
        'Alopurinol',
        'Cloroquina',
        'Azatioprima',
        'Metotrexato',
        'Penicilamina',
        'Sulfasolazina',
        'Clorfenamina',
        'Dexametasona',
        'Epinefrina',
        'Hidrocortisona',
        'Prednisolona',
        'Carbon Activado',
        'acetilcisteina',
        'atropina',
        'Cloruro de metiltioninio',
        'Atropina',
        'Deforaxamina',
        'Dimercaprol',
        'DL-metionina',
        'Edetato de calcio y sodio',
        'Gluconato de Calcio',
        'Naloxona',
        'Nitrito de sodio',
        'Tiosulfato de sodio',
        'Acido valproico',
        'Carbamazepina',
        'Diazepam',
        'Fenitoina',
        'Fenobarbital',
        'Sulfato de magnesio',
        'Albendazol',
        'Levamisol',
        'Mebendazol',
        'Niclosamida',
        'Pirantel',
        'Prazicuantel',
        'Ivermectina',
        'Aldiman',
        'Triclavendazol',
        'Amoxicilina',
        'Ampicilina',
        'Bencilpenicilina',
        'Cefazolina',
        'Cefixima',
        'Cloxacilina',
        'Fenoximelpenicilina',
        'Ciprofloxacino',
        'Cloranfenicol',
        'Doxiciclina',
        'Eritomicina',
        'Espectinomicina',
        'Gentamicina',
        'Metronidazol',
        'Nitrofurantoína',
        'Sulfametoxazol',
        'Trimetoprim',
        'Clofazimina',
        'Dapsona',
        'Rifampicina',
        'Estreptomocina',
        'Etambutol',
        'Isoniazida',
        'Pirazinamida',
        'Anaframil',
        'Amikacina',
        'Capreonicina',
        'Cicloserina',
        'Etionamida',
        'Kanamicina',
        'Ofloxacino',
        'CLotrimazol',
        'Fluconazol',
        'Grisofulvina',
        'Nistatina',
        'Amforeticina',
        'Flucitosina',
        'Ioduro de Potasio',
        'Aciclovir'];
    n                INTEGER DEFAULT cardinality(names_medication);
BEGIN
    FOR i in 1..n
        LOOP
            INSERT INTO medication
            VALUES (names_medication[i], floor(random() * 2 + 1)::int, floor(random() * 4 + 1)::int,
                    floor(random() * 2 + 1)::int);
        end loop;
END;
$$;



CREATE OR REPLACE PROCEDURE insert_random_disease()
    language plpgsql as
$$
DECLARE
    names_disease TEXT ARRAY DEFAULT ARRAY ['bupivacaína',
        'Artritis',
        'Alzheimer',
        'Neumonia',
        'Artrosis',
        'Demencia senil',
        'Parkinson',
        'ELA',
        'Osteoporosis',
        'Ictus',
        'Diabetes',
        'Hipertensión',
        'Infarto',
        'Fibromialgia',
        'Anemia',
        'Estreñimiento',
        'Nauseas y vomitos',
        'Bronquitis aguda',
        'Resfriado comun',
        'infeccion de oido',
        'influenza',
        'Sinusitis',
        'Infecciones de la piel',
        'Dolor de Garganta',
        'Infeccion Urinaria',
        'Boletines DDR',
        'Diarrea',
        'insuficencia renal Cronica',
        'SIDA',
        'Epilepsia',
        'Hermofilia',
        'Fibrosis quistica',
        'Esclerosis Multiple',
        'Hepatitis B',
        'Hepatitis C',
        'Hipotiroidismo',
        'Lupus',
        'Helicibacter pylori',
        'Antrax',
        'Asma',
        'Autismo',
        'Clamidia',
        'Culebrilla',
        'Deficit de atencion',
        'Ebola',
        'Escarlatina',
        'Estreptococo B',
        'Gonorrhea',
        'Hemofilia',
        'Herpes genital',
        'Meningitis',
        'Paperas',
        'Poliomielitis',
        'Rabia',
        'Rotavirus',
        'Shigella',
        'Sifilis',
        'Silicosis',
        'Sindrome alcohólico fetal',
        'Sindrome fatiga cronica',
        'Sindrome de tourette',
        'TACO',
        'Tosgeria',
        'Tricomosiasis',
        'Tuberculosis',
        'Vaginosis bacteriana',
        'Zika',
        'Arritmia',
        'Ampollas',
        'Infeccion Rectal',
        'Apendicitis',
        'Dismenorra',
        'Díspepsia',
        'Distonia',
        'Esclerodermia',
        'EPOC',
        'Escleritis',
        'Fiebre de lassa',
        'Galactorrea',
        'Gastroenteritis',
        'Hirsutismo',
        'Insomnio',
        'Intolerancia a la histamina',
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
        'Reflujo gastroesofagico',
        'Sarampion',
        'Sarcoidosis',
        'Sarna'];
    n             INTEGER DEFAULT cardinality(names_disease);
    condicional   BOOLEAN ARRAY DEFAULT ARRAY ['t','f'];
Begin
    FOR i in 1..n
        LOOP
            INSERT INTO disease
            VALUES (names_disease[i], floor(random() * 14 + 1)::int, condicional[floor(random() * 2 + 1)::int]);
        end loop;
end;

$$;

CALL insert_random_medication();
SELECT *
from medication;

CALL insert_random_disease();
SELECT *
from disease;




