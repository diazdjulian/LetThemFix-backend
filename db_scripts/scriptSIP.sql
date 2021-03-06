USE [master]
GO
/****** Object:  Database [TPSIP]    Script Date: 23/11/2018 20:50:00 ******/
CREATE DATABASE [TPSIP]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'TPSIP', FILENAME = N'/var/opt/mssql/data/TPSIP.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'TPSIP_log', FILENAME = N'/var/opt/mssql/data/TPSIP_log.ldf' , SIZE = 2048KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [TPSIP] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [TPSIP].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [TPSIP] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [TPSIP] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [TPSIP] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [TPSIP] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [TPSIP] SET ARITHABORT OFF 
GO
ALTER DATABASE [TPSIP] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [TPSIP] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [TPSIP] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [TPSIP] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [TPSIP] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [TPSIP] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [TPSIP] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [TPSIP] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [TPSIP] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [TPSIP] SET  DISABLE_BROKER 
GO
ALTER DATABASE [TPSIP] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [TPSIP] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [TPSIP] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [TPSIP] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [TPSIP] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [TPSIP] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [TPSIP] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [TPSIP] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [TPSIP] SET  MULTI_USER 
GO
ALTER DATABASE [TPSIP] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [TPSIP] SET DB_CHAINING OFF 
GO
ALTER DATABASE [TPSIP] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [TPSIP] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [TPSIP] SET DELAYED_DURABILITY = DISABLED 
GO
USE [TPSIP]
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
	[idProblema] [numeric](18, 0) NOT NULL,
	[idProfesional] [numeric](18, 0) NOT NULL,
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
ALTER DATABASE [TPSIP] SET  READ_WRITE 
GO
