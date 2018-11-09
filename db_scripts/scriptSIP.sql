
CREATE TABLE [dbo].[Clientes](
	[idCliente] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](50) NOT NULL,
	[apellido] [nvarchar](50) NOT NULL,
	[usuario] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NOT NULL,
	[nro_fiscal] [nvarchar](20) NOT NULL,
	[fecha_nacimento] [date] NULL,
	[telefono] [nvarchar](50) NULL,
	[mail] [nvarchar](150) NOT NULL,
	[calle] [nvarchar](100) NOT NULL,
	[altura] [int] NOT NULL,
	[localidad] [nvarchar](100) NOT NULL,
	[provincia] [nvarchar](100) NOT NULL,
	[valoracion] [float] NOT NULL,
 CONSTRAINT [PK_Clientes] PRIMARY KEY CLUSTERED 
(
	[idCliente] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

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


/****** Object:  Table [dbo].[Presupuestos]    Script Date: 23/10/2018 21:04:04 ******/
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
/****** Object:  Table [dbo].[Problemas]    Script Date: 23/10/2018 21:04:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Problemas](
	[idProblema] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[idCliente] [numeric](18, 0) NOT NULL,
	[titulo] [nvarchar](30) NOT NULL,
	[descripcion] [nvarchar](400) NOT NULL,
	[presupuestoMinimo] [numeric](18, 0) NOT NULL,
	[presupuestoMaximo] [numeric](18, 0) NOT NULL,
	[zona] [nvarchar](30) NOT NULL,
	[idRubro] [numeric](18, 0) NOT NULL,
	[idPresupuesto] [numeric](18, 0) NOT NULL,
	[idTrabajo] [numeric](18, 0) NOT NULL,
 CONSTRAINT [PK_Problema] PRIMARY KEY CLUSTERED 
(
	[idProblema] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Profesionales]    Script Date: 23/10/2018 21:04:04 ******/
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
	[fecha_nacimento] [date] NULL,
	[telefono] [nvarchar](50) NULL,
	[mail] [nvarchar](150) NOT NULL,
	[calle] [nvarchar](100) NOT NULL,
	[altura] [int] NOT NULL,
	[localidad] [nvarchar](100) NOT NULL,
	[provincia] [nvarchar](100) NOT NULL,
	[valoracion] [float] NOT NULL,
 CONSTRAINT [PK_Profesional] PRIMARY KEY CLUSTERED 
(
	[idProfesional] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Trabajos]    Script Date: 23/10/2018 21:04:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Trabajos](
	[idTrabajo] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[idProblema] [numeric](18, 0) NOT NULL,
	[idProfesional] [numeric](18, 0) NOT NULL,
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

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Valoraciones](
	[idValoracion] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[idValorado] [numeric](18, 0) NOT NULL,
	[idValorador] [numeric](18, 0) NOT NULL,
	[tipoValorado] [nvarchar](10) NULL,
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
