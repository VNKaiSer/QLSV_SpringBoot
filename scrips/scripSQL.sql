CREATE TABLE SinhVien (
    maSV nvarchar(6) PRIMARY KEY,
    ten NVARCHAR(255),
	gioiTinh bit,
    tuoi INT,
	sdt NVARCHAR(255),
    email NVARCHAR(255),
    diaChi NVARCHAR(255),
	lop nvarchar(255),
	gpa real);

CREATE TABLE Users (
    username VARCHAR(6) PRIMARY KEY NOT NULL ,
    password VARCHAR(50) NOT NULL
);
GO
CREATE TRIGGER tg_insert_sv ON SinhVien 
AFTER INSERT 
AS
	BEGIN
		INSERT INTO Users SELECT maSV,'1234' FROM inserted
	END
GO

CREATE TRIGGER tg_delete_sv ON SinhVien 
AFTER DELETE 
AS
	BEGIN
		DECLARE @mssv nvarchar(6)
		SELECT @mssv = maSV FROM deleted
		DELETE Users WHERE username = @mssv
	END
GO

INSERT INTO SinhVien (maSV, ten, gioiTinh, tuoi, sdt, email, diaChi, lop, gpa)
VALUES
    ('SV0001', N'Nguyễn Văn A', 1, 20, '0123456789', 'nguyenvana@gmail.com', N'Hà Nội', 'CNTT1', 8.0),
    ('SV0002', N'Trần Thị B', 0, 21, '0123456790', 'tranthib@gmail.com', N'Hồ Chí Minh', 'CNTT2', 7.5),
    ('SV0003', N'Lê Văn C', 1, 22, '0123456791', 'levanc@gmail.com', N'Đà Nẵng', 'CNTT1', 9.0),
    ('SV0004', N'Phạm Thị D', 0, 20, '0123456792', 'phamthid@gmail.com', N'Hà Tĩnh', 'CNTT3', 6.5),
    ('SV0005', N'Nguyễn Thị E', 0, 19, '0123456793', 'nguyenthie@gmail.com', N'Quảng Ninh', 'CNTT2', 8.5);

CREATE TABLE SinhVien (
    maSV nvarchar(6) PRIMARY KEY,
    ten NVARCHAR(255),
	gioiTinh bit,
    tuoi INT,
	sdt NVARCHAR(255),
    email NVARCHAR(255),
    diaChi NVARCHAR(255),
	lop nvarchar(255),
	gpa real);

CREATE TABLE Users (
    username VARCHAR(6) PRIMARY KEY NOT NULL ,
    password VARCHAR(50) NOT NULL
);
GO
CREATE TRIGGER tg_insert_sv ON SinhVien 
AFTER INSERT 
AS
	BEGIN
		INSERT INTO Users SELECT maSV,'1234' FROM inserted
	END
GO

CREATE TRIGGER tg_delete_sv ON SinhVien 
AFTER DELETE 
AS
	BEGIN
		DECLARE @mssv nvarchar(6)
		SELECT @mssv = maSV FROM deleted
		DELETE Users WHERE username = @mssv
	END
GO

INSERT INTO SinhVien (maSV, ten, gioiTinh, tuoi, sdt, email, diaChi, lop, gpa)
VALUES
    ('SV0001', N'Nguyễn Văn A', 1, 20, '0123456789', 'nguyenvana@gmail.com', N'Hà Nội', 'CNTT1', 8.0),
    ('SV0002', N'Trần Thị B', 0, 21, '0123456790', 'tranthib@gmail.com', N'Hồ Chí Minh', 'CNTT2', 7.5),
    ('SV0003', N'Lê Văn C', 1, 22, '0123456791', 'levanc@gmail.com', N'Đà Nẵng', 'CNTT1', 9.0),
    ('SV0004', N'Phạm Thị D', 0, 20, '0123456792', 'phamthid@gmail.com', N'Hà Tĩnh', 'CNTT3', 6.5),
    ('SV0005', N'Nguyễn Thị E', 0, 19, '0123456793', 'nguyenthie@gmail.com', N'Quảng Ninh', 'CNTT2', 8.5);

