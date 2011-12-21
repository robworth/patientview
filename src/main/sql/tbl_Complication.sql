CREATE TABLE rpv.dbo.tbl_Complication (
	cmpID int NOT NULL,
	cmpDesc nvarchar(50)
);

INSERT INTO rpv.dbo.tbl_Complication SELECT * FROM rpv.dbo.tbl_Complication;

