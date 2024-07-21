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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table akademik.guru: ~0 rows (approximately)
REPLACE INTO `guru` (`id_guru`, `nama`, `tempat_lahir`, `tgl_lahir`, `jenis_kelamin`, `status_kepegawaian`, `mengajar_mapel`) VALUES
	(4, 'Afghan', 'test', '2024-07-03', 'P', 'HONOR', 'MATEMATIKA');

-- Dumping structure for table akademik.mapel
CREATE TABLE IF NOT EXISTS `mapel` (
  `kode_mapel` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `nama_mapel` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`kode_mapel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table akademik.mapel: ~0 rows (approximately)
REPLACE INTO `mapel` (`kode_mapel`, `nama_mapel`) VALUES
	('MAP001', 'MATEMATIKA');

-- Dumping structure for table akademik.nilai_siswa
CREATE TABLE IF NOT EXISTS `nilai_siswa` (
  `id_nilai` int NOT NULL AUTO_INCREMENT,
  `tahun_pelajaran` int DEFAULT NULL,
  `semester` int DEFAULT NULL,
  `kelas` varchar(10) DEFAULT NULL,
  `angkatan` int DEFAULT NULL,
  `nis` int DEFAULT NULL,
  `kode_mapel` varchar(10) DEFAULT NULL,
  `id_guru` int DEFAULT NULL,
  `nilai_absen` decimal(5,2) DEFAULT NULL,
  `nilai_harian` decimal(5,2) DEFAULT NULL,
  `nilai_uts` decimal(5,2) DEFAULT NULL,
  `nilai_uas` decimal(5,2) DEFAULT NULL,
  `nilai_rata2` decimal(5,2) GENERATED ALWAYS AS (((((`nilai_absen` + `nilai_harian`) + `nilai_uts`) + `nilai_uas`) / 4)) STORED,
  `grade` varchar(2) DEFAULT NULL,
  `keterangan` text,
  PRIMARY KEY (`id_nilai`),
  KEY `nis` (`nis`),
  KEY `kode_mapel` (`kode_mapel`),
  KEY `id_guru` (`id_guru`),
  CONSTRAINT `nilai_siswa_ibfk_1` FOREIGN KEY (`nis`) REFERENCES `siswa` (`nis`),
  CONSTRAINT `nilai_siswa_ibfk_2` FOREIGN KEY (`kode_mapel`) REFERENCES `mapel` (`kode_mapel`),
  CONSTRAINT `nilai_siswa_ibfk_3` FOREIGN KEY (`id_guru`) REFERENCES `guru` (`id_guru`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table akademik.nilai_siswa: ~0 rows (approximately)

-- Dumping structure for table akademik.pembayaran
CREATE TABLE IF NOT EXISTS `pembayaran` (
  `kode_pembayaran` varchar(10) NOT NULL,
  `jenis_pembayaran` enum('spp_perbulan','registrasi_ulang','pembiayaan_kegiatan','lain2') DEFAULT NULL,
  `nominal` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`kode_pembayaran`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table akademik.pembayaran: ~0 rows (approximately)

-- Dumping structure for table akademik.siswa
CREATE TABLE IF NOT EXISTS `siswa` (
  `nis` int NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `tempat_lahir` varchar(100) DEFAULT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `jenis_kelamin` enum('L','P') DEFAULT NULL,
  `nisn` int DEFAULT NULL,
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

-- Dumping data for table akademik.siswa: ~0 rows (approximately)

-- Dumping structure for table akademik.transaksi_pembayaran
CREATE TABLE IF NOT EXISTS `transaksi_pembayaran` (
  `no_transaksi` int NOT NULL AUTO_INCREMENT,
  `nis` int DEFAULT NULL,
  `tgl_pembayaran` date DEFAULT NULL,
  `kode_pembayaran` varchar(10) DEFAULT NULL,
  `jumlah_bayar` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`no_transaksi`),
  KEY `nis` (`nis`),
  KEY `kode_pembayaran` (`kode_pembayaran`),
  CONSTRAINT `transaksi_pembayaran_ibfk_1` FOREIGN KEY (`nis`) REFERENCES `siswa` (`nis`),
  CONSTRAINT `transaksi_pembayaran_ibfk_2` FOREIGN KEY (`kode_pembayaran`) REFERENCES `pembayaran` (`kode_pembayaran`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table akademik.transaksi_pembayaran: ~0 rows (approximately)

-- Dumping structure for table akademik.users
CREATE TABLE IF NOT EXISTS `users` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `nama` varchar(100) DEFAULT NULL,
  `jabatan` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table akademik.users: ~0 rows (approximately)
REPLACE INTO `users` (`id_user`, `nama`, `jabatan`, `username`, `password`) VALUES
	(1, 'test', 'test', 'test', 'test');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
