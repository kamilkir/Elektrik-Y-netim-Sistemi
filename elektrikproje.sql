-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 17 Oca 2022, 01:52:33
-- Sunucu sürümü: 10.4.22-MariaDB
-- PHP Sürümü: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `elektrikproje`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `bagis`
--

CREATE TABLE `bagis` (
  `id` int(11) DEFAULT NULL,
  `musteri_no` int(11) NOT NULL,
  `tutar` int(11) NOT NULL,
  `odeme_tarihi` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `faturalar`
--

CREATE TABLE `faturalar` (
  `id` int(11) NOT NULL,
  `musteri_no` int(11) NOT NULL,
  `tuketim` int(11) NOT NULL,
  `birim_fiyat` int(11) NOT NULL,
  `vergi_orani` int(11) NOT NULL,
  `fatura_kesim` date NOT NULL,
  `fatura_son_odeme` date NOT NULL,
  `odenme_durum` tinyint(1) NOT NULL,
  `tutar` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `faturalar`
--

INSERT INTO `faturalar` (`id`, `musteri_no`, `tuketim`, `birim_fiyat`, `vergi_orani`, `fatura_kesim`, `fatura_son_odeme`, `odenme_durum`, `tutar`) VALUES
(3, 22, 330, 1, 18, '2021-11-17', '2021-12-01', 1, 379.49999999999994),
(4, 22, 254, 1, 18, '2021-12-17', '2021-12-31', 0, 292.09999999999997),
(5, 22, 1674, 1, 15, '2021-11-10', '2021-11-25', 1, 1925.1),
(6, 111, 1423, 1, 15, '2021-12-10', '2022-01-25', 0, 1679.1399999999999);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `k_musteriler`
--

CREATE TABLE `k_musteriler` (
  `id` int(11) NOT NULL,
  `musteri_no` int(11) NOT NULL,
  `sifre` text NOT NULL,
  `firma` text NOT NULL,
  `firma_yetkili` text NOT NULL,
  `vergi_no` text NOT NULL,
  `telefon_no` text NOT NULL,
  `musteri_tip` tinyint(1) NOT NULL,
  `uyelik_tarih` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `k_musteriler`
--

INSERT INTO `k_musteriler` (`id`, `musteri_no`, `sifre`, `firma`, `firma_yetkili`, `vergi_no`, `telefon_no`, `musteri_tip`, `uyelik_tarih`) VALUES
(1, 112794030, '123456789', 'Kamil', 'Kır', '10246848878', '5422535940', 0, '2015-03-17'),
(3, 22, '123', 'VYNER TEKNOLOJİ VE İNAVASYON LTD.ŞTİ', 'Mehmet DEMİR', '12532563215', '05436545678', 1, '2011-12-07'),
(4, 170684767, '123456789', 'Yasin', 'Rümel', '56948573981', '5325989874', 0, '2019-08-13'),
(5, 153800439, '123456789', 'Kürşat', 'Demir', '55345309056', '05320099945', 0, '2021-10-12'),
(6, 233775690, '123456789', 'HATAY LEZZETLERİ ANONİM ŞTİ', 'Canercan İntizamoğlu', '32245900577', '5418984555', 1, '2020-07-10'),
(7, 109099745, '123456789', 'Gökçen', 'Kıyıcı', '11278900688', '05447785667', 0, '2020-08-12'),
(8, 167756090, '123456789', 'Eylül', 'Rümel', '45709457851', '5426774111', 0, '2021-09-21'),
(9, 267501732, '123456789', 'BELİNAY YAĞ SAN. TİC. A.Ş', 'Belinay Yeditepe', '11278031697', '05556789231', 1, '2021-09-15'),
(10, 177800456, '123456789', 'Furkan', 'Minareciler', '20748589452', '05433934589', 0, '2021-11-16'),
(11, 188941723, '123456789', 'Göksu', 'Kırış', '2336780102', '5323275690', 0, '2018-12-19'),
(12, 207871430, 'dfh', 'dfdgf', '234', '34324', 'dfg', 2, '2022-17-01'),
(13, 225044119, '345', 'hrthfgh', '345', '234', '234', 2, '2022-17-01'),
(14, 277957685, '345', '324234', 'fdgdsf', 'ergdfg', 'dfg345', 2, '2022-17-01'),
(15, 228993428, '234', 'kamil213', '31', '456dfgdfgdfg', '345dfgdfh', 2, '2022-17-01');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `musteriler`
--

CREATE TABLE `musteriler` (
  `id` int(11) NOT NULL,
  `musteri_no` int(11) NOT NULL,
  `sifre` text NOT NULL,
  `ad` text NOT NULL,
  `soyad` text NOT NULL,
  `tc_no` text NOT NULL,
  `telefon_no` text NOT NULL,
  `musteri_tip` tinyint(1) NOT NULL,
  `uyelik_tarih` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `musteriler`
--

INSERT INTO `musteriler` (`id`, `musteri_no`, `sifre`, `ad`, `soyad`, `tc_no`, `telefon_no`, `musteri_tip`, `uyelik_tarih`) VALUES
(1, 111, '111', 'Kamil', 'Kır', '10246848878', '5422535940', 0, '2015-03-17'),
(3, 22, '123456789', 'VYNER TEKNOLOJİ VE İNAVASYON LTD.ŞTİ', 'Mehmet DEMİR', '12532563215', '05436545678', 1, '2011-12-07'),
(4, 170684767, '123456789', 'Yasin', 'Rümel', '56948573981', '5325989874', 0, '2019-08-13'),
(5, 153800439, '123456789', 'Kürşat', 'Demir', '55345309056', '05320099945', 0, '2021-10-12'),
(6, 233775690, '123456789', 'HATAY LEZZETLERİ ANONİM ŞTİ', 'Canercan İntizamoğlu', '32245900577', '5418984555', 1, '2020-07-10'),
(7, 109099745, '123456789', 'Gökçen', 'Kıyıcı', '11278900688', '05447785667', 0, '2020-08-12'),
(8, 167756090, '123456789', 'Eylül', 'Rümel', '45709457851', '5426774111', 0, '2021-09-21'),
(9, 267501732, '123456789', 'BELİNAY YAĞ SAN. TİC. A.Ş', 'Belinay Yeditepe', '11278031697', '05556789231', 1, '2021-09-15'),
(10, 177800456, '123456789', 'Furkan', 'Minareciler', '20748589452', '05433934589', 0, '2021-11-16'),
(11, 188941723, '123456789', 'Göksu', 'Kırış', '2336780102', '5323275690', 0, '2018-12-19'),
(12, 504948564, '123', '234', '234', '2324', '234', 1, '2022-17-01'),
(13, 220587136, '345', '3423', 'dfg', 'fgh', '345', 1, '2022-17-01');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `odenmis_faturalar`
--

CREATE TABLE `odenmis_faturalar` (
  `id` int(11) NOT NULL,
  `musteri_no` int(11) NOT NULL,
  `tutar` double NOT NULL,
  `tuketim` int(11) NOT NULL,
  `odeme_tarihi` text NOT NULL,
  `gecikme_bedeli` double NOT NULL,
  `odeme_son_tarihi` text NOT NULL,
  `kesim_tarihi` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `odenmis_faturalar`
--

INSERT INTO `odenmis_faturalar` (`id`, `musteri_no`, `tutar`, `tuketim`, `odeme_tarihi`, `gecikme_bedeli`, `odeme_son_tarihi`, `kesim_tarihi`) VALUES
(1, 22, 379.49999999999994, 330, '2022-17-01', 0, '2021-12-01', '2021-11-17'),
(2, 22, 379.49999999999994, 330, '2022-17-01', 0, '2021-12-01', '2021-11-17'),
(3, 22, 292.09999999999997, 254, '2022-17-01', 0, '2021-12-31', '2021-12-17'),
(4, 22, 379.49999999999994, 330, '2022-17-01', 0, '2021-12-01', '2021-11-17'),
(5, 22, 379.49999999999994, 330, '2022-17-01', 0, '2021-12-01', '2021-11-17'),
(6, 111, 1679.1399999999999, 1423, '2022-17-01', 0, '2022-01-25', '2021-12-10');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `faturalar`
--
ALTER TABLE `faturalar`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `k_musteriler`
--
ALTER TABLE `k_musteriler`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `musteri_no_2` (`musteri_no`),
  ADD UNIQUE KEY `musteri_no_3` (`musteri_no`),
  ADD KEY `musteri_no` (`musteri_no`);

--
-- Tablo için indeksler `musteriler`
--
ALTER TABLE `musteriler`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `musteri_no_2` (`musteri_no`),
  ADD UNIQUE KEY `musteri_no_3` (`musteri_no`),
  ADD KEY `musteri_no` (`musteri_no`);

--
-- Tablo için indeksler `odenmis_faturalar`
--
ALTER TABLE `odenmis_faturalar`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `faturalar`
--
ALTER TABLE `faturalar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Tablo için AUTO_INCREMENT değeri `k_musteriler`
--
ALTER TABLE `k_musteriler`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Tablo için AUTO_INCREMENT değeri `musteriler`
--
ALTER TABLE `musteriler`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Tablo için AUTO_INCREMENT değeri `odenmis_faturalar`
--
ALTER TABLE `odenmis_faturalar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
