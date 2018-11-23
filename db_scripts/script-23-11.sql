USE [master]
GO
/****** Object:  Database [TPSIP-REAL2]    Script Date: 23/11/2018 20:50:00 ******/
CREATE DATABASE [TPSIP-REAL2]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'TPSIP-REAL2', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\TPSIP-REAL2.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'TPSIP-REAL2_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\TPSIP-REAL2_log.ldf' , SIZE = 2048KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [TPSIP-REAL2] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [TPSIP-REAL2].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [TPSIP-REAL2] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [TPSIP-REAL2] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [TPSIP-REAL2] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [TPSIP-REAL2] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [TPSIP-REAL2] SET ARITHABORT OFF 
GO
ALTER DATABASE [TPSIP-REAL2] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [TPSIP-REAL2] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [TPSIP-REAL2] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [TPSIP-REAL2] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [TPSIP-REAL2] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [TPSIP-REAL2] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [TPSIP-REAL2] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [TPSIP-REAL2] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [TPSIP-REAL2] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [TPSIP-REAL2] SET  DISABLE_BROKER 
GO
ALTER DATABASE [TPSIP-REAL2] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [TPSIP-REAL2] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [TPSIP-REAL2] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [TPSIP-REAL2] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [TPSIP-REAL2] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [TPSIP-REAL2] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [TPSIP-REAL2] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [TPSIP-REAL2] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [TPSIP-REAL2] SET  MULTI_USER 
GO
ALTER DATABASE [TPSIP-REAL2] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [TPSIP-REAL2] SET DB_CHAINING OFF 
GO
ALTER DATABASE [TPSIP-REAL2] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [TPSIP-REAL2] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [TPSIP-REAL2] SET DELAYED_DURABILITY = DISABLED 
GO
USE [TPSIP-REAL2]
GO
/****** Object:  Table [dbo].[Clientes]    Script Date: 23/11/2018 20:50:00 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Clientes](
	[idCliente] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](50) NOT NULL,
	[apellido] [nvarchar](50) NOT NULL,
	[usuario] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NOT NULL,
	[nro_fiscal] [nvarchar](20) NOT NULL,
	[fecha_nacimiento] [date] NULL,
	[telefono] [nvarchar](50) NULL,
	[mail] [nvarchar](150) NOT NULL,
	[domicilio] [nvarchar](100) NOT NULL,
	[altura] [int] NOT NULL,
	[localidad] [nvarchar](100) NOT NULL,
	[provincia] [nvarchar](100) NOT NULL,
	[valoracion] [float] NOT NULL,
	[estado] [nchar](1) NULL,
 CONSTRAINT [PK_Clientes] PRIMARY KEY CLUSTERED 
(
	[idCliente] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Especialidades]    Script Date: 23/11/2018 20:50:00 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Especialidades](
	[idEspecilidad] [int] IDENTITY(1,1) NOT NULL,
	[descripcion] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Especialidades] PRIMARY KEY CLUSTERED 
(
	[idEspecilidad] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Presupuestos]    Script Date: 23/11/2018 20:50:00 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Presupuestos](
	[idPresupuesto] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[observacion] [nvarchar](4000) NULL,
	[valor] [float] NOT NULL,
	[cantidad_jornadas_lab] [int] NOT NULL,
	[valor_materiales] [float] NULL,
 CONSTRAINT [PK_Presupuestos] PRIMARY KEY CLUSTERED 
(
	[idPresupuesto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Problemas]    Script Date: 23/11/2018 20:50:00 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Problemas](
	[idProblema] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[idCliente] [numeric](18, 0) NOT NULL,
	[titulo] [nvarchar](30) NOT NULL,
	[descripcion] [nvarchar](400) NOT NULL,
	[presupuestoMinimo] [numeric](18, 0) NULL,
	[presupuestoMaximo] [numeric](18, 0) NULL,
	[zona] [nvarchar](30) NOT NULL,
	[idRubro] [numeric](18, 0) NOT NULL,
	[idPresupuesto] [numeric](18, 0) NULL,
	[idTrabajo] [numeric](18, 0) NULL,
 CONSTRAINT [PK_Problema] PRIMARY KEY CLUSTERED 
(
	[idProblema] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Profesionales]    Script Date: 23/11/2018 20:50:00 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Profesionales](
	[idProfesional] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](50) NOT NULL,
	[apellido] [nvarchar](50) NOT NULL,
	[usuario] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NOT NULL,
	[nro_fiscal] [nvarchar](20) NOT NULL,
	[fecha_nacimiento] [date] NULL,
	[telefono] [nvarchar](50) NULL,
	[mail] [nvarchar](150) NOT NULL,
	[domicilio] [nvarchar](100) NOT NULL,
	[altura] [int] NOT NULL,
	[localidad] [nvarchar](100) NOT NULL,
	[provincia] [nvarchar](100) NOT NULL,
	[valoracion] [float] NOT NULL,
	[estado] [nchar](1) NULL,
 CONSTRAINT [PK_Profesional] PRIMARY KEY CLUSTERED 
(
	[idProfesional] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[RubroProfesional]    Script Date: 23/11/2018 20:50:00 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RubroProfesional](
	[id] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[idProfesional] [numeric](18, 0) NOT NULL,
	[idRubro] [numeric](18, 0) NOT NULL,
	[matricula] [nvarchar](50) NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Rubros]    Script Date: 23/11/2018 20:50:00 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Rubros](
	[idRubro] [int] IDENTITY(1,1) NOT NULL,
	[descripcion] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Rubros] PRIMARY KEY CLUSTERED 
(
	[idRubro] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Trabajos]    Script Date: 23/11/2018 20:50:00 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Trabajos](
	[idTrabajo] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[fecha_aceptacion] [date] NOT NULL,
	[fecha_inicio] [date] NULL,
	[fecha_fin] [date] NULL,
	[observaciones] [nvarchar](4000) NULL,
	[estado] [nvarchar](50) NULL,
 CONSTRAINT [PK_Trabajos] PRIMARY KEY CLUSTERED 
(
	[idTrabajo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Valoraciones]    Script Date: 23/11/2018 20:50:00 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Valoraciones](
	[idValoracion] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[idValorado] [numeric](18, 0) NOT NULL,
	[idValorador] [numeric](18, 0) NOT NULL,
	[tipoValorado] [nvarchar](15) NOT NULL,
	[detalle] [nvarchar](50) NULL,
	[calificacion] [numeric](18, 0) NOT NULL,
 CONSTRAINT [PK_Valoraciones] PRIMARY KEY CLUSTERED 
(
	[idValoracion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
USE [master]
GO
ALTER DATABASE [TPSIP-REAL2] SET  READ_WRITE 
GO
