-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.30 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for akademik
CREATE DATABASE IF NOT EXISTS `akademik` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `akademik`;

-- Dumping structure for table akademik.guru
CREATE TABLE IF NOT EXISTS `guru` (
  `id_guru` int NOT NULL AUTO_INCREMENT,
  `nama` varchar(100) DEFAULT NULL,
  `tempat_lahir` varchar(100) DEFAULT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `jenis_kelamin` enum('L','P') DEFAULT NULL,
  `status_kepegawaian` enum('TETAP','HONOR') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `mengajar_mapel` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_guru`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table akademik.guru: ~10 rows (approximately)
INSERT INTO `guru` (`id_guru`, `nama`, `tempat_lahir`, `tgl_lahir`, `jenis_kelamin`, `status_kepegawaian`, `mengajar_mapel`) VALUES
	(1, 'Andi Pratama', 'Bandung', '1980-02-15', 'L', 'TETAP', 'Matematika'),
	(2, 'Budi Setiawan', 'Jakarta', '1975-08-20', 'L', 'HONOR', 'Fisika'),
	(3, 'Siti Aminah', 'Surabaya', '1985-11-10', 'P', 'TETAP', 'Biologi'),
	(4, 'Rina Kusuma', 'Yogyakarta', '1990-05-30', 'P', 'HONOR', 'Kimia'),
	(5, 'Joko Susilo', 'Medan', '1982-07-25', 'L', 'TETAP', 'Ekonomi'),
	(6, 'Lina Mariani', 'Bandung', '1978-01-20', 'P', 'HONOR', 'Geografi'),
	(7, 'Tommy Hadi', 'Jakarta', '1981-04-15', 'L', 'TETAP', 'Sejarah'),
	(8, 'Yuni Rahayu', 'Surabaya', '1987-09-10', 'P', 'HONOR', 'Bahasa Inggris'),
	(9, 'Ferry Kurniawan', 'Medan', '1983-12-05', 'L', 'TETAP', 'Komputer'),
	(10, 'Wati Hasanah', 'Yogyakarta', '1989-07-30', 'P', 'HONOR', 'Seni');

-- Dumping structure for table akademik.mapel
CREATE TABLE IF NOT EXISTS `mapel` (
  `kode_mapel` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `nama_mapel` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`kode_mapel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table akademik.mapel: ~10 rows (approximately)
INSERT INTO `mapel` (`kode_mapel`, `nama_mapel`) VALUES
	('M001', 'Matematika'),
	('M002', 'Fisika'),
	('M003', 'Biologi'),
	('M004', 'Kimia'),
	('M005', 'Ekonomi'),
	('M006', 'Geografi'),
	('M007', 'Sejarah'),
	('M008', 'Bahasa Inggris'),
	('M009', 'Komputer'),
	('M010', 'Seni');

-- Dumping structure for table akademik.nilai_siswa
CREATE TABLE IF NOT EXISTS `nilai_siswa` (
  `id_nilai` int NOT NULL AUTO_INCREMENT,
  `tahun_pelajaran` varchar(50) DEFAULT NULL,
  `semester` int DEFAULT NULL,
  `kelas` varchar(10) DEFAULT NULL,
  `angkatan` int DEFAULT NULL,
  `nis` varchar(50) DEFAULT NULL,
  `kode_mapel` varchar(10) DEFAULT NULL,
  `id_guru` int DEFAULT NULL,
  `nilai_absen` decimal(5,2) DEFAULT NULL,
  `nilai_harian` decimal(5,2) DEFAULT NULL,
  `nilai_uts` decimal(5,2) DEFAULT NULL,
  `nilai_uas` decimal(5,2) DEFAULT NULL,
  `nilai_rata2` decimal(5,2) DEFAULT NULL,
  `grade` varchar(2) DEFAULT NULL,
  `keterangan` text,
  PRIMARY KEY (`id_nilai`),
  KEY `FK_nilai_siswa_siswa` (`nis`),
  KEY `nilai_siswa_ibfk_2` (`kode_mapel`),
  KEY `nilai_siswa_ibfk_3` (`id_guru`),
  CONSTRAINT `FK_nilai_siswa_siswa` FOREIGN KEY (`nis`) REFERENCES `siswa` (`nis`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `nilai_siswa_ibfk_2` FOREIGN KEY (`kode_mapel`) REFERENCES `mapel` (`kode_mapel`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `nilai_siswa_ibfk_3` FOREIGN KEY (`id_guru`) REFERENCES `guru` (`id_guru`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table akademik.nilai_siswa: ~1 rows (approximately)
INSERT INTO `nilai_siswa` (`id_nilai`, `tahun_pelajaran`, `semester`, `kelas`, `angkatan`, `nis`, `kode_mapel`, `id_guru`, `nilai_absen`, `nilai_harian`, `nilai_uts`, `nilai_uas`, `nilai_rata2`, `grade`, `keterangan`) VALUES
	(3, '2023/2024', 2, 'XI RPL 2', 2024, '202043501781', 'M001', 1, 90.00, 90.00, 90.00, 90.00, 90.00, 'A', 'LULUS');

-- Dumping structure for table akademik.pembayaran
CREATE TABLE IF NOT EXISTS `pembayaran` (
  `kode_pembayaran` varchar(10) NOT NULL,
  `jenis_pembayaran` enum('SPP_PERBULAN','REGISTRASI_ULANG','PEMBIAYAAN_ULANG','LAIN2') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `nominal` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`kode_pembayaran`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table akademik.pembayaran: ~1 rows (approximately)
INSERT INTO `pembayaran` (`kode_pembayaran`, `jenis_pembayaran`, `nominal`) VALUES
	('PEM001', 'SPP_PERBULAN', 900000.00);

-- Dumping structure for table akademik.siswa
CREATE TABLE IF NOT EXISTS `siswa` (
  `nis` varchar(50) NOT NULL DEFAULT '',
  `nama` varchar(100) DEFAULT NULL,
  `tempat_lahir` varchar(100) DEFAULT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `jenis_kelamin` enum('L','P') DEFAULT NULL,
  `nisn` varchar(50) DEFAULT NULL,
  `angkatan` int DEFAULT NULL,
  `kelas` varchar(10) DEFAULT NULL,
  `agama` varchar(50) DEFAULT NULL,
  `alamat` text,
  `no_telp` varchar(20) DEFAULT NULL,
  `nama_ayah` varchar(100) DEFAULT NULL,
  `tgl_lahir_ayah` date DEFAULT NULL,
  `pendidikan_ayah` varchar(50) DEFAULT NULL,
  `pekerjaan_ayah` varchar(50) DEFAULT NULL,
  `nama_ibu` varchar(100) DEFAULT NULL,
  `tgl_lahir_ibu` date DEFAULT NULL,
  `pendidikan_ibu` varchar(50) DEFAULT NULL,
  `pekerjaan_ibu` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`nis`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table akademik.siswa: ~1 rows (approximately)
INSERT INTO `siswa` (`nis`, `nama`, `tempat_lahir`, `tgl_lahir`, `jenis_kelamin`, `nisn`, `angkatan`, `kelas`, `agama`, `alamat`, `no_telp`, `nama_ayah`, `tgl_lahir_ayah`, `pendidikan_ayah`, `pekerjaan_ayah`, `nama_ibu`, `tgl_lahir_ibu`, `pendidikan_ibu`, `pekerjaan_ibu`) VALUES
	('202043501781', 'Afaan', 'Jakarta', '2024-07-03', 'L', '202403591285', 2024, 'XI RPL 2', 'Islam', 'test', '085156238645', 'Test', '2024-07-04', 'test', 'test', 'test', '2024-07-04', 'test', 'test');

-- Dumping structure for table akademik.transaksi_pembayaran
CREATE TABLE IF NOT EXISTS `transaksi_pembayaran` (
  `no_transaksi` int NOT NULL AUTO_INCREMENT,
  `nis` varchar(50) DEFAULT NULL,
  `tgl_pembayaran` date DEFAULT NULL,
  `kode_pembayaran` varchar(10) DEFAULT NULL,
  `jumlah_bayar` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`no_transaksi`),
  KEY `FK_transaksi_pembayaran_siswa` (`nis`),
  KEY `transaksi_pembayaran_ibfk_2` (`kode_pembayaran`),
  CONSTRAINT `FK_transaksi_pembayaran_siswa` FOREIGN KEY (`nis`) REFERENCES `siswa` (`nis`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `transaksi_pembayaran_ibfk_2` FOREIGN KEY (`kode_pembayaran`) REFERENCES `pembayaran` (`kode_pembayaran`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table akademik.transaksi_pembayaran: ~1 rows (approximately)
INSERT INTO `transaksi_pembayaran` (`no_transaksi`, `nis`, `tgl_pembayaran`, `kode_pembayaran`, `jumlah_bayar`) VALUES
	(2, '202043501781', '2024-07-10', 'PEM001', 900000.00);

-- Dumping structure for table akademik.users
CREATE TABLE IF NOT EXISTS `users` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `nama` varchar(100) DEFAULT NULL,
  `jabatan` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table akademik.users: ~1 rows (approximately)
INSERT INTO `users` (`id_user`, `nama`, `jabatan`, `username`, `password`) VALUES
	(7, 'admin', 'Kepala Sekolah', 'admin', 'admin');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
