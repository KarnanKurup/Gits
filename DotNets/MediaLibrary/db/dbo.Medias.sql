USE [C:\USERS\HARE KRISHNA\DOCUMENTS\MEDIALIB.MDF]
GO

/****** Object: Table [dbo].[Medias] Script Date: 1/23/2017 4:57:34 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

DROP TABLE [dbo].[Medias];


GO
CREATE TABLE [dbo].[Medias] (
    [mid]     INT           IDENTITY (1, 1) NOT NULL,
    [adminid] VARCHAR (50)  NOT NULL,
    [mtype]   VARCHAR (10)  NOT NULL,
    [mtitle]  VARCHAR (100) NOT NULL,
    [mdesc]   VARCHAR (300) NOT NULL,
    [mpath]   VARCHAR (650) NOT NULL
);

CREATE TABLE [dbo].[Medias] (
    [mid]     INT           IDENTITY (1, 1) NOT NULL,
    [adminid] VARCHAR (50)  NOT NULL,
    [mtype]   VARCHAR (10)  NOT NULL,
    [mtitle]  VARCHAR (100) NOT NULL,
    [mdesc]   VARCHAR (300) NOT NULL,
    [mpath]   VARCHAR (650) NOT NULL,
    PRIMARY KEY CLUSTERED ([mid] ASC),
    FOREIGN KEY ([adminid]) REFERENCES [dbo].[Admins] ([AdminId])
);




