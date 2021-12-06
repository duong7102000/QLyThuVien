-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost
-- Thời gian đã tạo: Th12 04, 2021 lúc 02:54 PM
-- Phiên bản máy phục vụ: 10.4.21-MariaDB
-- Phiên bản PHP: 7.3.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `QLThuVien`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_chitietphieumuon`
--

CREATE TABLE `tbl_chitietphieumuon` (
  `id_phieumuon` int(10) NOT NULL,
  `id_sach` int(8) NOT NULL,
  `date_borrow` date NOT NULL,
  `date_return` date NOT NULL,
  `forfeit` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tbl_chitietphieumuon`
--

INSERT INTO `tbl_chitietphieumuon` (`id_phieumuon`, `id_sach`, `date_borrow`, `date_return`, `forfeit`) VALUES
(1234578900, 1234567800, '2021-12-17', '2022-01-01', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_phieumuon`
--

CREATE TABLE `tbl_phieumuon` (
  `id_phieumuon` int(10) NOT NULL,
  `id_sinhvien` int(8) NOT NULL,
  `id_thuthu` int(8) NOT NULL,
  `date_start` date NOT NULL,
  `date_end` date NOT NULL,
  `deposit` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tbl_phieumuon`
--

INSERT INTO `tbl_phieumuon` (`id_phieumuon`, `id_sinhvien`, `id_thuthu`, `date_start`, `date_end`, `deposit`) VALUES
(1234578900, 20180000, 12345678, '2021-12-09', '2022-01-07', 20000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_sach`
--

CREATE TABLE `tbl_sach` (
  `id` int(10) NOT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `artist` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `major` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tbl_sach`
--

INSERT INTO `tbl_sach` (`id`, `name`, `artist`, `content`, `major`) VALUES
(1234567800, 'Ky thuat lap trinh', 'Ths. Doctor', 'Co ban ve lap trinh', 'Cong nghe thong tin');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_sinhvien`
--

CREATE TABLE `tbl_sinhvien` (
  `id` int(8) NOT NULL,
  `fullname` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `birth` date NOT NULL,
  `address` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `major` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `class` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tbl_sinhvien`
--

INSERT INTO `tbl_sinhvien` (`id`, `fullname`, `birth`, `address`, `major`, `class`) VALUES
(20180000, 'Bui Van Huy', '2000-12-12', 'Ha Noi', 'Ky thuat dien tu', '01'),
(20183764, 'Bùi Quang huy', '2000-03-29', 'Thai Binh', 'Ky thuat may tinh', 'IT2-01');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_taikhoan`
--

CREATE TABLE `tbl_taikhoan` (
  `username` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `position` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tbl_taikhoan`
--

INSERT INTO `tbl_taikhoan` (`username`, `password`, `position`) VALUES
('admin', 'admin', 'admin'),
('20183764', '123456', 'student'),
('12345678', '123456', 'librarian');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_thuthu`
--

CREATE TABLE `tbl_thuthu` (
  `id` int(8) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `birth` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tbl_thuthu`
--

INSERT INTO `tbl_thuthu` (`id`, `name`, `birth`) VALUES
(12345678, 'Bui Van A', '1990-01-01');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `tbl_chitietphieumuon`
--
ALTER TABLE `tbl_chitietphieumuon`
  ADD PRIMARY KEY (`id_phieumuon`,`id_sach`),
  ADD KEY `id_sach` (`id_sach`);

--
-- Chỉ mục cho bảng `tbl_phieumuon`
--
ALTER TABLE `tbl_phieumuon`
  ADD PRIMARY KEY (`id_phieumuon`,`id_sinhvien`,`id_thuthu`),
  ADD KEY `id_sinhvien` (`id_sinhvien`),
  ADD KEY `id_thuthu` (`id_thuthu`);

--
-- Chỉ mục cho bảng `tbl_sach`
--
ALTER TABLE `tbl_sach`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tbl_sinhvien`
--
ALTER TABLE `tbl_sinhvien`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tbl_thuthu`
--
ALTER TABLE `tbl_thuthu`
  ADD PRIMARY KEY (`id`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `tbl_chitietphieumuon`
--
ALTER TABLE `tbl_chitietphieumuon`
  ADD CONSTRAINT `tbl_chitietphieumuon_ibfk_1` FOREIGN KEY (`id_phieumuon`) REFERENCES `tbl_phieumuon` (`id_phieumuon`),
  ADD CONSTRAINT `tbl_chitietphieumuon_ibfk_2` FOREIGN KEY (`id_sach`) REFERENCES `tbl_sach` (`id`);

--
-- Các ràng buộc cho bảng `tbl_phieumuon`
--
ALTER TABLE `tbl_phieumuon`
  ADD CONSTRAINT `tbl_phieumuon_ibfk_2` FOREIGN KEY (`id_sinhvien`) REFERENCES `tbl_sinhvien` (`id`),
  ADD CONSTRAINT `tbl_phieumuon_ibfk_3` FOREIGN KEY (`id_thuthu`) REFERENCES `tbl_thuthu` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
