CREATE TABLE tanggal (saat_ini DATE);
-- INSERT INTO tanggal VALUES (TO_DATE('01-02-2023', 'DD-MM-YYYY'));
UPDATE tanggal SET saat_ini = TO_DATE('01-03-2023', 'DD-MM-YYYY');

CREATE TABLE customer ( 
username VARCHAR2(20), katasandi VARCHAR2(50), nik CHAR(5), nama VARCHAR2(50), 
gender CHAR(1) CHECK (gender IN ('L', 'P')), alamat VARCHAR2(50), telepon VARCHAR2(20),
CONSTRAINT PK_customer PRIMARY KEY (username) );

CREATE TABLE properti (
id_properti CHAR(6), tipe_properti VARCHAR2(20), nama_properti VARCHAR2(20), 
harga_jual NUMBER(13,0), harga_sewa_perhari NUMBER(13,0),
status VARCHAR2(15) CHECK (status IN ('TERSEDIA', 'TERJUAL', 'TERSEWA')), 
CONSTRAINT PK_properti PRIMARY KEY (id_properti) );

CREATE TABLE rekening_bank (
no_rekening VARCHAR2(30), nama_bank VARCHAR2(10), 
CONSTRAINT PK_rekening_bank PRIMARY KEY (no_rekening) );

CREATE TABLE transaksi_beli (
id_transaksi VARCHAR2(10), username VARCHAR2(20), 
id_properti CHAR(6), tipe_properti VARCHAR2(20), nama_properti VARCHAR2(20),
harga NUMBER(13,0), no_rekening VARCHAR2(30), tanggal_beli DATE,
CONSTRAINT PK_transaksi_beli PRIMARY KEY (id_transaksi),
CONSTRAINT FK_transaksi_beli FOREIGN KEY (username) REFERENCES customer (username),
FOREIGN KEY (id_properti) REFERENCES properti (id_properti),
FOREIGN KEY (no_rekening) REFERENCES rekening_bank (no_rekening));

CREATE TABLE transaksi_sewa (
id_transaksi VARCHAR2(10), username VARCHAR2(20), 
id_properti CHAR(6), tipe_properti VARCHAR2(20), nama_properti VARCHAR2(20),
mulai_sewa DATE, selesai_sewa DATE, total_hari NUMBER(6,0),
total_harga NUMBER(10,0), no_rekening VARCHAR2(30),
status VARCHAR2(15) CHECK (status IN ('BERLANGSUNG', 'BERHENTI')),
terlambat NUMBER(6,0), harga_denda_perhari NUMBER(13,0), total_denda NUMBER(13,0),
CONSTRAINT PK_transaksi_sewa PRIMARY KEY (id_transaksi),
CONSTRAINT FK_transaksi_sewa FOREIGN KEY (username) REFERENCES customer (username),
FOREIGN KEY (id_properti) REFERENCES properti (id_properti),
FOREIGN KEY (no_rekening) REFERENCES rekening_bank (no_rekening) );

/**
DROP TABLE transaksi_sewa;
DROP TABLE transaksi_beli;
DROP TABLE rekening_bank;
DROP TABLE properti;
DROP TABLE customer;
DROP TABLE tanggal;
**/

INSERT ALL
INTO customer VALUES ('fayy', 'rahasia', '12345', 'Fayyadh', 'L', 'Darussalam', '0879 1234 1234')
INTO customer VALUES ('lina2002', 'apaya', '54321', 'Linawati', 'P', 'Condong Catur', '0879 1234 4321')
INTO customer VALUES ('rafi', 'properti123', '11111', 'Rafi', 'L', 'Bantul', '0879 6666 1234')
SELECT * FROM dual;
SELECT username FROM customer WHERE username = 'fayy' AND katasandi = 'rahasia';

INSERT ALL
INTO rekening_bank VALUES ('1122334455', 'BRI')
INTO rekening_bank VALUES ('5544332211', 'BNI')
INTO rekening_bank VALUES ('1113335557', 'BSI')
INTO rekening_bank VALUES ('9876543210', 'MANDIRI')
INTO rekening_bank VALUES ('7659234728', 'BTN')
SELECT * FROM dual;

INSERT ALL
INTO properti VALUES ('P00001', 'Tipe Rumah', 'Rumah 1', 500000000, 1000000, 'TERSEDIA')
INTO properti VALUES ('P00002', 'Tipe Rumah', 'Rumah 2', 800000000, 1600000, 'TERSEDIA')
INTO properti VALUES ('P00003', 'Tipe Rumah', 'Rumah 3', 900000000, 1800000, 'TERSEDIA')
INTO properti VALUES ('P00004', 'Tipe Rumah', 'Rumah 4', 1100000000, 2200000, 'TERSEDIA')
INTO properti VALUES ('P00005', 'Tipe Rumah', 'Rumah 5', 400000000, 800000, 'TERSEDIA')
INTO properti VALUES ('P00006', 'Tipe Rumah', 'Rumah 6', 1500000000, 3000000, 'TERSEDIA')
INTO properti VALUES ('P00007', 'Tipe Komersial', 'Ruko1', 500000000, 1000000, 'TERSEDIA')
INTO properti VALUES ('P00008', 'Tipe Komersial', 'Ruko2', 900000000, 1800000, 'TERSEDIA')
INTO properti VALUES ('P00009', 'Tipe Komersial', 'Kantor1', 1000000000, 2000000, 'TERSEDIA')
INTO properti VALUES ('P00010', 'Tipe Komersial', 'Kantor2', 1300000000, 2600000, 'TERSEDIA')
INTO properti VALUES ('P00011', 'Tipe Komersial', 'Restoran', 1500000000, 3000000, 'TERSEDIA')
INTO properti VALUES ('P00012', 'Tipe Komersial', 'Restoran2', 1900000000, 3800000, 'TERSEDIA')
INTO properti VALUES ('P00013', 'Tipe Komersial', 'Hotel1', 5500000000, 11000000, 'TERSEDIA')
INTO properti VALUES ('P00014', 'Tipe Komersial', 'Hotel2', 10000000000, 20000000, 'TERSEDIA')
INTO properti VALUES ('P00015', 'Tipe Industrial', 'Mesin Bor', 500000000, 1000000, 'TERSEDIA')
INTO properti VALUES ('P00016', 'Tipe Industrial', 'Mesin Potong', 300000000, 600000, 'TERSEDIA')
INTO properti VALUES ('P00017', 'Tipe Industrial', 'Gudang', 100000000, 200000, 'TERSEDIA')
INTO properti VALUES ('P00018', 'Tipe Industrial', 'Mesin Cetak', 350000000, 700000, 'TERSEDIA')
INTO properti VALUES ('P00019', 'Tipe Apartemen', 'Ruang 1', 100000000, 200000, 'TERSEDIA')
INTO properti VALUES ('P00020', 'Tipe Apartemen', 'Ruang 2', 200000000, 400000, 'TERSEDIA')
INTO properti VALUES ('P00021', 'Tipe Apartemen', 'Ruang 3', 300000000, 600000, 'TERSEDIA')
INTO properti VALUES ('P00022', 'Tipe Apartemen', 'Ruang 4', 400000000, 800000, 'TERSEDIA')
INTO properti VALUES ('P00023', 'Tipe Apartemen', 'Ruang 5', 500000000, 1000000, 'TERSEDIA')
INTO properti VALUES ('P00024', 'Tipe Apartemen', 'Ruang 6', 600000000, 1200000, 'TERSEDIA')
INTO properti VALUES ('P00025', 'Tipe Apartemen', 'Ruang 7', 700000000, 1400000, 'TERSEDIA')
INTO properti VALUES ('P00026', 'Tipe Apartemen', 'Ruang 8', 800000000, 1600000, 'TERSEDIA')
INTO properti VALUES ('P00027', 'Tipe Apartemen', 'Ruang 9', 900000000, 1800000, 'TERSEDIA')
INTO properti VALUES ('P00028', 'Tipe Apartemen', 'Ruang 10', 1000000000, 2000000, 'TERSEDIA')
INTO properti VALUES ('P00029', 'Tipe Tanah', 'Tanah 1', 350000000, 0, 'TERSEDIA')
INTO properti VALUES ('P00030', 'Tipe Tanah', 'Tanah 2', 400000000, 0, 'TERSEDIA')
INTO properti VALUES ('P00031', 'Tipe Tanah', 'Tanah 3', 100000000, 0, 'TERSEDIA')
INTO properti VALUES ('P00032', 'Tipe Tanah', 'Tanah 4', 50000000, 0, 'TERSEDIA')
INTO properti VALUES ('P00033', 'Tipe Tanah', 'Tanah 5', 200000000, 0, 'TERSEDIA')
INTO properti VALUES ('P00034', 'Tipe Rumah', 'Rumah 7', 650000000, 1300000, 'TERSEDIA')
INTO properti VALUES ('P00035', 'Tipe Rumah', 'Rumah 8', 400000000, 800000, 'TERSEDIA')
INTO properti VALUES ('P00036', 'Tipe Rumah', 'Rumah 9', 1500000000, 3000000, 'TERSEDIA')
INTO properti VALUES ('P00037', 'Tipe Rumah', 'Rumah 10', 900000000, 1800000, 'TERSEDIA')
INTO properti VALUES ('P00038', 'Tipe Rumah', 'Rumah 11', 650000000, 1300000, 'TERSEDIA')
INTO properti VALUES ('P00039', 'Tipe Rumah', 'Rumah 12', 500000000, 1000000, 'TERSEDIA')
INTO properti VALUES ('P00040', 'Tipe Rumah', 'Rumah 13', 650000000, 1300000, 'TERSEDIA')
SELECT * FROM dual;






CREATE SEQUENCE id_transaksi_sewa
START WITH 1 -- Nilai awal sequence
INCREMENT BY 1 -- Increment setiap kali diambil
NOCACHE; -- Tidak menyimpan sequence di cache untuk performa

CREATE SEQUENCE id_transaksi_beli
START WITH 1 -- Nilai awal sequence
INCREMENT BY 1 -- Increment setiap kali diambil
NOCACHE; -- Tidak menyimpan sequence di cache untuk performa

/**
id_transaksi diisi ''
id_properti diisi ''
total_hari diisi 0
total_harga diisi 0
status diisi ''
terlambat diisi 0
harga_denda_perhari diisi 0
total_denda diisi 0
**/
CREATE OR REPLACE TRIGGER sewa_properti
BEFORE INSERT ON transaksi_sewa
FOR EACH ROW
DECLARE
current_date tanggal.saat_ini%TYPE;
harga_sewa properti.harga_sewa_perhari%TYPE;
kode_properti properti.id_properti%TYPE;
status properti.status%TYPE;
BEGIN
SELECT saat_ini INTO current_date FROM tanggal;
SELECT harga_sewa_perhari INTO harga_sewa FROM properti WHERE tipe_properti = :new.tipe_properti AND nama_properti = :new.nama_properti;
SELECT id_properti INTO kode_properti FROM properti WHERE tipe_properti = :new.tipe_properti AND nama_properti = :new.nama_properti;
SELECT status INTO status FROM properti WHERE tipe_properti = :new.tipe_properti AND nama_properti = :new.nama_properti;
IF :new.mulai_sewa > current_date AND status = 'TERSEDIA' AND :new.tipe_properti != 'Tipe Tanah' THEN
:new.id_transaksi := 'TS' || LPAD(id_transaksi_sewa.NEXTVAL, 5, '0');
:new.id_properti := kode_properti;
:new.total_hari := :new.selesai_sewa - :new.mulai_sewa;
:new.total_harga := :new.total_hari * harga_sewa;
:new.harga_denda_perhari := harga_sewa * 3;
:new.status := 'BERLANGSUNG';
ELSE
raise_application_error(-20002, 
'Tanggal yang Anda masukkan salah. 
Seharusnya tanggal mulai sewa > tanggal saat ini. 
Atau properti sedang disewa atau terjual.
Atau Anda tidak bisa menyewa tanah.');
END IF;
END;


/**
id_transaksi diisi ''
id_properti diisi ''
harga diisi 0
tanggal beli diisi ''
**/
CREATE OR REPLACE TRIGGER beli_properti
BEFORE INSERT ON transaksi_beli
FOR EACH ROW
DECLARE
current_date tanggal.saat_ini%TYPE;
harga_jual properti.harga_jual%TYPE;
kode_properti properti.id_properti%TYPE;
status properti.status%TYPE;
BEGIN
SELECT saat_ini INTO current_date FROM tanggal;
SELECT id_properti INTO kode_properti FROM properti WHERE tipe_properti = :new.tipe_properti AND nama_properti = :new.nama_properti;
SELECT harga_jual INTO harga_jual FROM properti WHERE tipe_properti = :new.tipe_properti AND nama_properti = :new.nama_properti;
SELECT status INTO status FROM properti WHERE tipe_properti = :new.tipe_properti AND nama_properti = :new.nama_properti;
IF status = 'TERSEDIA' THEN
:new.id_transaksi := 'TB' || LPAD(id_transaksi_beli.NEXTVAL, 5, '0');
:new.id_properti := kode_properti;
:new.harga := harga_jual;
:new.tanggal_beli := current_date;
ELSE
raise_application_error(-20003, 
'Properti sudah terjual.');
END IF;
END;


CREATE OR REPLACE TRIGGER ubah_status_sewa
AFTER INSERT ON transaksi_sewa
FOR EACH ROW
BEGIN
UPDATE properti SET status = 'TERSEWA' WHERE tipe_properti = :new.tipe_properti AND nama_properti = :new.nama_properti;
END;

CREATE OR REPLACE TRIGGER ubah_status_beli
AFTER INSERT ON transaksi_beli
FOR EACH ROW
BEGIN
UPDATE properti SET status = 'TERJUAL' WHERE tipe_properti = :new.tipe_properti AND nama_properti = :new.nama_properti;
END;

CREATE OR REPLACE PROCEDURE hitung_denda (
kode IN transaksi_sewa.id_transaksi%TYPE,
tipe IN properti.tipe_properti%TYPE,
nama IN properti.nama_properti%TYPE
)
IS
current_date tanggal.saat_ini%TYPE;
selesai_sewa transaksi_sewa.selesai_sewa%TYPE;
total transaksi_sewa.total_denda%TYPE;
denda transaksi_sewa.harga_denda_perhari%TYPE;
telat transaksi_sewa.terlambat%TYPE;
BEGIN
SELECT saat_ini INTO current_date FROM tanggal;
SELECT selesai_sewa INTO selesai_sewa FROM transaksi_sewa WHERE id_transaksi = kode;
SELECT harga_denda_perhari INTO denda FROM transaksi_sewa WHERE id_transaksi = kode;
telat := current_date - selesai_sewa;
total := telat * denda;
IF SIGN(telat) = 1 THEN 
UPDATE transaksi_sewa SET status = 'BERHENTI', terlambat = telat, total_denda = total WHERE id_transaksi = kode AND status = 'BERLANGSUNG';
UPDATE properti SET status = 'TERSEDIA' WHERE tipe_properti = tipe AND nama_properti = nama;
ELSE 
UPDATE properti SET status = 'TERSEDIA' WHERE tipe_properti = tipe AND nama_properti = nama;
UPDATE transaksi_sewa SET status = 'BERHENTI' WHERE id_transaksi = kode;
END IF;
END;

CREATE OR REPLACE PROCEDURE menyewa (
username IN transaksi_sewa.username%TYPE,
tipe_properti IN transaksi_sewa.tipe_properti%TYPE,
nama_properti IN transaksi_sewa.nama_properti%TYPE,
mulai_sewa IN VARCHAR2,
selesai_sewa IN VARCHAR2,
no_rekening IN transaksi_sewa.no_rekening%TYPE
)
IS
BEGIN
INSERT INTO transaksi_sewa VALUES
('', username, '', tipe_properti, nama_properti,
TO_DATE(mulai_sewa, 'DD-MM-YYYY'), TO_DATE(selesai_sewa, 'DD-MM-YYYY'),
0, 0, no_rekening, '', 0, 0, 0);
END;

CREATE OR REPLACE PROCEDURE membeli (
username IN transaksi_sewa.username%TYPE,
tipe_properti IN transaksi_sewa.tipe_properti%TYPE,
nama_properti IN transaksi_sewa.nama_properti%TYPE,
no_rekening IN transaksi_sewa.no_rekening%TYPE
)
IS
BEGIN
INSERT INTO transaksi_beli VALUES
('', username, '', tipe_properti, nama_properti, 0, no_rekening, '');
END;


--EXECUTE menyewa (<username>, <tipe_properti>, <nama_properti>, <mulai_sewa>, <selesai_sewa>, <no_rekening>)
EXECUTE menyewa ('rafi', 'Tipe Rumah', 'Rumah 6', '03-01-2023', '20-01-2023', '1122334455');
EXECUTE hitung_denda ('TS00001', 'Tipe Rumah', 'Rumah 6');

-- EXECUTE membeli (<username>, <tipe_properti>, <nama_properti>, <no_rekening>)
EXECUTE membeli ('fayy', 'Tipe Rumah', 'Rumah 9', '9876543210');

/**
INSERT INTO transaksi_sewa VALUES 
('', 'rafi', '', 'Tipe Tanah', 'Tanah 1', 
TO_DATE('01-03-2023', 'DD-MM-YYYY'), TO_DATE('16-03-2023', 'DD-MM-YYYY'), 
0, 0, 1122334455, '', 0, 0, 0); --ERROR karena menyewa tanah
**/

/**INSERT INTO transaksi_beli VALUES
('', 'fayy', '', 'Tipe Rumah', 'Rumah 9', 0, 9876543210, '');**/

EXECUTE menyewa ('fayy', 'Tipe Industrial', 'Mesin Bor', '03-01-2023', '16-01-2023', '1122334455');
CALL menyewa ('fayy', 'Tipe Industrial', 'Gudang', '03-01-2023', '16-01-2023', '1122334455');
EXECUTE hitung_denda ('TS00023', 'Tipe Komersial', 'Ruko2');
EXECUTE hitung_denda ('TS00027', 'Tipe Rumah', 'Rumah 6');
EXECUTE hitung_denda ('TS00002', 'Tipe Rumah', 'Rumah 1');
EXECUTE hitung_denda ('TS00011', 'Tipe Industrial', 'Mesin Bor');
EXECUTE hitung_denda ('TS00012', 'Tipe Industrial', 'Gudang');
EXECUTE hitung_denda ('TS00024', 'Tipe Apartemen', 'Ruang 1');


INSERT INTO transaksi_sewa VALUES ('', 'purn', '', 'Tipe Rumah', 'Rumah 1', TO_DATE('03-01-2023', 'DD-MM-YYYY'), TO_DATE('14-01-2023', 'DD-MM-YYYY'), 0, 0, '7659234728', '', 0, 0, 0);
INSERT INTO transaksi_sewa VALUES ('', 'purn', '', 'Tipe Rumah', 'Rumah 2', TO_DATE('03-01-2023', 'DD-MM-YYYY'), TO_DATE('14-01-2023', 'DD-MM-YYYY'), 0, 0, '7659234728', '', 0, 0, 0);

SELECT id_transaksi, tipe_properti, nama_properti, mulai_sewa, selesai_sewa, no_rekening, status, terlambat, total_denda FROM transaksi_sewa;

INSERT INTO transaksi_sewa VALUES ('', 'purn', '', 'Tipe Rumah', 'Rumah 4', TO_DATE('03-01-2023', 'DD-MM-YYYY'), TO_DATE('14-01-2023', 'DD-MM-YYYY'), 0, 0, '7659234728', '', 0, 0, 0);

SELECT DISTINCT tipe_properti FROM properti WHERE tipe_properti != 'Tipe Tanah';
SELECT nama_properti FROM properti WHERE tipe_properti = 'Tipe Rumah';
SELECT nama_properti FROM properti WHERE tipe_properti = 'Tipe Komersial';
SELECT nama_properti FROM properti WHERE tipe_properti = 'Tipe Industrial';
SELECT nama_properti FROM properti WHERE tipe_properti = 'Tipe Apartemen';
SELECT nama_properti FROM properti WHERE tipe_properti = 'Tipe Tanah';
SELECT harga_sewa_perhari FROM properti WHERE tipe_properti = 'Tipe Apartemen' AND nama_properti = 'Ruang 5';

UPDATE properti SET status = 'TERSEDIA' WHERE id_properti = 'P00036';
DELETE transaksi_beli;

SELECT saat_ini FROM tanggal;