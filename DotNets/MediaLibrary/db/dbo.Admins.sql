USE [C:\USERS\HARE KRISHNA\DOCUMENTS\MEDIALIB.MDF]
GO

/****** Object: Table [dbo].[Admins] Script Date: 1/23/2017 4:57:01 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

DROP TABLE [dbo].[Admins];


GO
CREATE TABLE [dbo].[Admins] (
    [AdminId] VARCHAR (50) NOT NULL,
    [pass]    VARCHAR (50) NOT NULL
);

CREATE TABLE [dbo].[Admins] (
    [AdminId] VARCHAR (50) NOT NULL,
    [pass]    VARCHAR (50) NOT NULL,
    PRIMARY KEY CLUSTERED ([AdminId] ASC)
);
